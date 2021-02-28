import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { HTTPService } from 'src/Services/HttpService';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Input() login: boolean;
  @Output() loginChange = new EventEmitter();

  username: string;
  password: string;

  constructor(public httpService: HTTPService) { }

  ngOnInit(): void { }

  onLogin(){
    //this.httpService.post("","");
    this.login = true;
    this.loginChange.emit(this.login);
  }

}
