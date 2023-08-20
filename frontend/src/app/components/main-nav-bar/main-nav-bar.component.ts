import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import {
  getSession,
  invalidateSession,
  invalidateToken,
} from 'src/app/util/context';

@Component({
  selector: 'app-main-nav-bar',
  templateUrl: './main-nav-bar.component.html',
  styleUrls: ['./main-nav-bar.component.scss'],
})
export class MainNavBarComponent {
  role: string = '';
  isOfficial: boolean = false;

  isHandset$: Observable<boolean> = this.breakpointObserver
    .observe(Breakpoints.Handset)
    .pipe(
      map((result) => result.matches),
      shareReplay()
    );

  constructor(
    private breakpointObserver: BreakpointObserver,
    private router: Router
  ) {}

  ngOnInit(): void {
    const session = getSession();
    if (session !== undefined) {
      this.role = session.role;
      if (this.role.toUpperCase() === 'SLUZBENIK') {
        this.isOfficial = true;
      }
    } else {
      this.role = '';
    }
  }

  logout() {
    invalidateSession();
    invalidateToken();
    this.router.navigate([this.router.url])
    this.router.navigate(["/"])
    window.location.replace("/")
  }
}
