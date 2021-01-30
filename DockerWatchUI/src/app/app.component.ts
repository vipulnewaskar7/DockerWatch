import { Component } from '@angular/core';
import { Container } from 'src/Model/Container';
import { Image } from 'src/Model/Image';
import { Host } from 'src/Model/Host';
import { HTTPService } from 'src/Services/HttpService';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'DockerWatchUI';

  hosts: Host[];

  connectedHost: Host;
  defaultHost: Host = new Host("-", "-", "-", "-",[],[]);

  selectedContainers: Container;

  constructor(public httpService: HTTPService) {
    
    this.hosts = [
      new Host("1", "Local_Host", "tcp://localhost:2375", "Last updated 3 mins ago", [], [])
    ];
    this.connectedHost = this.hosts[0];
    this.selectedContainers = this.connectedHost.Containers[0];
    this.GetImages();
  }

  ConnectHost(host: Host) {
    if (this.connectedHost.Id === host.Id) {
      this.connectedHost = this.defaultHost;
    } else {
      this.connectedHost = host;
    }
  }

  SelectContainer(container: Container) {
    this.selectedContainers = container;
  }

  async GetImages(){
    this.httpService.get<Image[]>("http://localhost:8080/api/v1/images").subscribe(images => {
      console.log(JSON.stringify(images));
      this.hosts[0].Images = images;
    });
  }

  async GetContainer(){
    this.httpService.get<Container[]>("http://localhost:8080/api/v1/containers").subscribe(containers => {
      console.log(JSON.stringify(containers));
      this.hosts[0].Containers = containers;
    });
  }

}
