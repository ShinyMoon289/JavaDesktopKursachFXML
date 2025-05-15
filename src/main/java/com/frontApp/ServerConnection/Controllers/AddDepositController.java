package com.frontApp.ServerConnection.Controllers;

import com.frontApp.GSONBuilder;
import com.frontApp.ServerConnection.Controllers.Props.RateProp;
import com.frontApp.ServerConnection.ServerConnection;
import com.frontApp.auth.LogIn;
import com.frontApp.models.Account;
import com.frontApp.models.Deposit;
import com.frontApp.models.DepositType;
import com.frontApp.models.InterestRate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.print.attribute.IntegerSyntax;
import java.util.ArrayList;
import java.util.Random;

public class AddDepositController {

	@FXML
	private TableView<RateProp> rateTable;

	@FXML
	private TableColumn<RateProp,String> rateNameCol;

	@FXML
	private TableColumn<RateProp,Double> rateValueCol;

	@FXML
	private TableColumn<RateProp,Boolean> rateFixedCol;

	@FXML
	private TableColumn<RateProp,Boolean> rateEndmonthCol;

	@FXML
	private ComboBox<String>currencyCheck;

	@FXML
	private Slider sumScroller;

	@FXML
	private Slider durationScroller;

	@FXML
	private CheckBox withdrawCheck;

	@FXML
	private CheckBox capCheck;

	@FXML
	private CheckBox insuranceCheck;

	private ArrayList<InterestRate> getList;
	private ServerConnection conn=null;

	public void initialize(){
		Gson gson = GSONBuilder.getGson();
		conn=new ServerConnection();
		conn.sendMessage("getallrates");
		TypeToken<ArrayList<InterestRate>> token = new TypeToken<>() {
		};
		String message = conn.getServerMessage();
		getList = gson.fromJson(message, token.getType());
		ObservableList<RateProp>showList= FXCollections.observableArrayList();

		for(InterestRate rate:getList) {
			showList.add(new RateProp(rate.getId(),rate.getName(),rate.getRate(),rate.isFixed(),rate.isEndmonth()));
		}
		rateTable.setItems(showList);

		rateValueCol.setCellValueFactory(new PropertyValueFactory<>("rate"));
		rateFixedCol.setCellValueFactory(new PropertyValueFactory<>("fixed"));
		rateEndmonthCol.setCellValueFactory(new PropertyValueFactory<>("endmonth"));
		rateNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

		currencyCheck.getItems().addAll("BYN","USD","RUB","CNY");
		Alert alert = new Alert(Alert.AlertType.WARNING,"Валюта не выбрана!");
		alert.showAndWait();
	}

	@FXML
	public void sendDeposit(ActionEvent event){
		if(rateTable.getSelectionModel().getSelectedItem()==null){
			Alert rateAlert = new Alert(Alert.AlertType.WARNING,"Выберите план ставки");
			rateAlert.showAndWait();
		}
		Gson gson = GSONBuilder.getGson();
		Random rand = new Random();
		conn=new ServerConnection();
		conn.sendMessage("getaccount");
		conn.sendMessage(gson.toJson(LogIn.loggedUser));
		Deposit dep=new Deposit();
		dep.setRequisits(String.valueOf(rand.nextInt(1000000)));
		dep.setMoney(sumScroller.getValue());
		dep.setType(new DepositType());
		dep.getType().setCurrency(currencyCheck.getSelectionModel().getSelectedItem());
		dep.getType().setDuration((int)durationScroller.getValue());
		dep.getType().setInsurance(insuranceCheck.isSelected());
		dep.getType().setWithdraw(withdrawCheck.isSelected());
		dep.getType().setCapitalizing(capCheck.isSelected());
		dep.getType().setRate(getList.get(rateTable.getSelectionModel().getSelectedItem().getId()-1));
		Account acc = gson.fromJson(conn.getServerMessage(),Account.class);
		conn = new ServerConnection();
		conn.sendMessage("adddeposit");
		conn.sendMessage(gson.toJson(dep));

	}

	@FXML
	public void cancelDeposit(ActionEvent event){
		final Node source = (Node)event.getSource();
		final Stage stage = (Stage)source.getScene().getWindow();
		stage.close();
	}


}
