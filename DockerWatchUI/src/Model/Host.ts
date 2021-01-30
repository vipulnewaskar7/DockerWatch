import {Image} from './Image';
import {Container} from './Container';

export class Host {
    Id: string;
    Name: string;
    Address: string;
    Status: string;
    Images: Image[];
    Containers: Container[];
  
    constructor(id: string, name: string, address: string, status: string, images: Image[], containers:Container[]) {
      this.Id = id;
      this.Name = name;
      this.Address = address;
      this.Status = status;
      this.Images=images;
      this.Containers = containers;
    }
  }