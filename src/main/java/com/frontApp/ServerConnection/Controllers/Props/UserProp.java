package com.frontApp.ServerConnection.Controllers.Props;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserProp{
	private SimpleIntegerProperty id;
	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private SimpleStringProperty middleName;
	private SimpleIntegerProperty telephone;
	private SimpleStringProperty email;
	private SimpleStringProperty login;

	private SimpleStringProperty role;

	public int getId() {
		return id.get();
	}

	public SimpleIntegerProperty idProperty() {
		return id;
	}

	public void setId(int id) {
		this.id.set(id);
	}

	public String getFirstName() {
		return firstName.get();
	}

	public SimpleStringProperty firstNameProperty() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public String getLastName() {
		return lastName.get();
	}

	public SimpleStringProperty lastNameProperty() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public String getMiddleName() {
		return middleName.get();
	}

	public SimpleStringProperty middleNameProperty() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName.set(middleName);
	}

	public int getTelephone() {
		return telephone.get();
	}

	public SimpleIntegerProperty telephoneProperty() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone.set(telephone);
	}

	public String getEmail() {
		return email.get();
	}

	public SimpleStringProperty emailProperty() {
		return email;
	}

	public void setEmail(String email) {
		this.email.set(email);
	}

	public String getLogin() {
		return login.get();
	}

	public SimpleStringProperty loginProperty() {
		return login;
	}

	public void setLogin(String login) {
		this.login.set(login);
	}

	public String getRole() {
		return role.get();
	}

	public SimpleStringProperty roleProperty() {
		return role;
	}

	public void setRole(String role) {
		this.role.set(role);
	}

	public UserProp(int id, String name, String lName, String mName, int tel, String mail, String login, String role){
		this.id = new SimpleIntegerProperty(id);
		this.firstName = new SimpleStringProperty(name);
		this.lastName = new SimpleStringProperty(lName);
		this.middleName =  new SimpleStringProperty(mName);
		this.telephone = new SimpleIntegerProperty(tel);
		this.email = new SimpleStringProperty(mail);
		this.login = new SimpleStringProperty(login);
		this.role = new SimpleStringProperty(role);
	}
}
