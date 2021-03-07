import { Guid } from 'guid-typescript';

export class MessagePattern<T>{
    requestid:string;
    user: string;
    time:number;
    message:T;

    constructor(message: T){
        this.requestid = Guid.create().toString();
        var user = localStorage.getItem('user');
        if(!user){
            localStorage.setItem('user', "default");
            this.user = "default";
        } else {
            this.user = user;
        }
        this.time = Date.now();
        this.message = message;
    }
}
