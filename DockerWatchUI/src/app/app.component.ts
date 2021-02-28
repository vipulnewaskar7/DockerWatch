import { Component } from '@angular/core';
import { Container } from 'src/Model/Container';
import { Image } from 'src/Model/Image';
import { Host } from 'src/Model/Host';
import { HTTPService } from 'src/Services/HttpService';
import { WebSocketAPI } from './WebSocketAPI';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  login: boolean = false;

  addHostModel: Host = new Host("","","","",[],[]);

  hosts: Host[];

  logs: string = `START GUI WM_LBUTTONDOWN BUTTON BUTTON-1 myprog.p
START GUI WM_KILLFOCUS FILL-IN FILL-IN-1 myprog.p
START GUI WM_SETFOCUS BUTTON BUTTON-1 myprog.p
START GUI WM_LBUTTONUP BUTTON BUTTON-1 myprog.p
PUSH FOCUS-IN BUTTON BUTTON-1 myprog.p
PUSH ENTRY BUTTON BUTTON-1 myprog.p
PUSH LEAVE FILL-IN FILL-IN-1 myprog.p
Begin-Trigger "LEAVE OF FILL-IN FILL-IN-1" myprog.p
End-Trigger "LEAVE OF FILL-IN FILL-IN-1" 
PRGS LEAVE FILL-IN FILL-IN-1 return no-apply
POP LEAVE FILL-IN FILL-IN-1 myprog.p
START GUI WM_KILLFOCUS BUTTON BUTTON-1 myprog.p
START GUI WM_SETFOCUS FILL-IN FILL-IN-1 myprog.p
PRGS ENTRY BUTTON BUTTON-1 discarded
POP ENTRY BUTTON BUTTON-1 myprog.p
PRGS FOCUS-IN BUTTON BUTTON-1 discarded
POP FOCUS-IN BUTTON BUTTON-1 myprog.p`;

  connectedHost: Host;
  defaultHost: Host = new Host("-", "-", "-", "-", [], []);

  selectedContainers: Container;

  webSocketAPI: WebSocketAPI;
  
  connect(){
    this.webSocketAPI._connect();
  }

  disconnect(){
    this.webSocketAPI._disconnect();
  }

  sendMessage(){
    this.webSocketAPI._send("something");
  }

  handleMessage(message: any){
    console.log(message);
  }

  constructor(public httpService: HTTPService) {
    this.hosts = [
      new Host("1", "Local_Host", "tcp://localhost:2375", "Last updated 3 mins ago", [], [])
    ];
    this.connectedHost = this.hosts[0];
    this.selectedContainers = this.connectedHost.Containers[0];
    //this.GetImages();
    //this.GetContainer();
    this.webSocketAPI = new WebSocketAPI(this);
    this.connect();
  }

  EditSelectedHost(){
    this.addHostModel = this.connectedHost;
  }

  AddNewHost(){
    this.addHostModel = new Host("","","","",[],[]);
  }

  ConnectHost(host: Host) {
    if (this.connectedHost.Id === host.Id) {
      this.connectedHost = this.defaultHost;
    } else {
      this.connectedHost = host;
    }
  }

  SaveHost(){
    
  }

  Logout(){
    this.login = false;
  }

  SelectContainer(container: Container) {
    this.selectedContainers = container;
  }

  async GetImages() {
    this.httpService.get<Image[]>("http://localhost:8080/api/v1/images").subscribe(images => {
      console.log(JSON.stringify(images));
      this.hosts[0].Images = images;
    });
  }

  async GetContainer() {
    this.httpService.get<Container[]>("http://localhost:8080/api/v1/containers").subscribe(containers => {
      console.log(JSON.stringify(containers));
      this.hosts[0].Containers = containers;
      this.selectedContainers = this.connectedHost.Containers[0];
    });
  }


}


