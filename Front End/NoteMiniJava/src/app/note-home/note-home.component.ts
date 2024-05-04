import { Component, OnInit } from '@angular/core';
import { NoteService } from '../Service/note.service';

import { LoginService } from '../Service/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-note-home',
  templateUrl: './note-home.component.html',
  styleUrls: ['./note-home.component.css']
})
export class NoteHomeComponent implements OnInit {
  notes: any[] = [];
  currentUser: any ;
  isUserPresent: boolean = false;

  constructor(private loginService : LoginService,private noteService: NoteService, public router: Router) {}

  ngOnInit(): void {
    this.loadRecentNotes(); 
  }

  loadRecentNotes() {
    this.noteService.getRecentNotes().subscribe(
      (data: any[]) => {
        this.notes = data; 
      },
      (error) => {
        console.error('Error fetching recent notes:', error);
      }
    );
  }

  fetchNotes() {
    this.noteService.getRecentNotes().subscribe(
      (response) => {
        this.notes = response;
      },
      (error) => {
        console.error('Error fetching notes:', error);
      }
    );
  }

  deleteNote(noteId: number) {
    this.noteService.deleteNote(noteId).subscribe(
      () => {
        console.log('Note deleted successfully.');
        // After deleting the note, fetch the updated list of notes
        this.fetchNotes();
      },
      (error) => {
        console.error('Error deleting note:', error);
      }
    );
  }

}
