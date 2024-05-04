package com.nagarro.noteWebApp.controller;

import com.nagarro.noteWebApp.model.Note;
import com.nagarro.noteWebApp.model.User;
import com.nagarro.noteWebApp.repository.NoteRepository;
import com.nagarro.noteWebApp.service.CurrentUserContext;
import com.nagarro.noteWebApp.service.NoteRequestDto;
import com.nagarro.noteWebApp.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/notes")
public class NoteController {

	@Autowired
	private NoteService noteService;

	@Autowired
	private NoteRepository noteRepository;

	@SuppressWarnings("unused")
	@PostMapping("/add")
	public Note addNote(@RequestBody NoteRequestDto noteRequestDto) {

		User currentUser = CurrentUserContext.getInstance().getCurrentUser();
		System.out.println(
				"Checking user from note controller for add note " + currentUser + " Email " + currentUser.getEmail());

		if (currentUser == null) {
			Note newErrorNote = new Note();
			newErrorNote.setContent("ErrorOccursNoUserFound");
			newErrorNote.setUser(currentUser);
			return newErrorNote;
		}

		return noteService.addNote(noteRequestDto.getContent(), currentUser);
	}

	@PutMapping("/{id}")
	public Note editNote(@PathVariable Long id, @RequestBody NoteRequestDto noteRequestDto) {

		User currentUser = CurrentUserContext.getInstance().getCurrentUser();
		System.out.println(
				"Checking user from note controller for edit note " + currentUser + " Email " + currentUser.getEmail());

		return noteService.editNote(id, noteRequestDto.getContent(), currentUser);
	}

	@GetMapping("/recent")
	public List<Note> getRecentNotes() {

		User currentUser = CurrentUserContext.getInstance().getCurrentUser();
		System.out.println("Checking user from note controller for get recent note " + currentUser + " Email "
				+ currentUser.getEmail());

		List<Note> recentNotes = noteService.getRecentNotes(currentUser);
		System.out.println("Recent notes: " + recentNotes);

		return noteService.getRecentNotes(currentUser);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<DeleteNoteResponse> deleteNote(@PathVariable Long id) {

		User currentUser = CurrentUserContext.getInstance().getCurrentUser();
		System.out.println("Checking user from note controller for get recent note " + currentUser + " Email "
				+ currentUser.getEmail());

		// Check if the note exists
		Note noteToDelete = noteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Note with ID " + id + " not found"));

		// Ensure that the user deleting the note is the owner
		if (!noteToDelete.getUser().getId().equals(currentUser.getId())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body(new DeleteNoteResponse("You do not have permission to delete this note"));
		}

		noteRepository.delete(noteToDelete);

		return ResponseEntity.ok(new DeleteNoteResponse("Note deleted successfully"));
	}

	public class DeleteNoteResponse {
		private String message;

		public DeleteNoteResponse(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}

}
