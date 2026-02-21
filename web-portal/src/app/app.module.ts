import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';
import { HomeComponent } from './home/home.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';  
import { AuthService } from './core/services/auth.service';
import { SignupComponent } from './signup/signup.component';  
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptorService } from './core/services/token-interceptor.service';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { AddUserComponent } from './add-user/add-user.component';
import { ManageUsersComponent } from './manage-users/manage-users.component';
import { AssessmentComponent } from './assessment/assessment.component';
import { FileUploadComponent } from './file-upload/file-upload.component';
import { AssessmentResultComponent } from './assessment/assessment-result/assessment-result.component';
import { AssessmentAllResultsDashboardComponent } from './assessment/assessment-all-results-dashboard/assessment-all-results-dashboard.component';
import { PreAssessmentSubjectSelectionComponent } from './assessment/pre-assessment-subject-selection/pre-assessment-subject-selection.component';
import { QuestionAreaComponent } from './assessment/question-area/question-area.component';
import { AssessmentInstructionsComponent } from './assessment/assessment-instructions/assessment-instructions.component';
import { SubmissionConfirmationComponent } from './assessment/submission-confirmation/submission-confirmation.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    AboutComponent,
    ContactComponent,
    HomeComponent,
    FooterComponent,
    LoginComponent,
    SignupComponent,
    DashboardComponent,
    UserProfileComponent,
    AddUserComponent,
    ManageUsersComponent,
    AssessmentComponent,
    FileUploadComponent,
    AssessmentResultComponent,
    AssessmentAllResultsDashboardComponent,
    PreAssessmentSubjectSelectionComponent,
    QuestionAreaComponent,
    AssessmentInstructionsComponent,
    SubmissionConfirmationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [AuthService,
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptorService, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
