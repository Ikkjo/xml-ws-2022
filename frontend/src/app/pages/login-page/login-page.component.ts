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
  import { HttpClient, HttpResponse } from '@angular/common/http';
  import { Observable } from 'rxjs';
  import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { TokenDTO } from 'src/app/dto/TokenDTO';

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
  styleUrls: ['./login-page.component.css']
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

        console.log({
            email: this.loginForm.value?.email,
            password: this.loginForm.value?.password,
        })

        // TODO: post login request

        this.authService.login({
            email: this.loginForm.value?.email,
            password: this.loginForm.value?.password,
        }).subscribe({
            next: (token) => {
                this.handleSuccessfulLogin(token)
            },
            error: (error) => {
                console.log(error);
            }
        })

		this.loginForm.reset();
    }
  }

  handleSuccessfulLogin(response: HttpResponse<TokenDTO>): void {

    console.log(response)

    if(response.body) {
        localStorage.setItem('token', response.body.token);
        this.router.navigate(['/']);
    } else {
        console.log(response)
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
