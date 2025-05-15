package com.frontApp.ServerConnection.Controllers;

import com.frontApp.rateCalculator.CalculatorService;
import com.frontApp.rateCalculator.CapitalizingRateCalculator;
import com.frontApp.rateCalculator.InterestRateCalculator;
import com.frontApp.rateCalculator.NonCapitalizingRateCalculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.math.BigDecimal;

public class CalculatorController {

	@FXML
	private Slider moneySlider;

	@FXML
	private Slider rateSlider;

	@FXML
	private Slider durationSlider;


	@FXML
	private CheckBox capitalizedCheck;

	@FXML
	private TextField resultWindow;

	@FXML
	public void calculate(ActionEvent event){
		InterestRateCalculator calculator =null;


		if(capitalizedCheck.isSelected()){
			calculator= new CapitalizingRateCalculator();
		}else
			calculator=new NonCapitalizingRateCalculator();
		CalculatorService service = new CalculatorService(calculator,(int)durationSlider.getValue(),moneySlider.getValue(),
				rateSlider.getValue());
		this.resultWindow.setText(new BigDecimal(service.calculateRate()).toPlainString());

	}

	@FXML
	public void initialize(){

	}
}
