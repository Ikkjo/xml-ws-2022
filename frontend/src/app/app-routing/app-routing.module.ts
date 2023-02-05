import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CopyrightRequestFormComponent } from '../forms/copyright-request-form/copyright-request-form.component';
import { CopyrightSearchPageComponent } from '../pages/copyright-search-page/copyright-search-page.component';
import { LoginPageComponent } from '../pages/login-page/login-page.component';
import { RegsitrationPageComponent } from '../pages/regsitration-page/regsitration-page.component';

const routes: Routes = [
  {
    path: 'copyright/request-form',
    component: CopyrightRequestFormComponent,
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
    path: 'copyright/search',
    component: CopyrightSearchPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
