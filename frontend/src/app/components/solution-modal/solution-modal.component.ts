import { Component, Input} from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { CopyrightService } from 'src/app/services/copyright.service';

@Component({
  selector: 'app-solution-modal',
  templateUrl: './solution-modal.component.html',
  styleUrls: ['./solution-modal.component.css']
})
export class SolutionModalComponent {
    @Input() requestNumber: string = "";
    @Input() type: string = "";
    motivation: string = "";
    accepted: boolean = false;


    public constructor(
        public dialogRef: MatDialogRef<SolutionModalComponent>,
        private copyrightService: CopyrightService
    ) { }

    postSolution(): any {
        
    }

    onCloseClick() {
        this.dialogRef.close();
    }
}
