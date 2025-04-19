import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {}

  getUserProfile(): Observable<any> {
    return this.http.get(`${environment.apiUrl}/user/profile`);
  }

  addUser(data: any[]) {
    this.http.post(`${environment.apiUrl}/user/addUser`, data).subscribe(
      data => console.log("Add user response: " + JSON.stringify(data)),
      error => console.log("Add user api error: " + JSON.stringify(error))
    );
  }

  updateUserProfile(data: any): Observable<any> {
    return this.http.put(`${environment.apiUrl}/user/update`, data);
  }

  getAllUsers(): Observable<any> {
    return this.http.get(`${environment.apiUrl}/user/getAllUsers`);
  }

  deleteUser(userIds: string[]) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), body: userIds
    };
    this.http.delete(`${environment.apiUrl}/user/deleteUser`, httpOptions).subscribe(() => console.log("Delete user successful userId: " + userIds));
  }
}
