import { HttpClient, HttpHeaders, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginForm } from '../dto/LoginForm';
import { RegistrationForm } from '../dto/RegistrationForm';
import { SystemUserDTO } from '../dto/SystemUserDTO';
import { TokenDTO } from '../dto/TokenDTO';

const apiUrl = "http://localhost:8989/authorization";
const xmlContentHeader = new HttpHeaders().set('Content-Type', 'application/xml');

const endpoints = {
    login: apiUrl + "/login",
    logout: apiUrl + "/logout",
    register: apiUrl + "/register",
    getUserFromToken: apiUrl + "/getUser/"
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  public login(loginForm: LoginForm): Observable<HttpResponse<string>>{
    return this.http.post<HttpResponse<string>>(endpoints.login, loginForm, { headers: xmlContentHeader });
  }

  public register(registrationFormXml: string): Observable<HttpResponse<String>> {
    return this.http.post<HttpResponse<String>>(endpoints.login, registrationFormXml, { headers: xmlContentHeader });
  }

  public getLoggedInUser(): SystemUserDTO {
    return {
        email: "mail@example.com",
        firstName: "John",
        lastName: "Doe",
        role: "official"
    };
  }

}
