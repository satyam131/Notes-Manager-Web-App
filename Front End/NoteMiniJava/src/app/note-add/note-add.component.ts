import { Component, OnInit } from '@angular/core';
import { NoteService } from '../Service/note.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-note-add',
  templateUrl: './note-add.component.html',
  styleUrls: ['./note-add.component.css']
})
export class NoteAddComponent implements OnInit {
  newNoteContent: string = '';
  validationError: string | null = null;

  constructor(private noteService: NoteService, private router: Router) { }

  ngOnInit(): void { }

  isTextareaValid = true;
  validateTextarea(event: Event) {
    const textarea = event.target as HTMLTextAreaElement;
    const value = textarea.value.trim();
    if (value === '' || value.length > 500) {
      this.isTextareaValid = false;
    } else {
      this.isTextareaValid = true;
    }
  }

  onSubmit() {

    if (this.newNoteContent) {

      if(!this.isTextareaValid){
        this.validationError = 'Seems like you entered space only';
        return;
      }

      if (!this.isValidNoteContent(this.newNoteContent)) {
        this.validationError = 'Note content should not exceed 500 characters and may contain only [@, ;, &, *, +, -] special characters.';
        return;
      }

      this.noteService.addNote(this.newNoteContent).subscribe(
        (response) => {
          console.log('Note added successfully:', response);
          this.newNoteContent = '';
          this.validationError = null;
          this.router.navigate(['/home']);
        },
        (error) => {
          console.error('Error adding note:', error);
        }
      );
    }
  }

  // Function to validate note content
  isValidNoteContent(content: string): boolean {
    if (content.length > 500) {
      return false;
    }
    return true;
  }

  isEmpty(content: string): boolean {
    if (content.length == 0) {
      return true;
    }
    return false;
  }
}