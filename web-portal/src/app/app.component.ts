import { Component } from '@angular/core';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'web-portal';
  /** This is for hiding header footer on question area page */
  hideGlobalLayoutForAssessmentPage = false;

  constructor(private router: Router, private route: ActivatedRoute) {
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe(() => {
        let currentRoute = this.route.root;
        while (currentRoute.firstChild) {
          currentRoute = currentRoute.firstChild;
        }
        this.hideGlobalLayoutForAssessmentPage = currentRoute.snapshot.data['hideGlobalLayoutForAssessmentPage'] === true;
      });
  }
  /** This is for hiding header footer on question area page */
}
