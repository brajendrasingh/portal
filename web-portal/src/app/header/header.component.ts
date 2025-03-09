import { Component } from '@angular/core';
import { AuthService } from './../core/services/auth.service';

@Component({
  selector: 'app-header',
  standalone: false,
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  isLoggedIn = false; // Track login status
  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.isLoggedIn = this.authService.isLoggedIn();
  }
  logout() {
    this.authService.logout();
    this.isLoggedIn = false; // Update UI after logout
  }
}
