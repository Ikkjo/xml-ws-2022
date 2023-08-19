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
        const parser = new xml2js.Parser({strict: true, trim: true, explicitArray: false});
        parser.parseString(data.toString(), (err, result) => {
          console.log(result);
          let requests = result.ArrayList.item;
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
        const parser = new xml2js.Parser({strict: true, trim: true, explicitArray: false});
        parser.parseString(data.toString(), (err, result) => {
          console.log(result);
          let requests = result.ArrayList.item;
          for (var req of requests) {
            let requestForPatentReconition : RequestForPatentRecognitionDTO;
            requestForPatentReconition = this.convertResponseToRequest(req);
            requestsForPatentRecognition.push(requestForPatentReconition);
          }
        })
      })
    return requestsForPatentRecognition;
  }

  public getAllRequest() {
    return this.http.get(url + 'all', {headers: new HttpHeaders().set('Content-Type', 'application/xml'), responseType: 'text'});
  }

  public convertResponseToRequest(item : any) : RequestForPatentRecognitionDTO {

    const informationForInstitution : InformationForInstitution = {
      applicationNumber : item.informationForInstitution.applicationNumber,
      receiptDate : new Date(Number(item.informationForInstitution.receiptDate)),
      submissionDate : new Date(Number(item.informationForInstitution.submissionDate))
    }

    const applicationInformation : ApplicationInformation = {
      originalApplicationNumber : item.applicationInformation.originalApplicationNumber,
      originalApplicationSubmissionDate : new Date(Number(item.applicationInformation.originalApplicationSubmissionDate)),
      separateApplication : item.applicationInformation.separateApplication,
      supplementaryApplication : item.applicationInformation.supplementaryApplication
    }

    const patentName : PatentName = {
      serbianPatentName : item.patentName.serbianPatentName,
      englishPatentName : item.patentName.englishPatentName
    }

    const applicantAddress : Address = {
      street : item.applicant.street,
      streetNumber: item.applicant.streetNumber,
      city: item.applicant.city,
      zip: item.applicant.zip
    }

    const applicant : Person = {
      email : item.applicant.email,
      phoneNumber : item.applicant.phoneNumber,
      faxNumber : item.applicant.faxNumber,
      address : applicantAddress
    }

    const deliveryType : DeliveryType = {
      electronicDelivery : item.deliveryType.electronicDelivery,
      deliveryInPaperForm : item.deliveryType.deliveryInPaperForm
    }

    const inventorAddress : Address = {
      street : item.inventor.street,
      streetNumber: item.inventor.streetNumber,
      city: item.inventor.city,
      zip: item.inventor.zip
    }

    const inventor : Inventor = {
      email : item.inventor.email,
      phoneNumber : item.inventor.phoneNumber,
      faxNumber : item.inventor.faxNumber,
      address : inventorAddress,
      firstName : item.inventor.firstName,
      lastName : item.inventor.lastName,
      doesNotWantToBeListed : item.inventor.doesNotWantToBeListed === 'true'
    }

    const proxyAddress : Address = {
      street : item.proxy.street,
      streetNumber: item.proxy.streetNumber,
      city: item.proxy.city,
      zip: item.proxy.zip
    }

    const proxy : Proxy = {
      email : item.proxy.email,
      phoneNumber : item.proxy.phoneNumber,
      faxNumber : item.proxy.faxNumber,
      address : proxyAddress,
      firstName : item.proxy.firstName,
      lastName : item.proxy.lastName,
      proxyForRepresentation : item.proxy.proxyForRepresentation === 'true',
      attorneyForReceivingLetters : item.proxy.attorneyForReceivingLetters === 'true'
    }

    const deliveryAddress : DeliveryAddress = {
      street : item.deliveryAddress.street,
      streetNumber : item.deliveryAddress.streetNumber,
      city : item.deliveryAddress.city,
      zip : item.deliveryAddress.zip
    }

    const earlierApplication : EarlierApplication[] = [];
    if (item.priorityRightsRecognitionFromEarlierApplications) {

        if (item.priorityRightsRecognitionFromEarlierApplications.earlierApplications.length > 1) {
            for (let r of item.priorityRightsRecognitionFromEarlierApplications.earlierApplications) {
              const request : EarlierApplication = {
                earlierApplicationSubmissionDate : new Date(Number(r.earlierApplicationSubmissionDate)),
                earlierApplicationNumber : r.earlierApplicationNumber,
                countryOrOrganizationDesignation : r.countryOrOrganizationDesignation
              }
              earlierApplication.push(request);
            }
        } else {
            const request : EarlierApplication = {
                earlierApplicationSubmissionDate : new Date(Number(item.priorityRightsRecognitionFromEarlierApplications.earlierApplications.earlierApplications.earlierApplicationSubmissionDate)),
                earlierApplicationNumber : item.priorityRightsRecognitionFromEarlierApplications.earlierApplications.earlierApplications.earlierApplicationNumber,
                countryOrOrganizationDesignation : item.priorityRightsRecognitionFromEarlierApplications.earlierApplications.earlierApplications.countryOrOrganizationDesignation
              }
              earlierApplication.push(request);
        }

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
      address: item.address,
      institution: item.institution,
      isAccepted: item.isAccepted
    }

    return requestForPatentRecognition;
  }
}
