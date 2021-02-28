import {Image} from './Image';
import {Container} from './Container';

export class HostBase {
  id: string;
  name: string;
  address: string;
}

export class Host extends HostBase{
    status: string;
    Images: Image[];
    Containers: Container[];
  
    constructor(id: string, name: string, address: string, status: string, images: Image[], containers:Container[]) {
      super();
      this.id = id;
      this.name = name;
      this.address = address;
      this.status = status;
      this.Images = images;
      this.Containers = containers;
    }
  }