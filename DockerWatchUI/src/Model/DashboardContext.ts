import {Image} from './Image';
import {Container} from './Container';
import { Host } from './Host';
import { HTTPService } from 'src/Services/HttpService';
import { MessagePattern } from './MessagePattern';
import { MessageStatus } from './MessageStatus';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { AppConfig } from 'src/app/app.config';
import { Observable } from 'rxjs';
import { LogRequest } from './LogRequest';

export class DashboardContext{
    
    hosts: Host[];
    Images: Image[];
    Containers: Container[];

    selectedHost?: Host;
    selectedImage: Image;
    selectedContainer?: Container;

    EditBoxHost: Host = new Host();

    httpService:HTTPService;

    logs: string ="";
  
    constructor(hosts: Host[], images: Image[], containers:Container[], httpService: HTTPService) {
      this.hosts = hosts;
      this.Images = images;
      this.Containers = containers;
      this.httpService = httpService;
    }

    Initialize(){
      this.httpService.get<MessagePattern<Host[]>>(AppConfig.Address.GetAllHost).subscribe(data => {
        this.hosts = data.message;
      });
    }

    ConnectHost(host: Host, topic: string){
      if (this.selectedHost && this.selectedHost.id === host.id) {
        this._disconnect();
      } else {
        this._connect(host, topic);
      }
    }

    EditSelectedHost(){
      if(this.selectedHost){
        this.EditBoxHost = this.selectedHost;
      } else {
        this.EditBoxHost = new Host();
      }
    }

    AddNewHost(){
      this.EditBoxHost = new Host();
    }

    SaveHost(){
      let request = new MessagePattern<Host>(this.EditBoxHost);
      this.httpService.post<MessagePattern<MessageStatus>>(AppConfig.Address.SaveHost, request).subscribe(data => {
        if(data.message.status == "SUCCESS"){
          console.log(data);
          this.Initialize();
        }
      });
    }

    DeleteHost(){
      if(this.selectedHost){
        let request = new MessagePattern<Host>(this.selectedHost);
        this.httpService.delete<MessagePattern<MessageStatus>>(AppConfig.Address.DeleteHost, request).subscribe(data => {
          if(data.message.status == "SUCCESS"){
            console.log(data);
            this.Initialize();
          }
        });
      }
    }

    SelectContainer(container: Container) {
      this.selectedContainer = container;
    }

    async GetImages(host: Host) {
      this._send(host, AppConfig.Address.Images);
    }

    async GetContainer(host: Host) {
      this._send(host, AppConfig.Address.Containers);
    }

    async GetLogs(host: Host, container: Container) {
      var req = new LogRequest(host, container);
      this._send(req, AppConfig.Address.Containers);
    }

    Logout(req: MessagePattern<string>): Observable<MessagePattern<MessageStatus>> {
        return this.httpService.post<MessagePattern<MessageStatus>>(AppConfig.Address.Login, req);
    }


    stompClient: any;

    _connect(host: Host, topic: string) {
        console.log("Initialize WebSocket Connection");
        let ws = new SockJS(AppConfig.Address.SocketURL);
        this.stompClient = Stomp.over(ws);
        const _this = this;
        _this.stompClient.connect(host, function (frame: any) {
            _this.stompClient.subscribe(topic, function (sdkEvent:any) {
                _this.onMessageReceived(sdkEvent);
            });
            window.alert("Host Connected");
            _this.selectedHost = host;
            _this.GetContainer(_this.selectedHost);
            _this.GetImages(_this.selectedHost);
            //_this.stompClient.reconnect_delay = 2000;
        }, function (error:any) {
          console.log("errorCallBack -> " + error)
          window.alert("Connection Error");
          setTimeout(() => {
              _this._connect(host, topic);
          }, 5000);
        });
    };

    _disconnect() {
        if (this.stompClient !== null) {
            this.stompClient.disconnect();
        }
        delete this.selectedHost;
        console.log("Disconnected");
        window.alert("Host Disconnected");
    }

    
	/**
	 * Send message to sever via web socket
	 * @param {*} message
	 */
    _send(message: any, url: string) {
        console.log("calling logout api via web socket");
        this.stompClient.send(url, {}, JSON.stringify(message));
    }

    onMessageReceived(message: any) {
        console.log("Message Recieved from Server :: " + message);
        this.logs += message.body;
    }
  }
