export class Container {
    Id: string;
    Image: string;
    Status: string;
    State: string;
    Names: string[];
    constructor(id: string, image: string, status: string, state: string, name: string[]) {
        this.Id = id;
        this.Image = image;
        this.State = state;
        this.Status = status;
        this.Names = name;
    }
}
