import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {}

  getUserProfile(): Observable<any> {
    return this.http.get(`${environment.apiUrl}/user/profile`);
  }

  addUser(data: any[]): Observable<any> {
    return this.http.put(`${environment.apiUrl}/user/addUser`, data);
  }

  updateUserProfile(data: any): Observable<any> {
    return this.http.put(`${environment.apiUrl}/user/update`, data);
  }

  getAllUsers(): Observable<any> {
    return this.http.get(`${environment.apiUrl}/user/getAllUsers`);
  }

}
