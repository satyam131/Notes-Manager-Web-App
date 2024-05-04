import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenServiceService {

  constructor() { }

  generateToken(): string {
    // Generate a random token or use your own logic
    const token = Math.random().toString(36).substring(2);
    return token;
  }

}
