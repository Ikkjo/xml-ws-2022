import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CopyrightRequestFormComponent } from '../forms/copyright-request-form/copyright-request-form.component';
import {PatentRequestFormComponent} from "../forms/patent-request-form/patent-request-form.component";
import { CopyrightSearchPageComponent } from '../pages/copyright-search-page/copyright-search-page.component';
import { LoginPageComponent } from '../pages/login-page/login-page.component';
import { RegsitrationPageComponent } from '../pages/regsitration-page/regsitration-page.component';
import {PatentReportComponent} from "../pages/patent-report/patent-report.component";
import {PatentSearchComponent} from "../pages/patent-search/patent-search.component";
import { CopyrightReportPageComponent } from '../pages/copyright-report-page/copyright-report-page.component';

const routes: Routes = [
  {
    path: 'copyright/request-form',
    component: CopyrightRequestFormComponent,
  },
  {
    path: 'patent/request-form',
    component: PatentRequestFormComponent,
  },
  {
    path: 'login',
    component: LoginPageComponent
  },
  {
    path: 'register',
    component: RegsitrationPageComponent
  },
  {
    path: 'patent/report',
    component: PatentReportComponent
  },
  {
    path: 'patent/search',
    component: PatentSearchComponent
  },
  {
    path: 'copyright/search',
    component: CopyrightSearchPageComponent
  },
  {
    path: 'copyright/report',
    component: CopyrightReportPageComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: "reload"})],
  exports: [RouterModule]
})

export class AppRoutingModule { }
