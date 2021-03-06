import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { HTTPService } from 'src/Services/HttpService';
import { Host } from 'src/Model/Host';
import { MessagePattern } from 'src/Model/MessagePattern';
import { DashboardContext } from 'src/Model/DashboardContext';
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

  constructor(public httpService: HTTPService) {
    this.DataContext = new DashboardContext([],[],[], httpService);

    this.Initialize();
  }

  Initialize(){
    let request = new MessagePattern<any>(null);
    let response = this.httpService.post<MessagePattern<Host[]>>(AppConfig.Address.GetAllHost, request);
    response.then(data => {
      this.DataContext.hosts = data.message;
    });
  }

 
  ngOnInit(): void {
  }
  

  Logout(){
    localStorage.setItem('user', "default" ); 
    localStorage.setItem('isLoggedIn', "false");
    this.login = false;
    this.loginChange.emit(this.login);
  }

}
