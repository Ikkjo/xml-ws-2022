import { Component } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  FormGroupDirective,
  NgForm,
  Validators,
} from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { TokenDTO } from 'src/app/dto/TokenDTO';
import { saveToken, saveSession } from 'src/app/util/context';
import { xmlToObject, objectToXML } from 'src/app/util/XmlUtil';
import { SystemUserDTO } from 'src/app/dto/SystemUserDTO';

/** Error when invalid control is dirty, touched, or submitted. */
export class EmailFormErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(
    control: FormControl | null,
    form: FormGroupDirective | NgForm | null
  ): boolean {
    const isSubmitted = form && form.submitted;
    return !!(
      control &&
      control.invalid &&
      (control.dirty || control.touched || isSubmitted)
    );
  }
}

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
})
export class LoginPageComponent {
  loginForm!: FormGroup;
  matcher: EmailFormErrorStateMatcher = new EmailFormErrorStateMatcher();

  constructor(
    private authService: AuthService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {}
  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    });
  }

  doLogin() {
    if (this.loginForm.valid) {
      let loginDto = objectToXML(
        {
          email: this.loginForm.value?.email,
          password: this.loginForm.value?.password,
        },
        'LoginDTO'
      );

      this.authService.login(loginDto).subscribe({
        next: (token) => {
          this.handleSuccessfulLogin(token);
        },
        error: (error) => {
          console.log(error);
        },
      });

      this.loginForm.reset();
    }
  }

  handleSuccessfulLogin(response: string): void {
    if (response) {
      xmlToObject(response)
        .then((result: any) => {
          saveToken(result.TokenDTO.token);
          this.authService.getLoggedInUser(result.TokenDTO.token).subscribe({
            next: (sessionContextXML) => {
              xmlToObject(sessionContextXML).then((result: any) => {
                saveSession(result.SystemUserDTO);
                this.router.navigate([this.router.url])
                this.router.navigate(['/']);
                window.location.replace("/")
              });
            },
            error: (error) => {
              console.log(error);
            },
          });
        })
        .catch((error: any) => {
          console.log(error);
        });
    }
  }

  getPasswordErrorMessage(): string {
    if (this.loginForm.controls['password'].hasError('required')) {
      return 'Password is required';
    }
    return '';
  }

  getEmailErrorMessage(): string {
    if (this.loginForm.controls['email'].hasError('required')) {
      return 'Email is required';
    } else if (this.loginForm.controls['email'].hasError('email')) {
      return 'Email is not valid';
    }
    return '';
  }
}
