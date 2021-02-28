import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MessagePattern } from 'src/Model/MessagePattern';
import { MessageStatus } from 'src/Model/MessageStatus';
import { HTTPService } from 'src/Services/HttpService';
import {UserDetails} from '../../Model/UserDetails';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Input() login: boolean;
  @Output() loginChange = new EventEmitter();

  userDetails: UserDetails = new UserDetails();

  constructor(public httpService: HTTPService) {
  }

  ngOnInit(): void { }

  onLogin(){
    let request = new MessagePattern<UserDetails>(this.userDetails);
    let response = this.httpService.post<MessagePattern<MessageStatus>>("https://rahul.jedhe.in/api/dockerwatch/login.php", request);
    response.then(data => {
      console.log(data);
      if(data.message.status.includes("success")){
        localStorage.setItem('user', this.userDetails.username ); 
        localStorage.setItem('isLoggedIn', "true");
        this.login = true;
        this.loginChange.emit(this.login);
      } else {
        alert(data.message.message);
      }
    });
  }

}
