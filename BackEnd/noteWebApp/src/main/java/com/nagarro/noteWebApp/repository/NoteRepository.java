package com.nagarro.noteWebApp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.nagarro.noteWebApp.model.Note;
import com.nagarro.noteWebApp.model.User;
import jakarta.transaction.Transactional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

	List<Note> findTop10ByUserOrderByTimestampDesc(User user);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM note WHERE user_id = ?1 AND id NOT IN (SELECT id FROM (SELECT id FROM note WHERE user_id = ?1 ORDER BY timestamp DESC LIMIT 10) AS latest_notes)", nativeQuery = true)
	public void deleteAllNotesByUserExceptLatest10(long userId);

}
