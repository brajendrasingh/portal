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
import { ResultComponent } from './result/result.component';
import { AssessmentResultsDashboardComponent } from './assessment-results-dashboard/assessment-results-dashboard.component';
import { AssessmentSubjectSelectionComponentComponent } from './assessment-subject-selection-component/assessment-subject-selection-component.component';
import { AssessmentContainerComponent } from './assessment/assessment-container/assessment-container.component';
import { QuestionAreaComponent } from './assessment/question-area/question-area.component';

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
    ResultComponent,
    AssessmentResultsDashboardComponent,
    AssessmentSubjectSelectionComponentComponent,
    AssessmentContainerComponent,
    QuestionAreaComponent
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
