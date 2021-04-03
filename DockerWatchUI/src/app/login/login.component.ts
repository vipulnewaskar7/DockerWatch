import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessagePattern } from 'src/Model/MessagePattern';
import { MessageStatus } from 'src/Model/MessageStatus';
import { HTTPService } from 'src/Services/HttpService';
import {UserDetails} from '../../Model/UserDetails';
import { AppConfig } from '../app.config';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  @Input() login: boolean;
  @Output() loginChange = new EventEmitter();


  loginForm: FormGroup;
    loading = false;
    submitted = false;

    constructor(
        public httpService: HTTPService,
        private formBuilder: FormBuilder,
    ) {
        
    }

    ngOnInit() {
        this.loginForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });
    }

    // convenience getter for easy access to form fields
    get f() { return this.loginForm.controls; }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.loginForm.invalid) {
            return;
        }

        this.loading = true;
        
        let request = new MessagePattern<UserDetails>({ username : this.f.username.value, password: this.f.password.value});
        this.httpService.post<MessagePattern<MessageStatus>>(AppConfig.Address.Login, request).subscribe(data => {
          if(data.message.status.includes("SUCCESS")){
            localStorage.setItem('user', data.message.message ); 
            localStorage.setItem('isLoggedIn', "true");
            this.login = true;
            this.loginChange.emit(this.login);
          } else {
            alert(data.message.message);
            this.loading = false;
          }
        });

    }

}
