import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import{DashboardComponent} from'./dashboard/dashboard.component';
import{AuthGuard} from'./core/services/auth.guard';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { AddUserComponent } from './add-user/add-user.component';
import { ManageUsersComponent } from './manage-users/manage-users.component';
import { AssessmentComponent } from './assessment/assessment.component';
import { FileUploadComponent } from './file-upload/file-upload.component';
import { ResultComponent } from './result/result.component';
import { AssessmentResultsDashboardComponent } from './assessment-results-dashboard/assessment-results-dashboard.component';

const routes: Routes = [
  { path: '', redirectTo: '/signup', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'profile', component: UserProfileComponent },
  { path: 'addUser', component: AddUserComponent },
  { path: 'dashboard', component: DashboardComponent , canActivate: [AuthGuard]},
  { path: 'manageUser', component: ManageUsersComponent },
  { path: 'assessment', component: AssessmentComponent },
  { path: 'result', component: ResultComponent },
  { path: 'resultsDashboard', component: AssessmentResultsDashboardComponent },
  { path: 'fileUpload', component: FileUploadComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
