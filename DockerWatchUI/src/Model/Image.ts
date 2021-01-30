
export class Image {
    Created: string;
    Id: string;
    ParentId: string;
    RepoTags: string[];
    RepoDigests: string[];
    Size: string;
    VirtualSize: string;
    SharedSize: string;
    Labels;
    Containers: string;

    constructor(
        Created: string,
        Id: string,
        ParentId: string,
        RepoTags: string[],
        RepoDigests: string[],
        Size: string,
        VirtualSize: string,
        SharedSize: string,
        Labels: Label,
        Containers: string,
    ) {
        this.Created = Created;
        this.Id = Id;
        this.ParentId = ParentId;
        this.RepoTags = RepoTags;
        this.RepoDigests = RepoDigests;
        this.Size = Size;
        this.VirtualSize = VirtualSize;
        this.SharedSize = SharedSize;
        this.Labels = Labels;
        this.Containers = Containers;
    }

}

export class Label {
    maintainer: string;
    constructor(maintainer: string) {
        this.maintainer = maintainer;
    }
}