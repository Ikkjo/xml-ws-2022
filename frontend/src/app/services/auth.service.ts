import { HttpClient, HttpHeaders, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginForm } from '../dto/LoginForm';
import { RegistrationForm } from '../dto/RegistrationForm';
import { SystemUserDTO } from '../dto/SystemUserDTO';
import { TokenDTO } from '../dto/TokenDTO';

const apiUrl = "http://localhost:8989/api/authorization";
const xmlContentHeader = new HttpHeaders().set('Content-Type', 'application/xml');

const endpoints = {
    login: apiUrl + "/login",
    logout: apiUrl + "/logout",
    register: apiUrl + "/registration",
    getUserFromToken: apiUrl + "/get-user/"
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  public login(loginForm: string) {
    return this.http.post(endpoints.login, loginForm, { headers: xmlContentHeader, responseType:'text'});
  }

  public register(registrationFormXml: string) {
    return this.http.post(endpoints.register, registrationFormXml, { headers: xmlContentHeader, responseType:'text' });
  }

  public getLoggedInUser(token: string) {
    const authHeader = new HttpHeaders().set('Authorization', token).set('Content-Type', 'application/xml');
    return this.http.get(endpoints.getUserFromToken, {headers: authHeader, responseType:'text'});
  }
}
