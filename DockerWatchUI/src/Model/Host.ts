
export class Host {
    id: string;
    name: string;
    address: string;
    status: string;
  
    constructor(id: string, name: string, address: string, status: string) {
      this.id = id;
      this.name = name;
      this.address = address;
      this.status = status;
    }
  }