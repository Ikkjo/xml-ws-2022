import { HttpClient, HttpHeaders, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginForm } from '../dto/LoginForm';
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

  public login(loginForm: LoginForm): Observable<HttpResponse<TokenDTO>>{
    return this.http.post<HttpResponse<TokenDTO>>(endpoints.login, loginForm, { headers: xmlContentHeader });
  }
}
