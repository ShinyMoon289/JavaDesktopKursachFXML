package com.frontApp.ServerConnection.Controllers;

import com.frontApp.GSONBuilder;
import com.frontApp.HelloApplication;
import com.frontApp.ServerConnection.ServerConnection;
import com.frontApp.auth.LogIn;
import com.frontApp.models.Deposit;
import com.frontApp.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminController {
	private ServerConnection conn = new ServerConnection();
	@FXML
	private ListView<String> usersMenuList;

	@FXML
	private ListView<String> managersMenuList;

	@FXML
	private ListView<String> adminsMenuList;


	public void initialize(){
		conn.sendMessage("getallusers");
		String message=conn.getServerMessage();
		Gson gson = GSONBuilder.getGson();
		TypeToken<ArrayList<User>> token = new TypeToken<>(){};
		ArrayList<User> getList =gson.fromJson(message, token.getType());
		ObservableList<String> userlist= FXCollections.observableArrayList();
		ObservableList<String> adminlist= FXCollections.observableArrayList();
		ObservableList<String> managerlist= FXCollections.observableArrayList();

		for(User user:getList){
			if(user.getLogin().equals(LogIn.loggedUser.getLogin()))
				continue;
			switch (user.getRole()){
				case "client"->
					userlist.add(user.getId()+": "+user.getInfo().getFirstName()+" "
							+user.getInfo().getMiddleName()+" "
							+user.getInfo().getLastName());
				case "admin"->{

						adminlist.add(user.getId()+": "+user.getInfo().getFirstName()+" "
								+user.getInfo().getMiddleName()+" "
								+user.getInfo().getLastName());

				}
				case "manager"->
						managerlist.add(user.getId()+": "+ user.getInfo().getFirstName()+" "
								+user.getInfo().getMiddleName()+" "
								+user.getInfo().getLastName());

			}



		}
		usersMenuList.setItems(userlist);
		adminsMenuList.setItems(adminlist);
		managersMenuList.setItems(managerlist);



	}

	@FXML
	public void addUser(ActionEvent event)throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/Windows/UserRegistration/userRegistration.fxml"));
		Stage stage = new Stage();
		Scene regScene = new Scene(fxmlLoader.load());
		stage.setScene(regScene);
		stage.setTitle("Регистрация");
		stage.setResizable(false);
		stage.show();
	}

	@FXML
	public void blockUser(ActionEvent event){

	}

	@FXML
	public void deleteManager(ActionEvent event){
		managersMenuList.getSelectionModel().clearSelection();

	}

	@FXML
	public void addNewManager(ActionEvent event){

	}
}
