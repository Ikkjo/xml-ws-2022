import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CopyrightSolution } from '../dto/Copyright/CopyrightSolutionDTO';
import { CopyrightRequest } from '../dto/Copyright/CopyrightRequestDTO';
import { Individual, Person } from '../dto/User';
import { xmlToObject } from '../util/XmlUtil';

const apiUrl = 'http://localhost:9999/api/copyright';
const xmlContentHeader = new HttpHeaders().set(
  'Content-Type',
  'application/xml'
);

const endpoints = {
  createSolution: apiUrl + '/solution', // POST
  getAllSolutions: apiUrl + '/solution/all', // GET
  getSolution: apiUrl + '/solution/', // GET
  getAllRequests: apiUrl + '/request/all', // GET
  createRequest: apiUrl + '/request', // POST
  searchRequestsByContent: apiUrl + '/request/search/', // GET
  searchRequestsByMetadata: apiUrl + '/request/search/metadata/', // GET
  getRequestById: apiUrl + '/request/', // GET
  getCopyrightReport: apiUrl + '/report', // GET
};

@Injectable({
  providedIn: 'root',
})
export class CopyrightService {
  constructor(private http: HttpClient) {}

  public createSolution(
    solution: String
  ): Observable<HttpResponse<String>> {
    return this.http.post<HttpResponse<String>>(
      endpoints.createSolution,
      solution,
      { headers: xmlContentHeader }
    );
  }

  public getSolution(id: string): Observable<HttpResponse<String>> {
    return this.http.get<HttpResponse<String>>(endpoints.getSolution + id, {
      headers: xmlContentHeader,
    });
  }

  public getAllSolutions(): Observable<HttpResponse<String>> {
    return this.http.get<HttpResponse<String>>(endpoints.getAllSolutions, {
      headers: xmlContentHeader,
    });
  }

  public getAllRequests() {
    return this.http.get(endpoints.getAllRequests, {
      headers: xmlContentHeader,
      responseType: "text"
    });
  }

  public getRequestById(id: string) {
    return this.http.get(endpoints.getRequestById + id, {
      headers: xmlContentHeader,
      responseType: 'text'
    });
  }

  public getRequestHtmlById(id: string) {
    return this.http.get(endpoints.getRequestById + id + "/html", {
      headers: xmlContentHeader,
      responseType: 'text'
    });
  }

  public getRequestPdfById(id: string) {
    return this.http.get(endpoints.getRequestById + id + "/pdf" , {
        headers: xmlContentHeader,
        responseType: "blob"
    });
  }

  public getRequestRdfById(id: string) {
    return this.http.get(endpoints.getRequestById + id + "/rdf", {
      headers: xmlContentHeader,
      responseType: "text"
    });
  }

  public getRequestJsonById(id: string) {
    return this.http.get(endpoints.getRequestById + id + "/json", {
      headers: xmlContentHeader,
      responseType: "json"
    });
  }

  public getLinkedDocumentByRequestId(id: string) {
    return this.http.get(endpoints.getRequestById + id, {
      headers: xmlContentHeader,
      responseType: "text"
    });
  }

  public getCopyrightReport(reportDateRange: HttpParams) {
    return this.http.get(endpoints.getCopyrightReport, {
      params: reportDateRange,
      headers: xmlContentHeader,
      responseType: "blob"
    });
  }

  public createCopyrightRequest(newCopyrightRequestXml: string) {
    return this.http.post<HttpResponse<String>>(endpoints.createRequest, newCopyrightRequestXml, { headers: xmlContentHeader} )
  }
}

// export function copyrightRequestXmlToObject(xml: string): CopyrightRequest | undefined {

//     xmlToObject(xml)

//     const applicant: Person = {
//         address: undefined,
//         email: '',
//         phoneNumber: '',
//         faxNumber: ''
//     }

//     const attorney: Individual = {
//         firstName: '',
//         lastName: '',
//         address: undefined,
//         email: '',
//         phoneNumber: '',
//         faxNumber: ''
//     }

//     const copyrightRequest: CopyrightRequest = {
//         applicant: applicant,
//         attorney: undefined,
//         authorPseudonym: "",
//         workTitle: "",
//         workType: "",
//         formOfRecordingWork: string,
//         adaptationWorkInfo?: AdaptationWorkInfo,
//         author?: Author,
//         isWorkMadeInBusinessRelationship: boolean,
//         wayOfUsingWork: string,
//         requestSubmissionDate?: Date,
//         requestNumber?: number,
//         institution?: string,
//         address?: string
//     }

//     return undefined;
// }

// export function copyrightRequestToXml(object: CopyrightRequest): string {
//     return "";
// }
