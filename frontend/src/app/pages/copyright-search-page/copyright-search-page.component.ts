import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { SolutionModalComponent } from 'src/app/components/solution-modal/solution-modal.component';
import { CopyrightRequest } from 'src/app/dto/Copyright/CopyrightRequestDTO';
import { Individual, LegalEntity } from 'src/app/dto/User';
import { CopyrightService } from 'src/app/services/copyright.service';
import { xmlToObject } from 'src/app/util/XmlUtil';
import { getSession } from 'src/app/util/context';
import { runInThisContext } from 'vm';

@Component({
  selector: 'app-copyright-search-page',
  templateUrl: './copyright-search-page.component.html',
  styleUrls: ['./copyright-search-page.component.css']
})
export class CopyrightSearchPageComponent {

    public copyrightSearchForm!: FormGroup;
    displayedColumns: string[] = [];
    citizenDisplayedColumns: string[] = ["requestNumber", "requestSubmissionDate", "applicant", "mainTitle", "accepted"]
    officialDisplayedColumns: string[] = ["requestNumber", "requestSubmissionDate", "applicant", "mainTitle", "accepted", "downloadDocument", "downloadMetadata", "solution"];
    copyrightRequestSource: CopyrightRequest[] = [];
    searchQuery: string = "";
    role: string = "";
    blob: Blob = new Blob();
    applicationNumber: string = '';
    showModal: boolean = false;

    constructor(
        private fb: FormBuilder,
        private service: CopyrightService,
        private router: Router,
        public dialog: MatDialog
    ) {}

    ngOnInit(): void {
        const session = getSession();
        if (session !== undefined) this.role = session.role;
        else this.role = '';

        if (this.role.toUpperCase() === "SLUZBENIK") {
            this.displayedColumns = this.officialDisplayedColumns
        } else {
            this.displayedColumns = this.citizenDisplayedColumns;
        }

        this.service.getAllRequests().subscribe({
            next: data => {
                xmlToObject(data).then((result: any) => {

                    console.log(result)

                    let requests: CopyrightRequest[] = [];

                    if (result.List.item.length > 1) {
                        requests = result.List.item
                    } else {
                        requests.push(result.List.item)
                    }

                    requests.forEach(request => {
                        request.requestSubmissionDate = new Date(Number(request.requestSubmissionDate)).toLocaleDateString();
                    });
                    
                    this.copyrightRequestSource = requests

                    console.log(this.copyrightRequestSource);
                })
            }
        })

        this.copyrightSearchForm = this.fb.group({
            searchQuery: ['']
        })
    }

    getApplicant(index: number): string {
        let request = this.copyrightRequestSource.at(index)
        if (request) {
                    if(request.applicantLegalEntity) return request.applicantLegalEntity.businessName
                    else return request.applicantIndividual.firstName + " " + request.applicantIndividual.lastName
        } else {
            return "";
        }
    }

    getAccepted(index: number): string {
        return this.copyrightRequestSource.at(index)?.accepted ? "ДА" : "НЕ";
    }

    search() {
        

    }

    toggleModal(requestNumber: string){
        console.log(requestNumber)
        const dialogRef = this.dialog.open(SolutionModalComponent, {
            data: {requestNumber: requestNumber, type: "copyright"},
          });
      
          dialogRef.afterClosed().subscribe(result => {
            console.log('The dialog was closed');
          });
    }
    downloadJson(applicationNumber: string) {
        this.service.getRequestJsonById(applicationNumber).subscribe({
            next: (data) => {
                console.log(data)
                this.blob = new Blob([JSON.stringify(data)], { type: 'application/json' });
                var downloadURL = window.URL.createObjectURL(this.blob);
                window.open(downloadURL)
            }});
    }
    downloadRdf(applicationNumber: string) {
        this.service.getRequestRdfById(applicationNumber).subscribe({
            next: (data) => {
                console.log(data)
                this.blob = new Blob([data], { type: 'application/xml' });
                var downloadURL = window.URL.createObjectURL(this.blob);
                window.open(downloadURL)
            }
        });
    }
    downloadPdf(applicationNumber: string) {
        this.service.getRequestPdfById(applicationNumber).subscribe({
            next: (data) => {
                if(data) {
                    this.blob = new Blob([data], { type: 'application/pdf' });
                    var downloadURL = window.URL.createObjectURL(data);
                    window.open(downloadURL)
                }
                console.log(data)
            }
        });
    }
    downloadHtml(applicationNumber: string) {
        this.service.getRequestHtmlById(applicationNumber).subscribe({
            next: (data) => {
                        this.blob = new Blob(["\ufeff", data], { type: 'text/html' });
                        var downloadURL = window.URL.createObjectURL(this.blob);
                        window.open(downloadURL)
            }
        });
    }
}
