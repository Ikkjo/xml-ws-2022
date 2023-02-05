import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import * as js2xml from "js2xmlparser";

const url = "http://localhost:4200/api/solution/";

@Injectable({
  providedIn: 'root'
})
export class PatentSolutionService {

  constructor(private http: HttpClient) { }

  getReport(startDate: string, endDate: string): Observable<Blob> {
    return this.http.get<Blob>(url + "report/" + startDate + "/" + endDate, {headers: new HttpHeaders().set('Content-Type', 'application/xml'), responseType: 'blob' as 'json'});
  }

  public getHtml(applicationNumber: string): Observable<string> {
    return this.http.get(url + applicationNumber + "/html", {headers: new HttpHeaders().set('Content-Type', 'application/xml'), responseType: 'text'});
  }

  public addSolution(solution: Object) : void {
    var xmlSolution = js2xml.parse("solutionForPatentDTO", solution);
    console.log(xmlSolution);
    const xmlResponse = this.http.post(url + 'create', xmlSolution, { headers: new HttpHeaders().set('Content-Type', 'application/xml'), responseType: 'text' });
    xmlResponse.subscribe();
  }

}
