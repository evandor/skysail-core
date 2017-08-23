import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppsComponent}     from './apps/apps.component';
import { BundlesComponent } from './bundles/bundles.component'
import { DashboardComponent } from './dashboard/dashboard.component'
import { GenericComponent } from './generic/generic.component'

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'apps', component: AppsComponent },
  { path: 'bundles', component: BundlesComponent },
  { path: 'services', component: AppsComponent },
  { path: '**', component: GenericComponent}
];
 
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}