package com.frontApp.ServerConnection.Controllers.Props;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RateProp {
	private SimpleIntegerProperty id;
	private SimpleDoubleProperty rate;
	private SimpleStringProperty name;
	private SimpleBooleanProperty fixed;
	private SimpleBooleanProperty endmonth;

	public RateProp(int id,String name ,double rate,boolean fixed,boolean endmonth){
		this.id = new SimpleIntegerProperty(id);
		this.name=new SimpleStringProperty(name);
		this.rate = new SimpleDoubleProperty(rate*100);
		this.endmonth = new SimpleBooleanProperty(endmonth);
		this.fixed = new SimpleBooleanProperty(fixed);

	}

	public String getName() {
		return name.get();
	}

	public SimpleStringProperty nameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
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

	public double getRate() {
		return rate.get();
	}

	public SimpleDoubleProperty rateProperty() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate.set(rate);
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

	public boolean isEndmonth() {
		return endmonth.get();
	}

	public SimpleBooleanProperty endmonthProperty() {
		return endmonth;
	}

	public void setEndmonth(boolean endmonth) {
		this.endmonth.set(endmonth);
	}
}
