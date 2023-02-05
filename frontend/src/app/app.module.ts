import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { MaterialModule } from './material/material.module';

import { AppComponent } from './app.component';
import { CopyrightRequestFormComponent } from './forms/copyright-request-form/copyright-request-form.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PatentRequestFormComponent } from './forms/patent-request-form/patent-request-form.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { RegsitrationPageComponent } from './pages/regsitration-page/regsitration-page.component';
import { MainNavBarComponent } from './components/main-nav-bar/main-nav-bar.component';
import { AuthService } from './services/auth.service';
import { HttpClientModule } from '@angular/common/http';
import {MatMenuModule} from "@angular/material/menu";
import { PatentReportComponent } from './pages/patent-report/patent-report.component';
import { PatentSearchComponent } from './pages/patent-search/patent-search.component';

@NgModule({
  declarations: [
    AppComponent,
    CopyrightRequestFormComponent,
    PatentRequestFormComponent,
    LoginPageComponent,
    RegsitrationPageComponent,
    MainNavBarComponent,
    PatentReportComponent,
    PatentSearchComponent,
  ],
  imports: [
    MaterialModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatMenuModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
