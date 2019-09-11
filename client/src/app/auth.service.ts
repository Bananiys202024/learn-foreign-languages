import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import {of as observableOf } from 'rxjs'; // since RxJs 6

const apiUrl = 'http://localhost:8083/api/auth/';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  redirectUrl: string;

  constructor(private http: HttpClient) { }

  login(data: any): Observable<any> {
    console.log('We here');
    return this.http.post(apiUrl + 'login', data)
  }

  isLogIn()
  {
    return localStorage.getItem('token');
  }

  logout(): Observable<any> {
    
    
    console.log('log_out successfully');

    localStorage.removeItem('token');
    localStorage.removeItem('email');
    localStorage.removeItem('password');
    localStorage.removeItem('username');

    return this.http.get<any>(apiUrl + 'logout')
  }
  
  register(data: any): Observable<any> {
    return this.http.post<any>(apiUrl + 'register', data)
      .pipe(
        tap(_ => this.log('login')),
        catchError(this.handleError('login', []))
      );
  }
  
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
  
      console.error('Error--'+error); // log to console instead
      this.log(`${operation} failed: ${error.message}`);
  
      return of(result as T);
    };
  }
  
  private log(message: string) {
    console.log(message);
  }

  getLoggedName() {
    return this.http.get(apiUrl + 'get/logged/name', {responseType: 'text'});
  }

  
}
