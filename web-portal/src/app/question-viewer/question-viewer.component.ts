import { Component } from '@angular/core';
import { QuestionServiceService } from '../core/services/question-service.service';
import { Question } from '../core/models/question.model';

@Component({
  selector: 'app-question-viewer',
  standalone: false,
  templateUrl: './question-viewer.component.html',
  styleUrl: './question-viewer.component.css'
})
export class QuestionViewerComponent {
  // VIEW MODAL STATE
  viewMode: boolean = false;
  selectedQuestion: any = null;

  // DATA
  questions: any[] = [];
  filteredQuestions: any[] = [];
  paginatedQuestions: any[] = [];

  // FILTERS
  searchText = '';
  filterSubject = '';
  filterDifficulty = '';
  subjects: string[] = [];

  // PAGINATION
  currentPage = 1;
  pageSize = 5;

  // EDIT
  editMode: boolean = false;
  editableQuestion: Question | null = null;

  // LANGUAGE
  selectedLang = 'en';

  // SELECTION
  selectedIds: Set<number> = new Set<number>();

  constructor(private service: QuestionServiceService) { }

  ngOnInit(): void {
    this.load();
  }

  // ================= LOAD =================
  load() {
    this.service.getV1Questions().subscribe(res => {
      this.questions = res;
      this.filteredQuestions = res;
      this.subjects = Array.from(new Set(res.map((q: any) => q.subject as string).filter((s: string) => !!s)));
      this.currentPage = 1;
      this.updatePagination();
    });
  }

  // ================= DELETE =================
  delete(id: number) {
    if (confirm('Delete this question?')) {
      this.service.deleteQuestion(id).subscribe(() => this.load());
    }
  }

  deleteSelected() {
    if (!confirm('Delete selected questions?')) return;

    const ids = Array.from(this.selectedIds);
    ids.forEach(id => {
      this.service.deleteQuestion(id).subscribe();
    });
    this.selectedIds.clear();
    // reload once
    setTimeout(() => this.load(), 300);
  }

  // ================= EDIT ================= 
  edit(q: any) {
    this.editMode = true;
    this.editableQuestion = {
      ...q,
      questionText: q.translations.find((x: any) => (x.language || '').trim().toLowerCase() === this.selectedLang).questionText || '',
      answerOptions: q.translations.find((x: any) => (x.language || '').trim().toLowerCase() === this.selectedLang).options || [],
      explanation: q.translations.find((x: any) => (x.language || '').trim().toLowerCase() === this.selectedLang).explanation
    };
  }

  update() {
    if (this.editableQuestion) {
      this.service.updateQuestion(this.editableQuestion)
        .subscribe(() => {
          this.editMode = false;
          this.editableQuestion = null;
          this.load();
        });
    }
  }

  cancel() {
    this.editMode = false;
    this.editableQuestion = null;
  }

  // ================= TEXT =================
  getText(q: any): string {
    if (!q?.translations) return 'No Data';
    const t = q.translations.find((x: any) => (x.language || '').trim().toLowerCase() === this.selectedLang);
    return t?.questionText || 'No Match Found';
  }

  getExplanation(q: any): string {
    const t = q.translations.find((x: any) => (x.language || '').trim().toLowerCase() === this.selectedLang);
    return t?.explanation || '';
  }

  getOptions(q: any): string[] {
    if (!q?.translations) return q.answerOptions || [];

    const lang = (this.selectedLang || '').trim().toLowerCase();
    const t = q.translations.find((x: any) => (x.language || '').trim().toLowerCase() === lang);
    return t?.options || [];
  }

  // ================= FILTERS =================
  applyFilters() {
    this.filteredQuestions = this.questions.filter(q => {

      const matchesSearch =
        !this.searchText ||
        this.getText(q).toLowerCase().includes(this.searchText.toLowerCase());

      const matchesSubject =
        !this.filterSubject || q.subject === this.filterSubject;

      const matchesDifficulty =
        !this.filterDifficulty || q.difficulty === this.filterDifficulty;

      return matchesSearch && matchesSubject && matchesDifficulty;
    });
    this.currentPage = 1;
    this.updatePagination();
  }

  resetFilters() {
    this.searchText = '';
    this.filterSubject = '';
    this.filterDifficulty = '';
    this.filteredQuestions = this.questions;
    this.currentPage = 1;
    this.updatePagination();
  }

  // ================= PAGINATION =================
  updatePagination() {
    const total = this.totalPages;

    if (this.currentPage > total) {
      this.currentPage = total;
    }
    const start = (this.currentPage - 1) * this.pageSize;
    const end = start + this.pageSize;
    this.paginatedQuestions = this.filteredQuestions.slice(start, end);
  }

  get totalPages(): number {
    return Math.ceil(this.filteredQuestions.length / this.pageSize) || 1;
  }

  nextPage() {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.updatePagination();
    }
  }

  prevPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.updatePagination();
    }
  }

  changePageSize() {
    this.currentPage = 1;
    this.updatePagination();
  }

  // ================= SELECTION =================
  toggleSelection(id: number, event: Event) {
    const checked = (event.target as HTMLInputElement).checked;
    if (checked) {
      this.selectedIds.add(id);
    } else {
      this.selectedIds.delete(id);
    }
    this.selectedIds = new Set(this.selectedIds);
  }

  toggleAll(event: Event) {
    const checked = (event.target as HTMLInputElement).checked;
    if (checked) {
      this.paginatedQuestions.forEach(q => this.selectedIds.add(q.id));
    } else {
      this.paginatedQuestions.forEach(q => this.selectedIds.delete(q.id));
    }
    this.selectedIds = new Set(this.selectedIds);
  }

  isAllSelected(): boolean {
    return this.paginatedQuestions.length > 0 &&
      this.paginatedQuestions.every(q => this.selectedIds.has(q.id));
  }

  view(q: any) {
    this.selectedQuestion = q;
    this.viewMode = true;
  }

  closeView() {
    this.viewMode = false;
    this.selectedQuestion = null;
  }

  selectLang(lang: string) {
    this.selectedLang = lang;
    // Optional: refresh filtered questions if needed
    this.applyFilters();
  }

}
