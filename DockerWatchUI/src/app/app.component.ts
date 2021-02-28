import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  login: boolean;

  constructor(){
    var isLoggedIn = localStorage.getItem('isLoggedIn');
    
    if(isLoggedIn != null && isLoggedIn.includes("true")){
      this.login = true;
    } else {
      this.login = false;
    }
    
  }

}


