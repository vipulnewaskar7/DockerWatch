import { Label } from "./Image";

export class Container {
    Command: string;
    Created: string;
    Id: string;
    Image: string;
    ImageID: string;
    Names: string[];
    Ports: Port[];
    Labels: Label;
    Status: string;
    State: string;

    constructor(
        Command: string,
        Created: string,
        Id: string,
        Image: string,
        ImageID: string,
        Names: string[],
        Ports: Port[],
        Labels: Label,
        Status: string,
        State: string
    ) {
        this.Command = Command;
        this.Created = Created;
        this.Id = Id;
        this.Image = Image;
        this.ImageID = ImageID;
        this.Names = Names;
        this.Ports = Ports;
        this.Labels = Labels;
        this.Status = Status;
        this.State = State;
    }
}

export class Port {
    IP: string;
    PrivatePort: string;
    PublicPort: string;
    Type: string;
    constructor(
        IP: string,
        PrivatePort: string,
        PublicPort: string,
        Type: string
    ) {
        this.IP = IP;
        this.PrivatePort = PrivatePort;
        this.PublicPort = PublicPort;
        this.Type = Type;
    }
}