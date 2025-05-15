package com.frontApp.ServerConnection.Controllers;


import com.frontApp.Adapters.LocalDateAdapter;
import com.frontApp.GSONBuilder;
import com.frontApp.HelloApplication;
import com.frontApp.ServerConnection.ServerConnection;
import com.frontApp.auth.LogIn;
import com.frontApp.auth.Register;
import com.frontApp.models.Account;
import com.frontApp.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class LoginController {

	private Account accCheck= new Account();
	@FXML
	private TextField loginField;

	@FXML
	private Label confirmation;

	@FXML
	private PasswordField passwordField;

	@FXML
	public void SendLoginInfo(ActionEvent event){

		Gson gson = GSONBuilder.getGson();

		if(loginField.getText().isBlank()||passwordField.getText().isBlank()){
			Alert emptyAlert = new Alert(Alert.AlertType.ERROR,"Не все поля заполнены!");
			emptyAlert.showAndWait();
		}else{
			ServerConnection conn = new ServerConnection();
			conn.sendMessage("login");

			conn.sendMessage(loginField.getText());
			conn.sendMessage(Register.encrypt(passwordField.getText()));
			System.out.println(passwordField.getText());
			try {
			String returncomm=conn.getServerMessage();
			if(returncomm.equals("ok")){

					LogIn.loggedUser=gson.fromJson(conn.getServerMessage(), User.class);

					System.out.println("ok");
					if(LogIn.loggedUser.getRole().equals("client")){
						FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/Windows/MainWindow/mainWindow.fxml"));
						Stage stage = new Stage();
						Scene regScene = new Scene(fxmlLoader.load());
						stage.setScene(regScene);
						stage.setTitle("Главная страница");
						stage.setResizable(false);
						stage.show();
						final Node source = (Node)event.getSource();
						final Stage thisStage = (Stage)source.getScene().getWindow();
						thisStage.close();
					} else if (LogIn.loggedUser.getRole().equals("admin")) {
						FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/Windows/AdminWindow/AdminWindow.fxml"));
						Stage stage = new Stage();
						Scene regScene = new Scene(fxmlLoader.load());
						stage.setScene(regScene);
						stage.setTitle("Панель управления");
						stage.setResizable(false);
						stage.show();
						final Node source = (Node)event.getSource();
						final Stage thisStage = (Stage)source.getScene().getWindow();
						thisStage.close();
					}



			}else if(returncomm.equals("notfound")){
				Alert emptyAlert = new Alert(Alert.AlertType.ERROR,"Неверный логин или пароль!");
				emptyAlert.showAndWait();
			}
			}catch(Exception ex){
				ex.printStackTrace();
				Alert emptyAlert = new Alert(Alert.AlertType.ERROR,"Ошибка сервера");
				emptyAlert.showAndWait();
			}






		}


	}

	@FXML
	public void OpenRegisterScreen(ActionEvent event)throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/Windows/UserRegistration/userRegistration.fxml"));
		Stage stage = new Stage();
		Scene regScene = new Scene(fxmlLoader.load());
		stage.setScene(regScene);
		stage.setTitle("Регистрация");
		stage.setResizable(false);
		final Node source = (Node)event.getSource();
		final Stage thisStage = (Stage)source.getScene().getWindow();
		stage.show();
		thisStage.close();

	}
}
