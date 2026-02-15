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

  getQuestions(filters?: {
    examType: string;
    examName: string;
    subject: string;
    questionType: string;
    difficulty: string;
  }): Observable<Question[]> {
    const mockurl = './../../../assets/mock/assessment/mock-assessment-questions.json';
    return this.http.get<Question[]>(mockurl);

    // let params = new HttpParams();
    // if (filters?.examType) {
    //   params = params.set('examType', filters.examType);
    // }
    // if (filters?.examName) {
    //   params = params.set('examName', filters.examName);
    // }
    // if (filters?.subject) {
    //   params = params.set('subject', filters.subject);
    // }
    // if (filters?.questionType) {
    //   params = params.set('questionsType', filters.questionType);
    // }
    // if (filters?.difficulty) {
    //   params = params.set('difficulty', filters.difficulty);
    // }

    // return this.http.get<Question[]>(`${environment.apiUrl}/qbs/questions`, { params });
  }

  getV1Questions(filters?: {
    examType: string;
    examName: string;
    subject: string;
    questionType: string;
    difficulty: string;
  }, assessmentLanguage?: string): Observable<any> {
    console.log("QuestionServiceService:assessmentLanguage: ", assessmentLanguage);
    const mockurl = './../../../assets/mock/assessment/mock-assessment-i18n-questions.json';
    return this.http.get<Question[]>(mockurl);
    // let params = new HttpParams();
    // if (filters?.examType) {
    //   params = params.set('examType', filters.examType);
    // }
    // if (filters?.subject) {
    //   params = params.set('subjects', filters.subject);
    // }
    // if (filters?.examName) {
    //   params = params.set('examName', filters.examName);
    // }
    // if (filters?.questionType) {
    //   params = params.set('questionsType', filters.questionType);
    // }
    // if (filters?.difficulty) {
    //   params = params.set('difficulty', filters.difficulty);
    // }
    // if (assessmentLanguage) {
    //   params = params.set('assessmentLanguage', assessmentLanguage);
    // }
    // if (assessmentLanguage === 'en-hi') {
    //   params = params.set('lang1', 'en');
    //   params = params.set('lang2', 'hi');
    // } else {
    //   params = params.set('lang1', assessmentLanguage ?? 'en');
    // }
    // return this.http.get<Question[]>(`${environment.apiUrl}/qbs/v1/questions`, { params });
  }

  submitAssessment(payload: AssessmentSubmissionPayload) {
    return this.http.post<AssessmentSubmissionPayload>(`${environment.apiUrl}/qbs/assessment/submit`, payload);
  }

  getResult(userId: string, assessmentId: string, attemptNo: number): Observable<AnswerSubmission[]> {
    const mockurl = './../../../assets/mock/assessment/mock-assessment-results.json';
    return this.http.get<AnswerSubmission[]>(mockurl);
    // return this.http.get<AnswerSubmission[]>(`${environment.apiUrl}/qbs/assessment/result/${userId}/${assessmentId}/${attemptNo}`);
  }

  getResultByAssessmentId(userId: string, assessmentId: string, attemptNo: number): Observable<any> {
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
    return this.http.get<any>(`${environment.apiUrl}/qbs/assessment/submittedAnswerDetail`, { params });
  }

  getAllResults(): Observable<any[]> {
    const mockurl = './../../../assets/mock/assessment/mock-assessment-resultsDashboard.json';
    return this.http.get<any[]>(mockurl);
    // return this.http.get<any[]>(`${environment.apiUrl}/qbs/assessment/submissions`);
  }

}
