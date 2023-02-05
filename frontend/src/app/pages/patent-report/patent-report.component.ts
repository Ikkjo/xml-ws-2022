import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PatentSolutionService} from "../../services/patentSolutionService";

@Component({
  selector: 'app-patent-report',
  templateUrl: './patent-report.component.html',
  styleUrls: ['./patent-report.component.css']
})
export class PatentReportComponent {

  public patentReportForm!: FormGroup;
  blob!: Blob;

  ngOnInit() {
    this.patentReportForm = this.fb.group({
      startDate: [null],
      endDate: [null]
    });
  }

  constructor(private fb: FormBuilder, private ss: PatentSolutionService) {};

  submitForm() {
    this.ss.getReport(this.patentReportForm.get("startDate")?.value, this.patentReportForm.get("endDate")?.value)
      .subscribe((data) => {

        this.blob = new Blob([data], {type: 'application/pdf'});

        var downloadURL = window.URL.createObjectURL(data);
        var link = document.createElement('a');
        link.href = downloadURL;
        link.download = "report.pdf";
        link.click();

      });
  }
}
