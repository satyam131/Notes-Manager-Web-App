package com.nagarro.noteWebApp.controller;

import com.nagarro.noteWebApp.model.User;
import com.nagarro.noteWebApp.repository.UserRepository;
import com.nagarro.noteWebApp.service.CurrentUserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/create")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}

	@PutMapping("/update/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));

		user.setUsername(updatedUser.getUsername());
		user.setPassword(updatedUser.getPassword());
		user.setEmail(updatedUser.getEmail());

		return userRepository.save(user);
	}

	@GetMapping("/byEmail")
	public User getUserByEmail(@RequestParam String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@GetMapping("/all")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/logout")
	public boolean logoutUser() {
		CurrentUserContext.getInstance().clearCurrentUser();
		return true;
	}

	private boolean isValidEmail(String email) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
		return email.matches(emailRegex);
	}

	// Register a new user
	@PostMapping("/register")
	public ResponseEntity<RegistrationResponse> registerUser(@RequestBody User user) {

		// Custom validation for email format
		if (!isValidEmail(user.getEmail())) {
			RegistrationResponse response = new RegistrationResponse("Invalid email format.",
					HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		// Custom validation for password length
		if (user.getPassword().length() < 6) {
			RegistrationResponse response = new RegistrationResponse("Password must be at least 6 characters.",
					HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		// Check if the email or username is already registered
		if (userRepository.existsByEmail(user.getEmail())) {
			RegistrationResponse response = new RegistrationResponse("Email is already in use.",
					HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		if (userRepository.existsByUsername(user.getUsername())) {
			RegistrationResponse response = new RegistrationResponse("Username is already in use.",
					HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		// Save the new user to the database
		userRepository.save(user);

		// Create a custom response object for successful registration
		RegistrationResponse response = new RegistrationResponse("Registration successful.", HttpStatus.OK.value());

		// Return the JSON response with the appropriate status code
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	// Define a custom response object
	class RegistrationResponse {
		private String message;
		private int status;

		public RegistrationResponse(String message, int status) {
			this.message = message;
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> loginUser(@RequestBody User loginUser) {
		// Find the user by email
		User user = userRepository.findByEmail(loginUser.getEmail());

		// Check if the user exists and the password matches
		if (user != null && user.getPassword().equals(loginUser.getPassword())) {
			CurrentUserContext.getInstance().setCurrentUser(user);

			User usecheck = CurrentUserContext.getInstance().getCurrentUser();
			System.out.println("checking user for usercontroller" + usecheck + "  Email = " + usecheck.getEmail());

			LoginResponse response = new LoginResponse("Login successful.", HttpStatus.OK.value());

			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			LoginResponse response = new LoginResponse("Login failed. Check your email and password.",
					HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	// Define a custom response object
	class LoginResponse {
		private String message;
		private int status;

		public LoginResponse(String message, int status) {
			this.message = message;
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

	}

}
