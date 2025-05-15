package com.frontApp.models;


public class InterestRate {

	private int id;


	private double rate;


	private boolean fixed;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private boolean endmonth;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	public boolean isEndmonth() {
		return endmonth;
	}

	public void setEndmonth(boolean endmonth) {
		this.endmonth = endmonth;
	}
}
