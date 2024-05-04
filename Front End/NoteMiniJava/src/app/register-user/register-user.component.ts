
import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RegistrationService } from '../Service/registration.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent {

  user = {
    username: '',
    email: '',
    password: ''
  };

  registrationError: string = '';

  constructor(private registrationService: RegistrationService, private router: Router) { }

  register() {
    this.registrationService.register(this.user).subscribe(
      (response) => {
        console.log('Registration successful:', response);
        this.router.navigate(['/login']);
      },
      (error) => {
        this.registrationError = error.error.message;
        console.log("ERROR", error);
      }
    );
  }
}


