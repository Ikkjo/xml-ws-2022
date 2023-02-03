import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CopyrightRequestFormComponent } from '../forms/copyright-request-form/copyright-request-form.component';
import {PatentRequestFormComponent} from "../forms/patent-request-form/patent-request-form.component";

const routes: Routes = [
  {
    path: 'copyright/request-form',
    component: CopyrightRequestFormComponent,
  },
  {
    path: 'patent/request-form',
    component: PatentRequestFormComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
