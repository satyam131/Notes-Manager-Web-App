import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  constructor() { }

  IsLoggedIn() {
    return !!localStorage.getItem('token');
  }
}
