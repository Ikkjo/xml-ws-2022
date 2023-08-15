import {Component, DoCheck, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-alert-modal',
  templateUrl: './alert-modal.component.html',
  styleUrls: ['./alert-modal.component.css']
})
export class AlertModalComponent implements DoCheck {

  @Input() show = false;
  @Output() showChange: EventEmitter<boolean> = new EventEmitter<boolean>();

  ngDoCheck() {
    this.showChange.next(this.show);
  }

  toggleModal(){
    this.show = !this.show;
    this.showChange.emit(this.show);
  }
}
