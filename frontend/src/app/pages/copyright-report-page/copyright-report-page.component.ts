import { HttpParams } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CopyrightService } from 'src/app/services/copyright.service';

@Component({
  selector: 'app-copyright-report-page',
  templateUrl: './copyright-report-page.component.html',
  styleUrls: ['./copyright-report-page.component.css'],
})
export class CopyrightReportPageComponent {
  public copyrightReportForm!: FormGroup;
  blob!: Blob;

  ngOnInit() {
    this.copyrightReportForm = this.fb.group({
      startDate: [null],
      endDate: [null],
    });
  }

  constructor(private fb: FormBuilder, private service: CopyrightService) {}

  submitForm() {

    let start: Date = this.copyrightReportForm.get('startDate')?.value;

    let end: Date = this.copyrightReportForm.get('endDate')?.value;

    start.setDate(start.getDate() + 1);
    end.setDate(end.getDate() + 1);

    const httpQuery = new HttpParams()
    .set(
        "startDate",
        start.toISOString().substring(0, 10))
    .set(
        "endDate",
        end.toISOString().substring(0, 10));

    console.log(httpQuery);

    this.service
      .getCopyrightReport(httpQuery)
      .subscribe((data) => {
        if(data) {
            this.blob = new Blob([data], { type: 'application/pdf' });
            var downloadURL = window.URL.createObjectURL(data);
            window.open(downloadURL)
        }
      });
  }
}
