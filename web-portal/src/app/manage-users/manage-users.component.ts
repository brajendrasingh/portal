import { Component } from '@angular/core';
import { AuthService } from '../core/services/auth.service';
import { UserService } from '../core/services/user.service';

@Component({
  selector: 'app-manage-users',
  standalone: false,
  templateUrl: './manage-users.component.html',
  styleUrl: './manage-users.component.css'
})
export class ManageUsersComponent {
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

  deleteUser(userName: string) {
    if (confirm('Are you sure you want to delete this user?')) {
      this.userService.deleteUser([userName]);
    }
  }
}
