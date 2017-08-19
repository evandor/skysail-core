import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppsComponent}     from './apps/apps.component';
import { BundlesComponent } from './bundles/bundles.component'
 
const routes: Routes = [
  { path: '', redirectTo: '/services', pathMatch: 'full' },
  { path: 'bundles', component: BundlesComponent },
  { path: 'services', component: AppsComponent }
];
 
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}