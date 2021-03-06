import { WebSocketAPI } from "src/app/WebSocketAPI";

export class Host {
  id: string;
  name: string;
  address: string;
  status: string;

  webSocketAPI: WebSocketAPI;

  GetLogs(){
    this.webSocketAPI = new WebSocketAPI(this.handleMessage);
  }

  DisconnectHost(host:Host){
    //this.DataContext.selectedHost = null;
    this.webSocketAPI._disconnect();
  }

  
  ConnectHost(){
    this.webSocketAPI._connect();
  }

  SendMessage(msg: any){
    this.webSocketAPI._send(msg);
  }

  handleMessage(message: any){
    console.log(message);
  }
}
