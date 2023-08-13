import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import { Router } from '@angular/router';
import {PatentRequestService} from "../../services/patentRequestService";
import {RequestForPatentRecognitionDTO} from "../../dto/RequestForPatentRecognitionDTO";
import * as xml2js from 'xml2js';
import { getSession } from 'src/app/util/context';

@Component({
  selector: 'app-patent-search',
  templateUrl: './patent-search.component.html',
  styleUrls: ['./patent-search.component.css']
})
export class PatentSearchComponent implements OnInit {

  public patentSearchForm!: FormGroup;
  requestsForPatentRecognition: RequestForPatentRecognitionDTO[] = [];
  blob: Blob = new Blob();
  role: string = '';
  applicationNumber: string = '';
  displayedColumns: string[] = ["requestNumber", "serbianPatentName", "englishPatentName", "applicant", "requestSubmissionDate"];
  searchQuery: string = "";
  showModal: boolean = false;


  constructor(private fb: FormBuilder, private rs: PatentRequestService, private router: Router) { }

  ngOnInit(): void {
    const session = getSession();
    if (session !== undefined) this.role = session.role;
    else this.role = '';
    this.requestsForPatentRecognition = this.rs.getAllRequest();
    this.patentSearchForm = this.fb.group({
      searchQuery : ['']
    });
  }

  downloadPdf(index: number) {
    const fileName = this.requestsForPatentRecognition[index].informationForInstitution.applicationNumber;
    this.rs.getPdf(fileName)
      .subscribe((data) => {

        this.blob = new Blob([data], {type: 'application/pdf'});

        var downloadURL = window.URL.createObjectURL(data);
        var link = document.createElement('a');
        link.href = downloadURL;
        link.download = "P-" + fileName + ".pdf";
        link.click();

      });
  }

  downloadHtml(index: number) {
    const fileName = this.requestsForPatentRecognition[index].informationForInstitution.applicationNumber;
    this.rs.getHtml(fileName)
      .subscribe((data) => {
        this.blob = new Blob([data], {type: 'text/html'});

        var downloadURL = window.URL.createObjectURL(this.blob);
        var link = document.createElement('a');
        link.href = downloadURL;
        link.download = "P-" + fileName + ".html";
        link.click();

      });
  }

  downloadRdf(index: number) {
    const fileName = this.requestsForPatentRecognition[index].informationForInstitution.applicationNumber;
    this.rs.getRdf(fileName)
      .subscribe((data) => {

        this.blob = new Blob([data], {type: 'application/xml'});

        var downloadURL = window.URL.createObjectURL(this.blob);
        var link = document.createElement('a');
        link.href = downloadURL;
        link.download = "P-" + fileName + ".rdf";
        link.click();

      });
  }

  downloadJson(index: number) {
    const fileName = this.requestsForPatentRecognition[index].informationForInstitution.applicationNumber;
    const jsonString = this.rs.getJson(fileName)
      .subscribe(data => {
        const parser = new xml2js.Parser({strict: true, trim: true});
        parser.parseString(data.toString(), (err, result) => {
          console.log(result);
          const jsonString = JSON.stringify(result);

          this.blob = new Blob([jsonString], {type: 'json'});

          var downloadURL = window.URL.createObjectURL(this.blob);
          var link = document.createElement('a');
          link.href = downloadURL;
          link.download = "P-" + fileName + ".json";
          link.click();
        })
      });
  }

  toggleModal(text: string){
    this.applicationNumber = text;
    this.showModal = !this.showModal;
  }

  /*displayDocument(index: number) {
    const fileName = this.requestsForPatentRecognition[index].informationForInstitution.applicationNumber;
    const url = this.router.serializeUrl(
      this.router.createUrlTree(['/preview', "zahtev-za-priznanje-patenta", fileName])
    );
    window.open(url, '_blank');
  }*/

}
