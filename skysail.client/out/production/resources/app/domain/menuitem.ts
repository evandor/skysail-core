
export class MenuItem {
    label: string;
    icon: string;
    routerLink: string;
    items: MenuItem[];

    constructor(label:string, icon:string, url:string) {
        this.label = label;
        this.icon = icon;
        this.routerLink = url;
    }
}