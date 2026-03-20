import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeedJsonQuestionsComponent } from './seed-json-questions.component';

describe('SeedJsonQuestionsComponent', () => {
  let component: SeedJsonQuestionsComponent;
  let fixture: ComponentFixture<SeedJsonQuestionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SeedJsonQuestionsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SeedJsonQuestionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
