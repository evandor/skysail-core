import { Bundle } from '../domain/Bundle';

export class BundleDetails {
    desc: Bundle;
    lastModification: Number;
    state: Number = -1;
    description = ""
    lastModified = "n/a"
    bundleClasspath = "n/a"
    location = "n/a"
    vendor = "n/a"
}