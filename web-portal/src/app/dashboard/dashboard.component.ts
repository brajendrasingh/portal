import { Component } from '@angular/core';
import { AuthService } from '../core/services/auth.service';
import { UserService } from '../core/services/user.service';

@Component({
  selector: 'app-dashboard',
  standalone: false,
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  users: any = {};
  constructor(private authService: AuthService, private userService: UserService,) {}

  logout() {
    this.authService.logout();
  }
  ngOnInit(): void {
    this.loadUserProfile();
  }

  loadUserProfile() {
    this.userService.getAllUsers().subscribe(
      (data) => {
        this.users = data;
      },
      (error) => {
        console.error('Error loading profile', error);
      }
    );
  }

}
