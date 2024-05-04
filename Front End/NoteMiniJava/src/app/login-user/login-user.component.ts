import { Component } from '@angular/core';
import { LoginService } from '../Service/login.service'; // Import the login service
import { Router } from '@angular/router'; // Import the Router
import { TokenServiceService } from '../authGuard/token-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.css']
})
export class LoginUserComponent {
  user = {
    email: '',
    password: ''
  };

  loginError: string = '';

  constructor(
    private loginService: LoginService,
    private router: Router,
    private tokenService: TokenServiceService
  ) { }

  ngOnInit(): void {
    this.deleteToken();
  }

  deleteToken() {
    localStorage.removeItem('token');
  }

  login() {
    this.loginService.login(this.user).subscribe(
      (response) => {

        const newToken = this.tokenService.generateToken();
        localStorage.setItem('token', newToken);

        console.log('Login successful:', response);
        this.router.navigate(['/home']);
      },
      (error) => {
        this.loginError = error.error.message;
        console.error('Login failed:', error);
      }
    );
  }
}
