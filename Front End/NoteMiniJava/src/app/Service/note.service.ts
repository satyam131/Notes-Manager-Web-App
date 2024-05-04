import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NoteService {
  private baseUrl = 'http://localhost:8082/api/notes';

  constructor(private httpClient: HttpClient) {}

  getRecentNotes(): Observable<any[]> {
    return this.httpClient.get<any[]>(`${this.baseUrl}/recent`);
  }

  deleteNote(noteId: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseUrl}/${noteId}`);
  }

  addNote(content: string): Observable<any> {
    const body = { content };
    return this.httpClient.post<any>(`${this.baseUrl}/add`, body);
  }

  editNote(noteId: number, content: string): Observable<any> {
    const body = { content };
    return this.httpClient.put<any>(`${this.baseUrl}/${noteId}`, body);
  }

}