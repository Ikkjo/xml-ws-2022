import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-patent-report',
  templateUrl: './patent-report.component.html',
  styleUrls: ['./patent-report.component.css']
})
export class PatentReportComponent {

  public patentReportForm!: FormGroup;

  ngOnInit() {
    this.patentReportForm = this.fb.group({
      startDate: [null],
      endDate: [null]
    });
  }

  constructor(private fb: FormBuilder) {};
}
