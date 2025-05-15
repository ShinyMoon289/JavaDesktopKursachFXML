package com.frontApp.ServerConnection.Controllers.Props;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DepositProp {
	private SimpleIntegerProperty id;
	private SimpleStringProperty ownerName;
	private SimpleIntegerProperty duration;
	private SimpleStringProperty currency;
	private SimpleBooleanProperty withdraw;
	private SimpleBooleanProperty insured;
	private SimpleIntegerProperty rateId;
	private SimpleBooleanProperty cap;
	private SimpleDoubleProperty rateValue;

	private SimpleBooleanProperty endmonth;

	private SimpleBooleanProperty fixed;
	private SimpleStringProperty requisits;
	public DepositProp(int id,
					   String owner,String reqs,
					   int duration,
					   String curr,
					   boolean withdraw,
					   boolean insurance,
					   int rate,boolean cap){

		this.id=new SimpleIntegerProperty(id);
		this.ownerName=new SimpleStringProperty(owner);
		this.currency=new SimpleStringProperty(curr);
		this.withdraw=new SimpleBooleanProperty(withdraw);
		this.insured=new SimpleBooleanProperty(insurance);
		this.duration=new SimpleIntegerProperty(duration);
		this.rateId=new SimpleIntegerProperty(rate);
		this.requisits=new SimpleStringProperty(reqs);
		this.cap=new SimpleBooleanProperty(cap);
		this.rateValue=new SimpleDoubleProperty();
		this.endmonth=new SimpleBooleanProperty();
		this.fixed=new SimpleBooleanProperty();

	}


	public boolean isEndmonth() {
		return endmonth.get();
	}

	public SimpleBooleanProperty endmonthProperty() {
		return endmonth;
	}

	public void setEndmonth(boolean endmonth) {
		this.endmonth.set(endmonth);
	}

	public boolean isFixed() {
		return fixed.get();
	}

	public SimpleBooleanProperty fixedProperty() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed.set(fixed);
	}

	public double getRateValue() {
		return rateValue.get();
	}

	public SimpleDoubleProperty rateValueProperty() {
		return rateValue;
	}

	public void setRateValue(double rateValue) {
		this.rateValue.set(rateValue);
	}

	public int getId() {
		return id.get();
	}

	public SimpleIntegerProperty idProperty() {
		return id;
	}

	public void setId(int id) {
		this.id.set(id);
	}

	public String getOwnerName() {
		return ownerName.get();
	}

	public SimpleStringProperty ownerNameProperty() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName.set(ownerName);
	}

	public int getDuration() {
		return duration.get();
	}

	public SimpleIntegerProperty durationProperty() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration.set(duration);
	}

	public String getCurrency() {
		return currency.get();
	}

	public SimpleStringProperty currencyProperty() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency.set(currency);
	}

	public boolean isWithdraw() {
		return withdraw.get();
	}

	public SimpleBooleanProperty withdrawProperty() {
		return withdraw;
	}

	public void setWithdraw(boolean withdraw) {
		this.withdraw.set(withdraw);
	}

	public boolean isInsured() {
		return insured.get();
	}

	public SimpleBooleanProperty insuredProperty() {
		return insured;
	}

	public void setInsured(boolean insured) {
		this.insured.set(insured);
	}

	public int getRateId() {
		return rateId.get();
	}

	public SimpleIntegerProperty rateIdProperty() {
		return rateId;
	}

	public void setRateId(int rateId) {
		this.rateId.set(rateId);
	}

	public boolean isCap() {
		return cap.get();
	}

	public SimpleBooleanProperty capProperty() {
		return cap;
	}

	public void setCap(boolean cap) {
		this.cap.set(cap);
	}

	public String getRequisits() {
		return requisits.get();
	}

	public SimpleStringProperty requisitsProperty() {
		return requisits;
	}

	public void setRequisits(String requisits) {
		this.requisits.set(requisits);
	}
}
