package com.frontApp.models;



import com.google.gson.annotations.Expose;

import java.time.LocalDate;


public class Account {
	@Expose
	private int id;

	private LocalDate registered;
	@Expose
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getRegistered() {
		return registered;
	}

	public void setRegistered(LocalDate registered) {
		this.registered = registered;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



}
