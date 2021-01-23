import { Component } from '@angular/core';
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

  constructor(){
    this.connectedHost = this.hosts[0];
  }

  ConnectHost(host: Host) {
    if(this.connectedHost.id === host.id){
      this.connectedHost = this.defaultHost;
    } else {
      this.connectedHost = host;
    }
  }

}
