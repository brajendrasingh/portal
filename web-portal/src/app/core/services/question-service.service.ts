import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Question } from '../../core/models/question.model';
import { AnswerSubmission } from '../../core/models/answerSubmission.model';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class QuestionServiceService {

  constructor(private http: HttpClient) { }

  getQuestions(): Observable<Question[]> {
    const mockurl = './../../../assets/mock/assessment/mock-assessment-questions.json';
    return this.http.get<Question[]>(mockurl);
    // return this.http.get<Question[]>(`${environment.apiUrl}/qbs/questions`);
  }

  submitAssessment(payload: AnswerSubmission[]) {
    return this.http.post<AnswerSubmission[]>(`${environment.apiUrl}/qbs/assessment/submit`, payload);
  }

  getResults(requestPayload: any): Observable<AnswerSubmission[]> {
    const mockurl = './../../../assets/mock/assessment/mock-assessment-results.json';
    return this.http.get<AnswerSubmission[]>(mockurl);
    // return this.http.get<AnswerSubmission[]>(`${environment.apiUrl}/qbs/assessment/result`);
  }

  getAllResults(): Observable<any[]> {
    const mockurl = './../../../assets/mock/assessment/mock-assessment-resultsDashboard.json';
    return this.http.get<any[]>(mockurl);
    // return this.http.get<any[]>(`${environment.apiUrl}/qbs/assessment/allResults`);
  }

}
