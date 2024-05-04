package com.nagarro.noteWebApp.service;

import com.nagarro.noteWebApp.model.Note;
import com.nagarro.noteWebApp.model.User;
import com.nagarro.noteWebApp.repository.NoteRepository;
import com.nagarro.noteWebApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteService {

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	UserRepository userRepository;

	public Note addNote(String content, User user) {
		Note note = new Note();
		note.setContent(content);
		note.setTimestamp(LocalDateTime.now());
		note.setUser(user);
		return noteRepository.save(note);
	}

	public Note editNote(Long id, String content, User user) {
		Note existingNote = noteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Note with ID " + id + " not found"));

		// Ensure that the user editing the note is the owner
		if (!existingNote.getUser().getId().equals(user.getId())) {
			throw new IllegalArgumentException("User does not have permission to edit this note");
		}

		existingNote.setContent(content);
		return noteRepository.save(existingNote);
	}

	public List<Note> getRecentNotes(User user) {
		return noteRepository.findTop10ByUserOrderByTimestampDesc(user);
	}

	public void deleteExcessNotesByUser(Long userId) {
		this.noteRepository.deleteAllNotesByUserExceptLatest10(userId);
	}

}
