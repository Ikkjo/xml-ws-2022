import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing/app-routing.module';

import { AppComponent } from './app.component';
import { CopyrightRequestFormComponent } from './forms/copyright-request-form/copyright-request-form.component';

@NgModule({
  declarations: [
    AppComponent,
    CopyrightRequestFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
