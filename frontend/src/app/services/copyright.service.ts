import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CopyrightSolutionDTO } from '../dto/CopyrightSolutionDTO';

const apiUrl = "http://localhost:9999/api/copyright";
const xmlContentHeader = new HttpHeaders().set('Content-Type', 'application/xml');

const endpoints = {
    createSolution: apiUrl + "/solution",
    getAllSolutions: apiUrl + "/solution/all",
    getSolution: apiUrl + "/solution/{id}",
    
};

@Injectable({
  providedIn: 'root'
})
export class CopyrightService {

  constructor(private http: HttpClient) { }

  public createSolution(solution: CopyrightSolutionDTO): Observable<HttpResponse<String>> {
    return this.http.post<HttpResponse<String>>(endpoints.createSolution, solution, { headers: xmlContentHeader });
  }

}
