package com.frontApp;
import com.frontApp.ServerConnection.ServerConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import java.io.IOException;


public class HelloApplication extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/Windows/StartWindow/startwindow.fxml"));
		Scene scene = new Scene(fxmlLoader.load());
		stage.setTitle("Hello!");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();



	}

	public static void main(String[] args) {
		try {
			HelloApplication.launch(args);
		}catch(RuntimeException ex){
			ex.printStackTrace();
		}

	}
}