import {Component, OnInit} from '@angular/core';
import {PatentRequestService} from "../../services/patentRequestService";
import {XonomyService} from "../../services/xonomy";
declare const Xonomy: any;

@Component({
  selector: 'app-xonomy-form',
  templateUrl: './xonomy-form.component.html',
  styleUrls: ['./xonomy-form.component.css']
})
export class XonomyFormComponent implements OnInit {
  constructor
  (
    private xonomyService: XonomyService,
    private requestService: PatentRequestService
  ) {}

  showAlert: boolean = false;

  toggleAlert(){
    this.showAlert = !this.showAlert;
  }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    let element = document.getElementById("editor");
    let specification = this.xonomyService.requestForPatentRecognitionSpecification;
    let xmlString = "<zahtevZaPriznanjePatenta></zahtevZaPriznanjePatenta>";
    Xonomy.render(xmlString, element, specification);
  }

  submit() {
    let text = Xonomy.harvest();
    console.log(text);
    this.requestService.addRequest(text).subscribe(
      data => { console.log(data); },
      error => { console.log(error); this.toggleAlert(); }
    );
  }
}
