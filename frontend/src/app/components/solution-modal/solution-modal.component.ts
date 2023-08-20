import { Component, Inject, Input} from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CopyrightService } from 'src/app/services/copyright.service';
import { PatentRequestService } from 'src/app/services/patentRequestService';
import { PatentSolutionService } from 'src/app/services/patentSolutionService';
import { objectToXML } from 'src/app/util/XmlUtil';
import { getSession } from 'src/app/util/context';

export interface DialogData {
    requestNumber: string;
    type: string;
}

@Component({
  selector: 'app-solution-modal',
  templateUrl: './solution-modal.component.html',

  styleUrls: ['./solution-modal.component.css']
})
export class SolutionModalComponent {
    public motivation: string = "";
    public accepted: boolean = false;
    session: any;

    public constructor(
        public dialogRef: MatDialogRef<SolutionModalComponent>,
        @Inject(MAT_DIALOG_DATA) public data: DialogData,
        private copyrightService: CopyrightService,
        private patentService: PatentSolutionService
    ) { }

    ngOnInit() {
        this.session = getSession()
    }

    postSolution(): any {

        console.log("POST")

        if (this.data.type.toUpperCase() === "COPYRIGHT") {
            console.log("POST COPYRIGHT")
            this.postCopyrightSolution()
        } else if (this.data.type.toUpperCase() === "PATENT") {
            // TODO: finish
        }
    }

    postCopyrightSolution() {

        console.log({
            requestNumber: this.data.requestNumber,
            accepted: this.accepted,
            official: {
                firstName: this.session.firstName,
                lastName: this.session.lastName
            },
            motivation: this.motivation,
            solutionDate: new Date().toISOString().substring(0, 10)
        })

        let solution = objectToXML({
            requestNumber: this.data.requestNumber,
            accepted: this.accepted,
            official: {
                firstName: this.session.firstName,
                lastName: this.session.lastName
            },
            motivation: this.motivation,
            solutionDate: new Date().toISOString().substring(0, 10)
        }, "copyrightRequestSolution")

        console.log(solution)

        this.copyrightService.createSolution(solution).subscribe({
            next: (response) => {

                console.log(response);                

                this.onCloseClick()
            },
            error: (response) => {
                console.log(response);
            }

        })
    }

    

    onCloseClick() {
        this.dialogRef.close();
    }
}
