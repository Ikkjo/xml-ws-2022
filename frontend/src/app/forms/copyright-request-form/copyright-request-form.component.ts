import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

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

  copyrightRequestForm: any;

  types: PersonType[] = [
    {value: 'individual', viewValue: 'Физичко лице'},
    {value: 'legal', viewValue: 'Правно лице'},
  ];

  constructor(private fb: FormBuilder) { };

  ngOnInit() {
    this.copyrightRequestForm = this.fb.group({
      applicantEmail: ''
    });
  }

}
