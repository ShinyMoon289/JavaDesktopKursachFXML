package com.frontApp.ServerConnection.Controllers;

import com.frontApp.Adapters.LocalDateAdapter;
import com.frontApp.GSONBuilder;
import com.frontApp.ServerConnection.ServerConnection;
import com.frontApp.auth.LogIn;
import com.frontApp.models.Deposit;
import com.frontApp.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


public class MainController{

	private ServerConnection conn = new ServerConnection();
	public Button switchNewInfo;
	public TextField newPasswordConfirm;
	public TextField newMiddleName;
	public TextField oldPassword;
	public TextField newPassword;
	public Button sendInfoButton;
	public Button cancelInfoButton;
	public ListView depositList;

	@FXML
	private Label greetingText;
	@FXML
	private TextField newFirstName;

	@FXML
	private TextField newLastName;

	@FXML
	private TextField newLogin;

	@FXML
	private TextField newMail;
	
	


	@FXML
	public void onInit(Event event)throws IOException {

	}

	@FXML
	public void OnInit(Event event)throws IOException{

	}
	@FXML
	public void initialize()throws IOException{

		Gson gson = GSONBuilder.getGson();



		greetingText.setText("Добро пожаловать, "+LogIn.loggedUser.getInfo().getFirstName());
		System.out.println(greetingText.getText());
		newFirstName.setText(LogIn.loggedUser.getInfo().getFirstName());
		newLastName.setText(LogIn.loggedUser.getInfo().getLastName());
		newLogin.setText(LogIn.loggedUser.getLogin());
		newMail.setText(LogIn.loggedUser.getInfo().getEmail());
		if(LogIn.loggedUser.getInfo().getMiddleName()==null||!LogIn.loggedUser.getInfo().getMiddleName().isBlank()){
			newMiddleName.setText(LogIn.loggedUser.getInfo().getMiddleName());
		}
		depositList =new ListView<String> ();

		try{
			conn.sendMessage("getdeposits");
			conn.sendMessage(gson.toJson(LogIn.loggedUser,User.class));
			TypeToken<ArrayList<Deposit>> token = new TypeToken<ArrayList<Deposit>>(){};
			String message = conn.getServerMessage();
			if(message==null){

			}
			else{
				ArrayList<Deposit> getList =gson.fromJson(message, token);
				if(getList!=null){
					ObservableList<String> list= FXCollections.observableArrayList();
					for(Deposit dep:getList){
						list.add(dep.getId()+": "+dep.getRequisits());
					}
					depositList.setItems(list);

				}
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}




	}


	public void sendNewPersonInfo(ActionEvent event) {
		
	}
}
