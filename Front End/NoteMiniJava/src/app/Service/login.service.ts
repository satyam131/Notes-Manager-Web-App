import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private baseUrl = 'http://localhost:8082/users'; 

  constructor(private httpClient: HttpClient) {}

  login(user: any): Observable<any> {
    return this.httpClient.post<any>(`${this.baseUrl}/login`, user);
  }
}
