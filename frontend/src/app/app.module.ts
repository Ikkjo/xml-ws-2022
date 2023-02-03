import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { MaterialModule } from './material/material.module';

import { AppComponent } from './app.component';
import { CopyrightRequestFormComponent } from './forms/copyright-request-form/copyright-request-form.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PatentRequestFormComponent } from './forms/patent-request-form/patent-request-form.component';

@NgModule({
  declarations: [
    AppComponent,
    CopyrightRequestFormComponent,
    PatentRequestFormComponent
  ],
  imports: [
    MaterialModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
