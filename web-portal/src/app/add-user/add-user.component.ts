import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserService } from '../core/services/user.service';

@Component({
  selector: 'app-add-user',
  standalone: false,
  templateUrl: './add-user.component.html',
  styleUrl: './add-user.component.css'
})
export class AddUserComponent {

  userForm: FormGroup;
  previewImage: string | ArrayBuffer | null = null;

  constructor(private fb: FormBuilder,  private userService: UserService) {
    this.userForm = this.fb.group({
      userName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      role: ['User', Validators.required],
      profileImage: [null]
    });
  }

  onFileChange(event: any) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        this.previewImage = reader.result;
      };
      reader.readAsDataURL(file);
    }
  }

  onSubmit() {
    if (this.userForm.valid) {
      console.log('User Data:', this.userForm.value);

  
      this.userService.addUser( [{
        userId: this.userForm.value.userName,        
        userName: this.userForm.value.userName,
        email: this.userForm.value.email
    }]);
      alert('User added successfully!');
      this.userForm.reset();
      this.previewImage = null;
    }
  }
}
