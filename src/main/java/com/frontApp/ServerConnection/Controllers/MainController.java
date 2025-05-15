package com.frontApp.ServerConnection.Controllers;

import com.frontApp.Adapters.LocalDateAdapter;
import com.frontApp.GSONBuilder;
import com.frontApp.HelloApplication;
import com.frontApp.ServerConnection.Controllers.Props.DepositProp;
import com.frontApp.ServerConnection.Controllers.Props.RateProp;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
	private TableView<DepositProp> depositTable;

	@FXML
	private TableColumn<DepositProp,Integer> depositIdCol;

	@FXML
	private TableColumn<DepositProp,String> depositRequisitsCol;

	@FXML
	private TableColumn<DepositProp,Integer> depositDurationCol;

	@FXML
	private TableColumn<DepositProp,String> depositCurrencyCol;

	@FXML
	private TableColumn<DepositProp,Boolean>depositWithdrawCol;

	@FXML
	private TableColumn<RateProp,Double>depositRateCol;

	@FXML
	private TableColumn<DepositProp,Boolean>depositCapCol;

	@FXML
	private TableColumn<DepositProp,Boolean>depositInsuredCol;

	@FXML
	private TableColumn<DepositProp,Boolean>depositFixedCol;

	@FXML
	private TableColumn<DepositProp,Boolean>depositEndmonthCol;



	

	@FXML
	public void addDeposit(ActionEvent event)throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/Windows/AddDepositWindow/AddDepositWindow.fxml"));
		Stage stage = new Stage();
		Scene regScene = new Scene(fxmlLoader.load());
		stage.setScene(regScene);
		stage.setTitle("Оформление вклада");
		stage.setResizable(false);
		stage.show();
	}

	@FXML
	public void openCalculator(ActionEvent event)throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/Windows/Calculator/Calculator.fxml"));
		Stage stage = new Stage();
		Scene regScene = new Scene(fxmlLoader.load());
		stage.setScene(regScene);
		stage.setTitle("Финансовый калькулятор");
		stage.setResizable(false);
		stage.show();
	}

	@FXML
	public void exitAccount(ActionEvent event){
		LogIn.loggedUser=null;
		final Node source = (Node)event.getSource();
		final Stage stage = (Stage)source.getScene().getWindow();
		stage.close();
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

		try{
			conn.sendMessage("getalldeposits");
			TypeToken<ArrayList<Deposit>> token = new TypeToken<>(){};
			String message = conn.getServerMessage();
			if(message==null){

			}
			else{
				ArrayList<Deposit> getList =gson.fromJson(message, token);
				if(getList!=null){
					ObservableList<DepositProp> depositList= FXCollections.observableArrayList();
					depositTable.setItems(depositList);
					for(Deposit dep:getList){
						if(dep.getAccount().getUser().getId()==LogIn.loggedUser.getId()){
							DepositProp prop = new DepositProp(dep.getId(),
									" ",
									dep.getRequisits(),
									dep.getType().getDuration(),
									dep.getType().getCurrency(),
									dep.getType().isWithdraw(),
									dep.getType().isInsurance(),
									dep.getType().getRate().getId(),
									dep.getType().isCapitalizing());
							prop.setRateValue(dep.getType().getRate().getRate()*100);
							prop.setFixed(dep.getType().getRate().isFixed());
							depositList.add(prop);
						}


					}

					depositIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
					depositCurrencyCol.setCellValueFactory(new PropertyValueFactory<>("currency"));
					depositRequisitsCol.setCellValueFactory(new PropertyValueFactory<>("requisits"));
					depositDurationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
					depositRateCol.setCellValueFactory(new PropertyValueFactory<>("rateValue"));
					depositCapCol.setCellValueFactory(new PropertyValueFactory<>("cap"));
					depositFixedCol.setCellValueFactory(new PropertyValueFactory<>("fixed"));
					depositWithdrawCol.setCellValueFactory(new PropertyValueFactory<>("withdraw"));
					depositEndmonthCol.setCellValueFactory(new PropertyValueFactory<>("endmonth"));
					depositInsuredCol.setCellValueFactory(new PropertyValueFactory<>("insured"));


				}
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}




	}


	public void sendNewPersonInfo(ActionEvent event) {
		
	}
}
