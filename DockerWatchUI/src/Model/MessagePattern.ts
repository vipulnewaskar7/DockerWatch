export class MessagePattern<T>{
    Id: string;
    Name: string;
    Catagory: Catagory;
    Message: T;

    constructor(Id: string, Name:string, Catagory: Catagory, Message: T){
        this.Id = Id;
        this.Name = Name;
        this.Catagory = Catagory;
        this.Message = Message;
    }
}

const enum Catagory{
    Notification,
    Logs
}