import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { HTTPService } from 'src/Services/HttpService';
import { DashboardContext } from 'src/Model/DashboardContext';

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

    this.DataContext.Initialize();
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
