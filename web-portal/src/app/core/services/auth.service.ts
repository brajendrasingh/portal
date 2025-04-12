import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
   constructor(private http: HttpClient, private router: Router) {}

  login(email: string, password: string): Observable<any> {
    //const mockurl = './../../../assets/mock/user/mock-user.json';
    return this.http.post(`${environment.apiUrl}/auth/login`, { email, password });
  }

  signup(user: any) {
    //const mockurl = './../../../assets/mock/user/mock-user.json';
    return this.http.post(`${environment.apiUrl}/auth/signup`, user);
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

  isLoggedIn() {
    return !!localStorage.getItem('token');
  }
}
