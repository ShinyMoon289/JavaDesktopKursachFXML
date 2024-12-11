package com.frontApp.ServerConnection.Controllers;


import com.frontApp.HelloApplication;
import com.frontApp.auth.LogIn;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {
	@FXML
	private static Label welcomeText;

	@FXML
	private static Button loginButton;
	@FXML
	private static Button regButton;
	@FXML
	private static Button exitButton;

	@FXML
	public void startLoginWindow(ActionEvent event)throws IOException{
		if(LogIn.loggedUser!=null){
			if(LogIn.loggedUser.getRole().equals("client")){
				FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/Windows/MainWindow/mainWindow.fxml"));
				Stage stage = new Stage();
				Scene regScene = new Scene(fxmlLoader.load());
				stage.setScene(regScene);
				stage.setTitle("Главная");
				stage.setResizable(false);
				stage.show();
			}else if(LogIn.loggedUser.getRole().equals("admin")){
				FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/Windows/AdminWindow/AdminWindow.fxml"));
				Stage stage = new Stage();
				Scene regScene = new Scene(fxmlLoader.load());
				stage.setScene(regScene);
				stage.setTitle("Панель управления");
				stage.setResizable(false);
				stage.show();
			}

		}
		FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/Windows/LoginWindow/loginwindow.fxml"));
		Stage stage = new Stage();
		Scene regScene = new Scene(fxmlLoader.load());
		stage.setScene(regScene);
		stage.setTitle("Вход");
		stage.setResizable(false);
		stage.show();

	}


	@FXML
	public void startRegistrationScreen(ActionEvent event)throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/Windows/UserRegistration/userRegistration.fxml"));
		Stage stage = new Stage();
		Scene regScene = new Scene(fxmlLoader.load());
		stage.setScene(regScene);
		stage.setTitle("Регистрация");
		stage.setResizable(false);
		stage.show();

	}

	@FXML
	public void onExit(ActionEvent event){
		Platform.exit();
	}

}