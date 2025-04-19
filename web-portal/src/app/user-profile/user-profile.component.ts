import { Component } from '@angular/core';
import { UserService } from '../core/services/user.service';
import { AuthService } from '../core/services/auth.service';

@Component({
  selector: 'app-user-profile',
  standalone: false,
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent {
  user: any = {};
  newPassword: string = '';

  constructor(private userService: UserService, private authService: AuthService) {}

  ngOnInit(): void {
    this.loadUserProfile();
  }

  loadUserProfile() {
    this.userService.getUserProfile().subscribe(
      (data) => {
        this.user = data;
      },
      (error) => {
        console.error('Error loading profile', error);
      }
    );
  }

  updateProfile() {
    const updatedData = {
      userId: this.user.userName, 
      userName: this.user.userName, 
      email: this.user.email,
      password: this.newPassword || undefined
    };

    this.userService.updateUserProfile(updatedData).subscribe(
      (response) => {
        alert('Profile updated successfully!');
        this.loadUserProfile();
      },
      (error) => {
        console.error('Error updating profile', error);
      }
    );
  }
}
