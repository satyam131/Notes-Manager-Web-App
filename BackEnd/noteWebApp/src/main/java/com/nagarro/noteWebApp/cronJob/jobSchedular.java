package com.nagarro.noteWebApp.cronJob;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.nagarro.noteWebApp.model.User;
import com.nagarro.noteWebApp.repository.UserRepository;
import com.nagarro.noteWebApp.service.NoteService;

@Configuration
@EnableScheduling
public class jobSchedular {

	@Autowired
	NoteService noteService;

	@Autowired
	private UserRepository userRepo;

	@Scheduled(cron = "0 0 * * * *")
	public void scheduleCronTaskToDeleteNotes() {
		System.out.println("schedular working.....................");
		List<User> usersList = userRepo.findAll();
		for (User user : usersList)
			this.noteService.deleteExcessNotesByUser(user.getId());
	}
}
