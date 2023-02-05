import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PatentRequestService} from "../../services/patentRequestService";
import * as js2xml from "js2xmlparser";

interface PersonType {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-patent-request-form',
  templateUrl: './patent-request-form.component.html',
  styleUrls: ['./patent-request-form.component.css']
})
export class PatentRequestFormComponent implements OnInit {

  public patentRequestForm!: FormGroup;

  types: PersonType[] = [
    {value: 'individual', viewValue: 'Физичко лице'},
    {value: 'legal', viewValue: 'Правно лице'},
  ];

  ngOnInit() {
    this.patentRequestForm = this.fb.group({
      serbianPatentName: ['', Validators.required],
      englishPatentName: ['', Validators.required],
      applicantType: [''],
      applicantFirstName: ['', Validators.required],
      applicantLastName: ['', Validators.required],
      applicantEmail: ['', Validators.required, Validators.email],
      applicantPhoneNumber: ['', Validators.required],
      applicantFaxNumber: ['', Validators.required],
      applicantStreet: ['', Validators.required],
      applicantStreetNumber: ['', Validators.required],
      applicantZipCode: ['', Validators.required],
      applicantCity: ['', Validators.required],
      applicantCountry: ['', Validators.required],
      applicantCitizenship: ['', Validators.required],
      doesNotWantToBeListed: [false],
      inventorFirstName: ['', Validators.required],
      inventorLastName: ['', Validators.required],
      inventorEmail: ['', Validators.required, Validators.email],
      inventorPhoneNumber: ['', Validators.required],
      inventorFaxNumber: ['', Validators.required],
      inventorStreet: ['', Validators.required],
      inventorStreetNumber: ['', Validators.required],
      inventorZipCode: ['', Validators.required],
      inventorCity: ['', Validators.required],
      inventorCountry: ['', Validators.required],
      proxyForRepresentation: [false],
      proxyForReceivingLetters: [false],
      proxyFirstName: ['', Validators.required],
      proxyLastName: ['', Validators.required],
      proxyEmail: ['', Validators.required, Validators.email],
      proxyPhoneNumber: ['', Validators.required],
      proxyStreet: ['', Validators.required],
      proxyStreetNumber: ['', Validators.required],
      proxyZipCode: ['', Validators.required],
      proxyCity: ['', Validators.required],
      street: ['', Validators.required],
      streetNumber: ['', Validators.required],
      zipCode: ['', Validators.required],
      city: ['', Validators.required],
      electronically: [false],
      inPaperForm: [false],
      supplementaryApplication: [false],
      separateApplication: [false],
      applicationNumber: [''],
      submissionDate: [null],
      earlierSubmissionDate: [null],
      earlierApplicationNumber: [''],
      countryOrOrganizationDesignation: [''],

    });
  }

  constructor(private fb: FormBuilder, private ps: PatentRequestService) {};

  submitForm() {
    var xmlRequest = js2xml.parse("RequestForPatentRecognition", this.patentRequestForm.value);
    this.ps.addRequest(xmlRequest).subscribe(
      data => { console.log(data); },
      error => { console.log(error); }
    );
  }
}
