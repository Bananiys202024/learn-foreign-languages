
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardAnonymouse  implements CanActivate
{

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
    const url: string = state.url;
  
    return this.checkLogin(url);
  }

  checkLogin(url: string): boolean {
    if (!this.authService.isLogIn()) {return true; }
    console.log(this.authService.isLogIn+"--meth");
    // Store the attempted URL for redirecting
    this.authService.redirectUrl = url;
  
    // Navigate to the login page with extras
    this.router.navigate(['']);
    return false;
  }
  
}
