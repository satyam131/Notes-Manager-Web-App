package com.nagarro.noteWebApp.service;

import com.nagarro.noteWebApp.model.User;

public class CurrentUserContext {
	private static CurrentUserContext instance;

	private User currentUser;

	private CurrentUserContext() {
		// Private constructor to enforce singleton pattern
	}

	public static CurrentUserContext getInstance() {
		if (instance == null) {
			instance = new CurrentUserContext();
		}
		return instance;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User user) {
		this.currentUser = user;
	}

	public void clearCurrentUser() {
		this.currentUser = null;
	}
}
