import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CopyrightRequestFormComponent } from '../forms/copyright-request-form/copyright-request-form.component';
import {PatentRequestFormComponent} from "../forms/patent-request-form/patent-request-form.component";
import { LoginPageComponent } from '../pages/login-page/login-page.component';
import { RegsitrationPageComponent } from '../pages/regsitration-page/regsitration-page.component';
import {PatentReportComponent} from "../pages/patent-report/patent-report.component";
import {PatentSearchComponent} from "../pages/patent-search/patent-search.component";

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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
