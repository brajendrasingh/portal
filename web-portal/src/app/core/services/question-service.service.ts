import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Question } from '../../core/models/question.model';
import { AssessmentSubmissionPayload } from '../../core/models/assessmentSubmissionPayload.model';
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

  submitAssessment(payload: AssessmentSubmissionPayload) {
    return this.http.post<AssessmentSubmissionPayload>(`${environment.apiUrl}/qbs/assessment/submit`, payload);
  }

  getResult(userId: string, assessmentId: string, attemptNo: number): Observable<AnswerSubmission[]> {
    const mockurl = './../../../assets/mock/assessment/mock-assessment-results.json';
    return this.http.get<AnswerSubmission[]>(mockurl);
    // return this.http.get<AnswerSubmission[]>(`${environment.apiUrl}/qbs/assessment/result/${userId}/${assessmentId}/${attemptNo}`);
  }

  getResultByAssessmentId(userId: string, assessmentId: string, attemptNo: number): Observable<any[]> {
    let params = new HttpParams();
    if (assessmentId) {
      params = params.set('assessmentId', assessmentId);
    }
    if (userId) {
      params = params.set('userId', userId);
    }
    if (attemptNo !== null && attemptNo !== undefined) {
      params = params.set('attemptNo', attemptNo.toString());
    }
    return this.http.get<any[]>(`${environment.apiUrl}/qbs/assessment/submissions`, { params });
  }

  getAllResults(): Observable<any[]> {
    const mockurl = './../../../assets/mock/assessment/mock-assessment-resultsDashboard.json';
    return this.http.get<any[]>(mockurl);
    // return this.http.get<any[]>(`${environment.apiUrl}/qbs/assessment/submissions`);
  }

}
