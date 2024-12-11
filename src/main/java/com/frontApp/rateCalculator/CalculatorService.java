package com.frontApp.rateCalculator;



public class CalculatorService {

	private InterestRateCalculator calculator;

	private int years;

	private int sum;

	private double rate;

	public CalculatorService(){	}
	public CalculatorService(InterestRateCalculator calc, int years,int sum,double rate){
		this.calculator=calc;
		this.rate=rate;
		this.sum=sum;
		this.years=years;
	}

	public double calculateRate(){
		return calculator.calculate(this.years,this.rate,this.sum);
	}
	public InterestRateCalculator getCalculator() {
		return calculator;
	}

	public void setCalculator(InterestRateCalculator calculator) {
		this.calculator = calculator;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
}
