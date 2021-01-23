import { Component } from '@angular/core';
import { Container } from 'src/Model/Container';
import { Host } from 'src/Model/Host';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'DockerWatchUI';

  hosts: Host[] = [
    new Host("1", "DevServer_001", "http://localhost:4200/", "Last updated 3 mins ago"),
    new Host( "2", "DevServer_002", "http://localhost:4200/", "Last updated 2 days ago")
  ];

  connectedHost: Host;
  defaultHost: Host = new Host("-","-","-","-");

  containers: Container[];
  selectedContainers: Container;

  constructor(){
    this.connectedHost = this.hosts[0];
    this.containers = [
      new Container("3727b0cdf479d","nginx:1.19.6-alpine","Up 14 seconds","running", ["/docker-demo_nginx_1"]),
      new Container("77cef2b220a09","demo/frontend","Up 18 seconds","running", ["/docker-demo_frontend_1"])
    ];
    this.selectedContainers = this.containers[0];
  }

  ConnectHost(host: Host) {
    if(this.connectedHost.id === host.id){
      this.connectedHost = this.defaultHost;
    } else {
      this.connectedHost = host;
    }
  }
  SelectContainer(container: Container) {
    this.selectedContainers = container;
  }

}
