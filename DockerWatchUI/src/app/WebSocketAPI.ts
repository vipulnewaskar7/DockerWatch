import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { DashboardComponent } from './dashboard/dashboard.component';


export class WebSocketAPI {
    webSocketEndPoint: string = 'http://localhost:8080/logs/';
    topic: string = "/subscribe/logs";
    stompClient: any;
    app: DashboardComponent;
    constructor(app: DashboardComponent){
        this.app = app;
    }

    _connect() {
        console.log("Initialize WebSocket Connection");
        let ws = new SockJS(this.webSocketEndPoint);
        this.stompClient = Stomp.over(ws);
        const _this = this;
        _this.stompClient.connect({}, function (frame: any) {
            _this.stompClient.subscribe(_this.topic, function (sdkEvent:any) {
                _this.onMessageReceived(sdkEvent);
            });
            //_this.stompClient.reconnect_delay = 2000;
        }, this.errorCallBack);
    };

    _disconnect() {
        if (this.stompClient !== null) {
            this.stompClient.disconnect();
        }
        console.log("Disconnected");
    }

    // on error, schedule a reconnection attempt
    errorCallBack(error: any) {
        console.log("errorCallBack -> " + error)
        setTimeout(() => {
            this._connect();
        }, 5000);
    }

	/**
	 * Send message to sever via web socket
	 * @param {*} message
	 */
    _send(message: any) {
        console.log("calling logout api via web socket");
        this.stompClient.send("/containerslogs", {}, JSON.stringify(message));
    }

    onMessageReceived(message: any) {
        console.log("Message Recieved from Server :: " + message);
        this.app.handleMessage(message);
    }
}
