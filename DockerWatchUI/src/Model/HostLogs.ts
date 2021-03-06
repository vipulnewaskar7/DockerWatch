import { WebSocketAPI } from "src/Services/WebSocket";

export class HostLogs {
    
  static webSocketAPI: WebSocketAPI;

  static DisconnectHost(){
    //this.DataContext.selectedHost = null;
    this.webSocketAPI._disconnect();
  }

  static ConnectHost(fn:Function){
    this.webSocketAPI = new WebSocketAPI(fn);
    this.webSocketAPI._connect();
  }

  static SendMessage(msg: any){
    this.webSocketAPI._send(msg);
  }
}