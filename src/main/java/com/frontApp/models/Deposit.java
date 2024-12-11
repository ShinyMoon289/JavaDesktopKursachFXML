package com.frontApp.models;

public class Deposit {

	private int id;


	private double money;


	private Account account;


	private String requisits;

	private DepositType type;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getRequisits() {
		return requisits;
	}

	public void setRequisits(String requisits) {
		this.requisits = requisits;
	}

	public DepositType getType() {
		return type;
	}

	public void setType(DepositType type) {
		this.type = type;
	}
}
