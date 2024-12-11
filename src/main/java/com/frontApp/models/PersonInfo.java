package com.frontApp.models;



import java.time.LocalDate;


public class PersonInfo {

	private int id;

	private String firstName;

	private String lastName;
	private String middleName;
	private int telephone;
	private LocalDate birthdate;

	private String email;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getEmail(){
		return this.email;
	}
	public void setEmail(String mail){
		this.email=mail;
	}

	public String getFirstName(){
		return this.firstName;
	}
	public void setFirstName(String name){
		this.firstName=name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public int getTelephone() {
		return this.telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public LocalDate getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
}
