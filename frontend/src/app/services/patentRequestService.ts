import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {
  ApplicationInformation, DeliveryAddress,
  DeliveryType, EarlierApplication, EarlierApplications, InformationForInstitution, Inventor,
  PatentName, PriorityRightsRecognitionFromEarlierApplications, Proxy,
  RequestForPatentRecognitionDTO
} from "../dto/RequestForPatentRecognitionDTO";
import * as xml2js from 'xml2js';
import {Address, Person} from "../dto/User";

const url = "http://localhost:4201/api/patent/";

@Injectable({
  providedIn: 'root'
})
export class PatentRequestService {

  constructor(private http: HttpClient) { }

  public getLinkedDocument(applicationNumber: string): Observable<string> {
    return this.http.get(url + applicationNumber + "/linked-document", {headers: new HttpHeaders().set('Content-Type', 'application/xml'), responseType: 'text'})
  }

  public getJson(applicationNumber: string): Observable<string> {
    return this.http.get(url + applicationNumber + "/rdf", {headers: new HttpHeaders().set('Content-Type', 'application/xml'), responseType: 'text'})
  }

  public getRdf(applicationNumber: string): Observable<string> {
    return this.http.get(url + applicationNumber + "/rdf", {headers: new HttpHeaders().set('Content-Type', 'application/xml'), responseType: 'text'})
  }

  public getPdf(applicationNumber: string): Observable<Blob> {
    return this.http.get<Blob>(url + applicationNumber + "/pdf", {headers: new HttpHeaders().set('Content-Type', 'application/xml'), responseType: 'blob' as 'json'})
  }

  public getHtml(applicationNumber: string): Observable<string> {
    return this.http.get(url + applicationNumber + "/html", {headers: new HttpHeaders().set('Content-Type', 'application/xml'), responseType: 'text'})
  }

  public addRequest(request: string) : Observable<string> {
    console.log(request);
    return this.http.post(url + 'create', request, { headers: new HttpHeaders().set('Content-Type', 'application/xml'), responseType: 'text' });
  }

  public searchMetadata(condition: string) : RequestForPatentRecognitionDTO[] {
    let requestsForPatentRecognition : RequestForPatentRecognitionDTO[] = [];
    const xmlResponse = this.http.get(url + 'search/metadata/' + condition, {headers: new HttpHeaders().set('Content-Type', 'application/xml'), responseType: 'text'})
      .subscribe(data => {
        const parser = new xml2js.Parser({strict: true, trim: true});
        parser.parseString(data.toString(), (err, result) => {
          console.log(result);
          let requests = result.List.item;
          for (var req of requests) {
            let requestForPatentRecognition : RequestForPatentRecognitionDTO;
            requestForPatentRecognition = this.convertResponseToRequest(req);
            requestsForPatentRecognition.push(requestForPatentRecognition);
          }
        })
      })
    return requestsForPatentRecognition;
  }

  public searchContent(condition: string) : RequestForPatentRecognitionDTO[] {
    let requestsForPatentRecognition : RequestForPatentRecognitionDTO[] = [];
    const xmlResponse = this.http.get(url + 'search/content/' + condition, {headers: new HttpHeaders().set('Content-Type', 'application/xml'), responseType: 'text'})
      .subscribe(data => {
        const parser = new xml2js.Parser({strict: true, trim: true});
        parser.parseString(data.toString(), (err, result) => {
          console.log(result);
          let requests = result.List.item;
          for (var req of requests) {
            let requestForPatentReconition : RequestForPatentRecognitionDTO;
            requestForPatentReconition = this.convertResponseToRequest(req);
            requestsForPatentRecognition.push(requestForPatentReconition);
          }
        })
      })
    return requestsForPatentRecognition;
  }

  public getAllRequest() : RequestForPatentRecognitionDTO[] {
    let requestsForPatentRecognition : RequestForPatentRecognitionDTO[] = [];
    const xmlResponse = this.http.get(url + 'all', {headers: new HttpHeaders().set('Content-Type', 'application/xml'), responseType: 'text'})
      .subscribe(data => {
        const parser = new xml2js.Parser({strict: true, trim: true});
        parser.parseString(data.toString(), (err, result) => {
          console.log(result);
          let requests = result.List.item;
          for (var req of requests) {
            let requestForPatentReconition : RequestForPatentRecognitionDTO;
            requestForPatentReconition = this.convertResponseToRequest(req);
            requestsForPatentRecognition.push(requestForPatentReconition);
          }
        })
      })
    return requestsForPatentRecognition;
  }

