package com.frontApp.ServerConnection.Controllers;

import com.frontApp.GSONBuilder;
import com.frontApp.HelloApplication;
import com.frontApp.ServerConnection.Controllers.Props.DepositProp;
import com.frontApp.ServerConnection.Controllers.Props.RateProp;
import com.frontApp.ServerConnection.Controllers.Props.UserProp;
import com.frontApp.ServerConnection.ServerConnection;
import com.frontApp.auth.LogIn;
import com.frontApp.models.Deposit;
import com.frontApp.models.InterestRate;
import com.frontApp.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.util.ArrayList;

public class AdminController {


	@FXML
	public TableColumn<UserProp, String> userFirstNameCol;

	@FXML
	public TableColumn<UserProp, String> userLastNameCol;
	@FXML
	public TableColumn<UserProp, String> userMiddleNameCol;
	@FXML
	public TableColumn<UserProp, String> userEmailCol;
	@FXML
	public TableColumn<UserProp, Integer> userTelephoneCol;

	@FXML
	public TableColumn<UserProp, String> userLoginCol;

	@FXML
	public TableColumn<UserProp, String> userRoleCol;
	@FXML
	public TableColumn<UserProp,String> depositRequisitsCol;

	@FXML
	public TableColumn<UserProp,Boolean> depositCapCol;

	private ServerConnection conn;


	@FXML
	private TableColumn<UserProp, Integer> userIdCol;

	@FXML
	private TableView<UserProp> usersMenuTable;

	@FXML
	private TableView<RateProp> ratesMenuTable;

	@FXML
	private TableColumn<RateProp, Integer> rateIdCol;

	@FXML
	private TableColumn<UserProp, Double> rateRateCol;

	@FXML
	private TableColumn<UserProp, Boolean> rateFixedCol;

	@FXML
	private TableColumn<UserProp, Boolean> rateMonthCol;


	@FXML
	private TableView<DepositProp> depositsMenuTable;

	@FXML
	private TableColumn<DepositProp, Integer> depositIdCol;
	@FXML
	private TableColumn<DepositProp, String> depositOwnerCol;

	@FXML
	private TableColumn<DepositProp, Integer> depositDurationCol;

	@FXML
	private TableColumn<DepositProp, String> depositCurrencyCol;

	@FXML
	private TableColumn<DepositProp, Boolean> depositWithdrCol;

	@FXML
	private TableColumn<DepositProp, Boolean> depositInsureCol;

	@FXML
	private TableColumn<DepositProp, Integer> depositRateIdCol;

	@FXML
	private TableColumn<DepositProp,String> rateNameCol;


	private void refreshUsers(){
		conn = new ServerConnection();
		conn.sendMessage("getallusers");
		String message = conn.getServerMessage();
		Gson gson = GSONBuilder.getGson();
		TypeToken<ArrayList<User>> token = new TypeToken<>() {
		};
		ArrayList<User> getList = gson.fromJson(message, token.getType());
		ObservableList<UserProp> showList = FXCollections.observableArrayList();

		usersMenuTable.setItems(showList);
		for (User user : getList) {
			if (user.getLogin().equals(LogIn.loggedUser.getLogin()))
				continue;
			showList.add(new UserProp(user.getId(), user.getInfo().getFirstName(),
					user.getInfo().getLastName(), user.getInfo().getMiddleName(),
					user.getInfo().getTelephone(), user.getInfo().getEmail(),
					user.getLogin(), user.getRole()));
		}


		userFirstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		userLastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		userMiddleNameCol.setCellValueFactory(new PropertyValueFactory<>("middleName"));
		userIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		userEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		userTelephoneCol.setCellValueFactory(new PropertyValueFactory<>("telephone"));
		userLoginCol.setCellValueFactory(new PropertyValueFactory<>("login"));
		userRoleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
	}

	public void initialize() {
		try {
			refreshUsers();
			String message=null;
			Gson gson = GSONBuilder.getGson();

			conn = new ServerConnection();
			conn.sendMessage("getallrates");
			message = conn.getServerMessage();
			TypeToken<ArrayList<InterestRate>> token2 = new TypeToken<>() {
			};
			ArrayList<InterestRate> rateList = gson.fromJson(message, token2.getType());
			ObservableList<RateProp> showList2 = FXCollections.observableArrayList();
			ratesMenuTable.setItems(showList2);
			for (InterestRate rate : rateList) {
				showList2.add(new RateProp(rate.getId(),rate.getName(), rate.getRate(),
						rate.isFixed(), rate.isEndmonth()));
			}
			rateIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
			rateRateCol.setCellValueFactory(new PropertyValueFactory<>("rate"));
			rateFixedCol.setCellValueFactory(new PropertyValueFactory<>("fixed"));
			rateMonthCol.setCellValueFactory(new PropertyValueFactory<>("endmonth"));
			rateNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

			conn = new ServerConnection();
			conn.sendMessage("getalldeposits");
			message = conn.getServerMessage();
			TypeToken<ArrayList<Deposit>> token3 = new TypeToken<>() {
			};
			ArrayList<Deposit> depositList = gson.fromJson(message, token3.getType());
			ObservableList<DepositProp> showDepositList = FXCollections.observableArrayList();
			depositsMenuTable.setItems(showDepositList);
			for (Deposit dep : depositList) {
				showDepositList.add(new DepositProp(dep.getId(),
								dep.getAccount().getUser().getInfo().getFirstName() + " " + dep.getAccount().getUser().getInfo().getMiddleName() + " " + dep.getAccount().getUser().getInfo().getLastName(),
								dep.getRequisits(),
								dep.getType().getDuration(),
								dep.getType().getCurrency(),
								dep.getType().isWithdraw(),
								dep.getType().isInsurance(),
								dep.getType().getRate().getId(),
								dep.getType().isCapitalizing()
						)
				);
			}
			depositIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
			depositOwnerCol.setCellValueFactory(new PropertyValueFactory<>("ownerName"));
			depositCurrencyCol.setCellValueFactory(new PropertyValueFactory<>("currency"));
			depositDurationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
			depositInsureCol.setCellValueFactory(new PropertyValueFactory<>("insured"));
			depositRateIdCol.setCellValueFactory(new PropertyValueFactory<>("rateId"));
			depositRequisitsCol.setCellValueFactory(new PropertyValueFactory<>("requisits"));
			depositCapCol.setCellValueFactory(new PropertyValueFactory<>("cap"));
			depositWithdrCol.setCellValueFactory(new PropertyValueFactory<>("withdraw"));
		}catch (Exception ex){
			ex.printStackTrace();
		}

	}

	@FXML
	public void addUser(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/Windows/UserRegistration/userRegistration.fxml"));
		Stage stage = new Stage();
		Scene regScene = new Scene(fxmlLoader.load());
		stage.setScene(regScene);
		stage.setTitle("Регистрация");
		stage.setResizable(false);
		stage.show();
		refreshUsers();

	}

	@FXML
	public void deleteDeposit(ActionEvent event){

	}

	@FXML
	public void blockUser(ActionEvent event) {
		System.out.println(usersMenuTable.getSelectionModel().getSelectedItem().getId());

		usersMenuTable.getItems().remove(usersMenuTable.getSelectionModel().getSelectedItem());


	}


	@FXML
	public void switchToUser(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/Windows/MainWindow/mainWindow.fxml"));
		Stage stage = new Stage();
		Scene regScene = new Scene(fxmlLoader.load());
		stage.setScene(regScene);
		stage.setTitle("Главная");
		stage.setResizable(false);
		stage.show();
	}

	public void switchToManager(ActionEvent event) {

	}



}
