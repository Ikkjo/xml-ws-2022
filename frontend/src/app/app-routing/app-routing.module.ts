import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CopyrightRequestFormComponent } from '../forms/copyright-request-form/copyright-request-form.component';

const routes: Routes = [
  {
    path: 'copyright/request-form',
    component: CopyrightRequestFormComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
