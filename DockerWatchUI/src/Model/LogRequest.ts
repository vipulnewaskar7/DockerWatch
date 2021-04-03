
import { Container } from "./Container";
import { Host } from "./Host";

export class LogRequest {
    host: Host;
    container: Container;
    constructor(host: Host, container: Container){
        this.host = host;
        this.container = container;
    }
}