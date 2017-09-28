export class Service {
    id: string;
    objectClass: string;
    pid: string;
    ranking: number;
    //bundle: Bundle = new Bundle();
    bundleId: number;
    properties: Map<string, string>;
    usingBundles: {};

    constructor(id: string, name: string) {
        this.id = id;
        this.objectClass = "objectClass";
    }
}