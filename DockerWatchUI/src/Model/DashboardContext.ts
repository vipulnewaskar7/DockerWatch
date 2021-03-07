import {Image} from './Image';
import {Container} from './Container';
import { Host } from './Host';
import { HTTPService } from 'src/Services/HttpService';
import { MessagePattern } from './MessagePattern';
import { MessageStatus } from './MessageStatus';
import { HostLogs } from './HostLogs';
import { AppConfig } from 'src/app/app.config';

export class DashboardContext{
    hosts: Host[];
    Images: Image[];
    Containers: Container[];

    selectedHost: Host;
    selectedImage: Image;
    selectedContainer: Container;

    EditBoxHost: Host = new Host();

    logs: string;

    httpService:HTTPService;
  
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
        HostLogs.DisconnectHost();
        this.logs = "";
      } else {
        this.selectedHost = host;
        this.logs = "";
        HostLogs.ConnectHost(this.UpdateLogs);
        //this.GetContainer();
        this.GetImages(this.selectedHost);
      }
    }
    UpdateLogs(message: any){
      this.logs += message;
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
        console.log(JSON.stringify(images));
        this.Images = images;
      });
    }
  
    async GetContainer() {
      this.httpService.get<Container[]>(AppConfig.Address.GetContainers).subscribe(containers => {
        console.log(JSON.stringify(containers));
        this.Containers = containers;
        this.selectedContainer = this.Containers[0];
      });
    }
  }