  public convertResponseToRequest(item : any) : RequestForPatentRecognitionDTO {

    const informationForInstitution : InformationForInstitution = {
      applicationNumber : item.informationForInstitution[0].applicationNumber[0],
      receiptDate : item.informationForInstitution[0].receiptDate[0],
      submissionDate : item.informationForInstitution[0].submissionDate[0]
    }

    const applicationInformation : ApplicationInformation = {
      originalApplicationNumber : item.applicationInformation[0].originalApplicationNumber,
      originalApplicationSubmissionDate : item.applicationInformation[0].originalApplicationSubmissionDate,
      separateApplication : item.applicationInformation[0].separateApplication,
      supplementaryApplication : item.applicationInformation[0].supplementaryApplication
    }

    const patentName : PatentName = {
      serbianPatentName : item.patentName[0].serbianPatentName,
      englishPatentName : item.patentName[0].englishPatentName
    }

    const applicantAddress : Address = {
      street : item.applicant[0].street[0],
      streetNumber: item.applicant[0].streetNumber[0],
      city: item.applicant[0].city[0],
      zip: item.applicant[0].zip[0]
    }

    const applicant : Person = {
      email : item.applicant[0].email[0],
      phoneNumber : item.applicant[0].phoneNumber[0],
      faxNumber : item.applicant[0].faxNumber[0],
      address : applicantAddress
    }

    const deliveryType : DeliveryType = {
      electronicDelivery : item.deliveryType[0].electronicDelivery[0],
      deliveryInPaperForm : item.deliveryType[0].deliveryInPaperForm[0]
    }

    const inventorAddress : Address = {
      street : item.inventor[0].street[0],
      streetNumber: item.inventor[0].streetNumber[0],
      city: item.inventor[0].city[0],
      zip: item.inventor[0].zip[0]
    }

    const inventor : Inventor = {
      email : item.inventor[0].email[0],
      phoneNumber : item.inventor[0].phoneNumber[0],
      faxNumber : item.inventor[0].faxNumber[0],
      address : inventorAddress,
      firstName : item.inventor[0].firstName[0],
      lastName : item.inventor[0].lastName[0],
      doesNotWantToBeListed : item.inventor[0].doesNotWantToBeListed[0] === 'true'
    }

    const proxyAddress : Address = {
      street : item.proxy[0].street[0],
      streetNumber: item.proxy[0].streetNumber[0],
      city: item.proxy[0].city[0],
      zip: item.proxy[0].zip[0]
    }

    const proxy : Proxy = {
      email : item.proxy[0].email[0],
      phoneNumber : item.proxy[0].phoneNumber[0],
      faxNumber : item.proxy[0].faxNumber[0],
      address : proxyAddress,
      firstName : item.proxy[0].firstName[0],
      lastName : item.proxy[0].lastName[0],
      proxyForRepresentation : item.proxy[0].proxyForRepresentation[0] === 'true',
      attorneyForReceivingLetters : item.proxy[0].attorneyForReceivingLetters[0] === 'true'
    }

    const deliveryAddress : DeliveryAddress = {
      street : item.deliveryAddress[0].street[0],
      streetNumber : item.deliveryAddress[0].streetNumber[0],
      city : item.deliveryAddress[0].city[0],
      zip : item.deliveryAddress[0].zip[0]
    }

    const earlierApplication : EarlierApplication[] = [];

    for (let r of item.priorityRightsRecognitionFromEarlierApplications) {
      const request : EarlierApplication = {
        earlierApplicationSubmissionDate : r.earlierApplicationSubmissionDate,
        earlierApplicationNumber : r.earlierApplicationNumber,
        countryOrOrganizationDesignation : r.countryOrOrganizationDesignation
      }
      earlierApplication.push(request);
    }

    const earlierApplications : EarlierApplications = {
      earlierApplication : earlierApplication
    }

    const priorityRightsRecognitionFromEarlierApplications : PriorityRightsRecognitionFromEarlierApplications = {
      earlierApplications : earlierApplications
    }
    const requestForPatentRecognition : RequestForPatentRecognitionDTO = {

      informationForInstitution: informationForInstitution,
      patentName: patentName,
      applicant: applicant,
      inventor: inventor,
      proxy: proxy,
      deliveryAddress: deliveryAddress,
      deliveryType: deliveryType,
      applicationInformation: applicationInformation,
      priorityRightsRecognitionFromEarlierApplications: priorityRightsRecognitionFromEarlierApplications,
      address: item.address[0],
      institution: item.institution[0],
      isAccepted: item.isAccepted[0]
    }

    return requestForPatentRecognition;
  }
}
