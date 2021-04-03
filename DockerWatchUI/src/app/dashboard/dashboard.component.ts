import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { HTTPService } from 'src/Services/HttpService';
import { DashboardContext } from 'src/Model/DashboardContext';
import { Host } from 'src/Model/Host';
import { AppConfig } from '../app.config';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  @Input() login: boolean;
  @Output() loginChange = new EventEmitter();

  DataContext: DashboardContext;

  topic : string;

  constructor(public httpService: HTTPService) {
    this.DataContext = new DashboardContext([],[],[], httpService);

    this.DataContext.Initialize();
  }

 
  ngOnInit(): void {
    var user = localStorage.getItem('user');
    if(user){
      this.topic = AppConfig.Address.Subscribe.replace(".user.", user);
    }
  }
  

  ConnectHost(host: Host){
    this.DataContext.ConnectHost(host, this.topic);
  }

  Logout(){
    localStorage.setItem('user', "default" ); 
    localStorage.setItem('isLoggedIn', "false");
    this.login = false;
    this.loginChange.emit(this.login);
  }

}
