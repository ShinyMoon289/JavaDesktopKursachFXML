package com.frontApp.ServerConnection.Controllers;

import com.frontApp.Adapters.LocalDateAdapter;
import com.frontApp.auth.LogIn;
import com.frontApp.auth.Register;
import com.frontApp.models.Account;
import com.frontApp.models.PersonInfo;
import com.frontApp.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.frontApp.ServerConnection.ServerConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.time.LocalDate;

public class RegistrationController {
	private ServerConnection conn;

	@FXML
	private TextField LoginText;

	@FXML
	private TextField nameField;
	@FXML
	private TextField lastNameField;

	@FXML
	private TextField middleNameField;

	@FXML
	private TextField telephoneField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private PasswordField doublePassword;

	@FXML
	private DatePicker birthDate;

	@FXML
	private TextField mailField;

	@FXML
	private ComboBox<String> userRoleBox;

	public void initialize(){
		if(LogIn.loggedUser==null||!LogIn.loggedUser.getRole().equals("admin")){
			userRoleBox.setDisable(true);
		}else{
			userRoleBox.setDisable(false);
			userRoleBox.getItems().addAll("admin","client","manager");
		}
	}
	@FXML
	public void onCloseRegistration(ActionEvent event){
		final Node source = (Node)event.getSource();
		final Stage stage = (Stage)source.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void onSendRegistration(ActionEvent event){
		if(nameField.getText().isBlank()
				||lastNameField.getText().isBlank()
				||telephoneField.getText().isBlank()
				||mailField.getText().isBlank()
				||LoginText.getText().isBlank()
			||passwordField.getText().isBlank()||doublePassword.getText().isBlank()){
			Alert blankAlert = new Alert(Alert.AlertType.ERROR,"Не все поля заполнены!");
			blankAlert.showAndWait();
		} else if (!passwordField.getText().equals(doublePassword.getText())) {
			Alert blankAlert = new Alert(Alert.AlertType.ERROR,"Пароли не совпадают!");
			blankAlert.showAndWait();
			passwordField.clear();
			doublePassword.clear();
		} else if(telephoneField.getText().length()!=7){
			Alert telephoneAlert = new Alert(Alert.AlertType.ERROR,
					"Неверно указан телефон, должно быть 7 цифр!");
			passwordField.clear();
			doublePassword.clear();
			telephoneAlert.showAndWait();
		}
		else{
			System.out.println(birthDate.getValue());

			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter(LocalDate.class,new LocalDateAdapter());
			builder.setPrettyPrinting();

			Gson gson = builder.create();
			conn = new ServerConnection();
			conn.sendMessage("registeruser");
			Account account = new Account();
			account.setUser(new User());
			account.getUser().setInfo(new PersonInfo());
			account.getUser().setLogin(LoginText.getText());
			account.getUser().setPasswordHash(Register.encrypt(passwordField.getText()));
			account.getUser().setRole("client");
			account.getUser().getInfo().setFirstName(nameField.getText());
			account.getUser().getInfo().setLastName(lastNameField.getText());
			if(!middleNameField.getText().isBlank()){
				account.getUser().getInfo().setMiddleName(middleNameField.getText());
			}
			account.getUser().getInfo().setEmail(mailField.getText());
			account.getUser().getInfo().setBirthdate(birthDate.getValue());
			account.getUser().getInfo().setTelephone(Integer.parseInt(telephoneField.getText()));
			account.setRegistered(LocalDate.now());
			conn.sendMessage(gson.toJson(account));
			String response = conn.getServerMessage();
			try {
				if(response==null){

					Alert regError = new Alert(Alert.AlertType.ERROR,"Ошибка регистрации!");
					regError.showAndWait();
				}else if(response.equals("ok"))
				{
					Alert regNotification = new Alert(Alert.AlertType.INFORMATION,"Регистрация успешно пройдена!");
					regNotification.showAndWait();
					final Node source = (Node)event.getSource();
					final Stage stage = (Stage)source.getScene().getWindow();
					stage.close();
					return;
				}
			}catch (Exception ex){
				ex.printStackTrace();
			}



		}


	}

}
