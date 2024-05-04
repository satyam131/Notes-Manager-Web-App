import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  private baseUrl = 'http://localhost:8082/users'; 

  constructor(private httpClient: HttpClient) {}

  register(user: any): Observable<any> {
    return this.httpClient.post<any>(`${this.baseUrl}/register`, user);
  }
}
