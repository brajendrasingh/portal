import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
   constructor(private http: HttpClient, private router: Router) {}

  login(email: string, password: string): Observable<any> {
    // return this.http.post('http://localhost:8080/about', { email, password });

    const mockurl = './../../../assets/mock/user/mock-user.json';
    return this.http.post(mockurl, { email, password });
  }

  signup(user: any) {
    const mockurl = './../../../assets/mock/user/mock-user.json';
    return this.http.post(mockurl, user);
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

  isLoggedIn() {
    return !!localStorage.getItem('token');
  }
}
