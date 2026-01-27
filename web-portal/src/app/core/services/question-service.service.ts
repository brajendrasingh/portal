import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Question } from '../../core/models/question.model';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class QuestionServiceService {

  constructor(private http: HttpClient) { }

  getQuestions(): Observable<Question[]> {
    const mockurl = './../../../assets/mock/assessment/mock-assessment.json';
    return this.http.get<Question[]>(mockurl);
  }
}
