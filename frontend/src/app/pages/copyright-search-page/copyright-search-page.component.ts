import { Component } from '@angular/core';
import { CopyrightRequestForm } from 'src/app/dto/CopyrightRequestFormDTO';

@Component({
  selector: 'app-copyright-search-page',
  templateUrl: './copyright-search-page.component.html',
  styleUrls: ['./copyright-search-page.component.css']
})
export class CopyrightSearchPageComponent {
    displayedColumns: string[] = ["requestNumber", "requestSubmissionDate", "applicant", "accepted"];
    copyrightRequestSource: CopyrightRequestForm[] = [];
    searchQuery: string = "";
    search() {
        }
}
