package com.frontApp.models;



public class DepositType {

	private int id;

	private int duration;


	private String currency;


	private boolean capitalizing;


	private boolean insurance;

	private InterestRate rate;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public boolean isCapitalizing() {
		return capitalizing;
	}

	public void setCapitalizing(boolean capitalizing) {
		this.capitalizing = capitalizing;
	}

	public boolean isInsurance() {
		return insurance;
	}

	public void setInsurance(boolean insurance) {
		this.insurance = insurance;
	}

	public InterestRate getRate() {
		return rate;
	}

	public void setRate(InterestRate rate) {
		this.rate = rate;
	}
}
