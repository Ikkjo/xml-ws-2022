import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { objectToXML } from 'src/app/util/XmlUtil';
import {MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-regsitration-page',
  templateUrl: './regsitration-page.component.html',
  styleUrls: ['./regsitration-page.component.css']
})
export class RegsitrationPageComponent implements OnInit {

    registrationForm!: FormGroup;

    public constructor(
        private formBuilder: FormBuilder,
        private authService: AuthService,
        private snackBarModule: MatSnackBarModule,
        private matSnackBar: MatSnackBar,
        private router: Router
    ) { };

    ngOnInit(): void {
        this.registrationForm = this.formBuilder.group({
            firstName: ['', Validators.required],
            lastName: ['', Validators.required],
            email: ['', Validators.email],
            password: ['', Validators.required]
        });
    }

    register() {
        console.log('register clicked!')
        if (this.registrationForm.valid) {
            let form = this.registrationForm.value;
            form.role = "citizen";
            console.log(form)
            console.log(objectToXML(form, "RegistrationDto"));
            this.authService.register(objectToXML(form, "RegistrationDto")).subscribe({
                next: (resp) => {
                    console.log(resp);
                    if (resp) {
                        this.router.navigate(['/']);
                        this.matSnackBar.open("Registration successful!", "Close");
                    }

                },
                error: (error) => {
                    console.log(error);
                    this.matSnackBar.open("Registration unsuccessful! Error: " + error.status, "Close")
                }
            }
            );
        }
    }

}
