export class Image {
    Created: string;
    Id: string;
    RepoTags: string[];
    Size: string;
    Labels: string[];
    Containers: string;
    constructor(Created: string, Id: string, RepoTags: string[], Size: string, Labels: string[], Containers: string) {
        this.Containers = Containers;
        this.Created = Created;
        this.RepoTags = RepoTags;
        this.Size = Size;
        this.Labels = Labels;
        this.Id = Id;
    }
}