package com.frontApp.models;



public class User {


	private int id;

	private String login;

	private PersonInfo info;

	private String passwordHash;



	private String role;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public PersonInfo getInfo() {
		return this.info;
	}

	public void setInfo(PersonInfo info) {
		this.info = info;
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}



