import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppsComponent}     from './apps/apps.component';
import { BundlesComponent } from './bundles/bundles.component'
import { DashboardComponent } from './dashboard/dashboard.component'
import { GenericComponent } from './generic/generic.component'
import { MaincontentComponent} from './maincontent/maincontent.component'

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'apps', component: AppsComponent },
  { path: 'bundles', component: BundlesComponent },
  { path: 'services', component: AppsComponent },
  { path: 'maincontent', component: MaincontentComponent, 
    children: [
      {
        path: 'sub',
        component: AppsComponent,
      },
      {
        path: 'sub2',
        component: DashboardComponent,
      },
      {
        path: 'generic',
        component: GenericComponent,
      },
      {
        path: '',
        redirectTo: 'sub',
        pathMatch: 'full'
      }
    ] 
  },
  { path: '**', component: GenericComponent}
];
 
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}