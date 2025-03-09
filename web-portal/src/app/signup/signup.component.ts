import { Component } from '@angular/core';
import { AuthService } from './../core/services/auth.service'; // Import AuthService
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  standalone: false,
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  user = { email: '', password: '', confirmPassword: '' };
  passwordMismatch = false;

  constructor(private authService: AuthService, private router: Router) {}

  signup() {
    if (this.user.password !== this.user.confirmPassword) {
      this.passwordMismatch = true;
      return;
    }

    this.passwordMismatch = false;
    this.authService.signup(this.user).subscribe(response => {
      console.log('Signup successful', response);
      this.router.navigate(['/login']);
    }, error => {
      console.error('Signup failed', error);
    });
  }
}
