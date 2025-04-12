import { Component } from '@angular/core';
import { AuthService } from './../core/services/auth.service'; // Import AuthService
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit(): void {
    this.authService.login(this.email, this.password).subscribe(
      (response) => {
        if (response.status === "success") {
          // alert('Login successful!');
          // this.router.navigate(['/dashboard']);
          localStorage.setItem('token', response['accessToken']);
          this.router.navigate(['/dashboard']);
        } else {
          alert('Invalid credentials');
        }
      },
      (error) => {
        alert('Login failed. Please try again.');
      }
    );
  }

}
