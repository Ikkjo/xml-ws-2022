import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

interface PersonType {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-copyright-request-form',
  templateUrl: './copyright-request-form.component.html',
  styleUrls: ['./copyright-request-form.component.css']
})

export class CopyrightRequestFormComponent implements OnInit {

  public copyrightRequestForm!: FormGroup;

  public isAuthorAnonymous: boolean = false;

  ngOnInit() {

    this.copyrightRequestForm = this.fb.group({
      applicantBusinessName: ['', Validators.required],
      applicantName: ['', Validators.required],
      applicantSurname: ['', Validators.required],
      applicantEmail: ['', Validators.required, Validators.email],
      applicantPhoneNumber: ['', Validators.required],
      applicantStreet: ['', Validators.required],
      applicantStreetNumber: ['', Validators.required],
      applicantZipCode: ['', Validators.required],
      applicantCity: ['', Validators.required],
      attorneyName: ['', Validators.required],
      attorneySurname: ['', Validators.required],
      attorneyEmail: ['', Validators.required, Validators.email],
      attorneyPhoneNumber: ['', Validators.required],
      attorneyStreet: ['', Validators.required],
      attorneyStreetNumber: ['', Validators.required],
      attorneyZipCode: ['', Validators.required],
      attorneyCity: ['', Validators.required],
      isAuthorApplicant: [false],
      authorName: [''],
      authorSurname: [''],
      authorAnonymous: [false],
      authorPseudonym: [''],
      authorDateOfDeath: [null],
      workMainTitle: ['', Validators.required],
      workAlternativeTitle: [''],
      workFormOfRecording: ['', ],
      workType: ['', ],
      workWayOfUsing: ['', ],
      workMadeInBusinessRelationship: [false, ],
      originalWorkMainTitle: [''],
      originalWorkAlternativeTitle: [''],
      originalAuthorName: [''],
      originalAuthorSurname: [''],
      originalAuthorAnonymous: [false],
      originalAuthorPseudonym: [''],
      originalAuthorDateOfDeath: [null],
      derivativeWork: [false],
      applicantType: [''],
    });
  }

  types: PersonType[] = [
    {value: 'individual', viewValue: 'Физичко лице'},
    {value: 'legal', viewValue: 'Правно лице'},
  ];

  submitForm() {
    // TODO: send post request to server

    console.log(JSON.stringify(this.copyrightRequestForm));
  }

  constructor(private fb: FormBuilder) {};

  
}
