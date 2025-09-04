import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

const BASE_URL = "http://localhost:9060/"; 

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  signup(signupRequest: any): Observable<any> {
    return this.http.post(BASE_URL + "api/auth/signup", signupRequest);
  }

  login(loginRequest: any): Observable<any> {
    return this.http.post<any>(BASE_URL + "api/auth/login", loginRequest).pipe(
      tap(response => {
        // save JWT token
        if (response.token) {
          localStorage.setItem('token', response.token);
        }

        // save customerId (if backend returns it)
        if (response.customerId) {
          localStorage.setItem('customerId', response.customerId);
        }
      })
    );
  }

  // helper method
  getCustomerId(): number | null {
    const id = localStorage.getItem('customerId');
    return id ? Number(id) : null;
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('customerId');
  }
}
