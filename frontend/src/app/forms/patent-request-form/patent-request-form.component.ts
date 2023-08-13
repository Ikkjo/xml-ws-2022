import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators, FormArray} from "@angular/forms";
import {PatentRequestService} from "../../services/patentRequestService";
import * as js2xml from "js2xmlparser";
import {EarlierApplications} from "../../dto/RequestForPatentRecognitionDTO";

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
      patentName : this.fb.group({
        serbianPatentName: ['', Validators.required],
        englishPatentName: ['', Validators.required],
      }),
      applicant : this.fb.group({
        applicantType: [''],
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        email: ['', Validators.required],
        phoneNumber: ['', Validators.required],
        faxNumber: ['', Validators.required],
        address: this.fb.group({
          street: ['', Validators.required],
          streetNumber: ['', Validators.required],
          zipCode: ['', Validators.required],
          city: ['', Validators.required],
          country: ['', Validators.required],
        }),
        citizenship: ['', Validators.required],
      }),
      inventor : this.fb.group({
        doesNotWantToBeListed: [false],
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        email: ['', Validators.required],
        phoneNumber: ['', Validators.required],
        faxNumber: ['', Validators.required],
        address: this.fb.group({
          street: ['', Validators.required],
          streetNumber: ['', Validators.required],
          zipCode: ['', Validators.required],
          city: ['', Validators.required],
          country: ['', Validators.required],
        })
      }),
      proxy : this.fb.group({

        proxyForRepresentation: [false],
        proxyForReceivingLetters: [false],
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        email: ['', Validators.required],
        phoneNumber: ['', Validators.required],
        address: this.fb.group({
          street: ['', Validators.required],
          streetNumber: ['', Validators.required],
          zipCode: ['', Validators.required],
          city: ['', Validators.required],
          country: ['', Validators.required],
        })
      }),
      deliveryAddress: this.fb.group({
        street: ['', Validators.required],
        streetNumber: ['', Validators.required],
        zipCode: ['', Validators.required],
        city: ['', Validators.required]
      }),
      deliveryType: this.fb.group({
        electronicDelivery: [false],
        deliveryInPaperForm: [false]
      }),
      applicationInformation: this.fb.group({
        supplementaryApplication: [false],
        separateApplication: [false],
        originalApplicationNumber: [''],
        originalApplicationSubmissionDate: ['']
      }),
      earlierApplications: this.fb.group({
        earlierApplication : this.fb.array([])
      })
    });
  }

  get earlierApplications() {
    return (this.patentRequestForm.controls["earlierApplications"].get("earlierApplication") as FormArray);
  }

  addRequest() : void {
    const earlierApplications = this.fb.group({
      earlierApplication : this.fb.group({
        earlierSubmissionDate: [''],
        earlierApplicationNumber: [''],
        countryOrOrganizationDesignation: ['']
      })
    });
    this.earlierApplications.push(earlierApplications);
    console.log(this.patentRequestForm.value)
  }

  removeRequest(index: number) {
    this.earlierApplications.removeAt(index);
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
