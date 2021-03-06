import {Image} from './Image';
import {Container} from './Container';
import { Host } from './Host';
import { HTTPService } from 'src/Services/HttpService';
import { MessagePattern } from './MessagePattern';
import { MessageStatus } from './MessageStatus';
import { stat } from 'fs';

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

    ConnectHost(host: Host){
      if (this.selectedHost.id === host.id) {
        host.DisconnectHost(host);
      } else {
        host.ConnectHost();
        this.selectedHost = host;
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
      let response = this.httpService.post<MessagePattern<MessageStatus>>("https://rahul.jedhe.in/api/dockerwatch/savehost.php", request);
      response.then(data => {
        console.log(data);
      });
    }

    SelectContainer(container: Container) {
      this.selectedContainer = container;
    }
  
    async GetImages() {
      this.httpService.get<Image[]>("http://localhost:8080/api/v1/images").subscribe(images => {
        console.log(JSON.stringify(images));
        this.Images = images;
      });
    }
  
    async GetContainer() {
      this.httpService.get<Container[]>("http://localhost:8080/api/v1/containers").subscribe(containers => {
        console.log(JSON.stringify(containers));
        this.Containers = containers;
        this.selectedContainer = this.Containers[0];
      });
    }
  }