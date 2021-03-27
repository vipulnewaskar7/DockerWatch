import {Image} from './Image';
import {Container} from './Container';
import { Host } from './Host';
import { HTTPService } from 'src/Services/HttpService';
import { MessagePattern } from './MessagePattern';
import { MessageStatus } from './MessageStatus';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { AppConfig } from 'src/app/app.config';

export class DashboardContext{
    hosts: Host[];
    Images: Image[];
    Containers: Container[];

    selectedHost: Host;
    selectedImage: Image;
    selectedContainer: Container;

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
      let request = new MessagePattern<any>(null);
      this.httpService.post<MessagePattern<Host[]>>(AppConfig.Address.GetAllHost, request).subscribe(data => {
        this.hosts = data.message;
      });
    }

    ConnectHost(host: Host){
      if (this.selectedHost && this.selectedHost.id === host.id) {
        this._disconnect();
      } else {
        this.selectedHost = host;
        this.GetContainer(this.selectedHost);
        this.GetImages(this.selectedHost);
        this._connect()
      }
    }
    EditSelectedHost(){
      this.EditBoxHost = this.selectedHost;
    }

    AddNewHost(){
      this.EditBoxHost = new Host();
    }

    SaveHost(){
      let request = new MessagePattern<Host>(this.EditBoxHost);
      this.httpService.post<MessagePattern<MessageStatus>>(AppConfig.Address.SaveHost, request).subscribe(data => {
        console.log(data);
      });
    }

    SelectContainer(container: Container) {
      this.selectedContainer = container;
    }

    async GetImages(host: Host) {
      this.httpService.post<Image[]>(AppConfig.Address.GetImages, host).subscribe(images => {
        this.Images = images;
      });
    }

    async GetContainer(host: Host) {
      this.httpService.post<Container[]>(AppConfig.Address.GetContainers, host).subscribe(containers => {
        this.Containers = containers;
        this.SelectContainer(this.Containers[0]);
      });
    }

    webSocketEndPoint: string = AppConfig.Address.GetLogs;
    topic: string = AppConfig.Address.LogTopic;
    stompClient: any;

    _connect() {
        console.log("Initialize WebSocket Connection");
        let ws = new SockJS(this.webSocketEndPoint);
        this.stompClient = Stomp.over(ws);
        const _this = this;
        _this.stompClient.connect({}, function (frame: any) {
            _this.stompClient.subscribe(_this.topic, function (sdkEvent:any) {
                _this.onMessageReceived(sdkEvent);
            });
            //_this.stompClient.reconnect_delay = 2000;
        }, this.errorCallBack);
    };

    _disconnect() {
        if (this.stompClient !== null) {
            this.stompClient.disconnect();
        }
        console.log("Disconnected");
    }

    // on error, schedule a reconnection attempt
    errorCallBack(error: any) {
        console.log("errorCallBack -> " + error)
        setTimeout(() => {
            this._connect();
        }, 5000);
    }

	/**
	 * Send message to sever via web socket
	 * @param {*} message
	 */
    _send(message: any) {
        console.log("calling logout api via web socket");
        this.stompClient.send(AppConfig.Address.SendLogMessage, {}, JSON.stringify(message));
    }

    onMessageReceived(message: any) {
        console.log("Message Recieved from Server :: " + message);
        this.logs += message.body;
    }
  }
