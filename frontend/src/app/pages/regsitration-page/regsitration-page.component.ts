import { XmlParser } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { objectToXML } from 'src/app/util/XmlUtil';

@Component({
  selector: 'app-regsitration-page',
  templateUrl: './regsitration-page.component.html',
  styleUrls: ['./regsitration-page.component.css']
})
export class RegsitrationPageComponent implements OnInit {

    registrationForm!: FormGroup;

    public constructor(
        private formBuilder: FormBuilder,
        private authService: AuthService
    ) { };

    ngOnInit(): void {
        this.registrationForm = this.formBuilder.group({
            firstName: ['', Validators.required],
            lastName: ['', Validators.required],
            email: ['', Validators.required, Validators.email],
            password: ['', Validators.required]
        });
    }

    register() {
        if (this.registrationForm.valid) {
            let form = this.registrationForm.value;
            form.role = "citizen";

            this.authService.register(objectToXML(form));
        }
    }

}
