package com.frontApp.rateCalculator;

public class CapitalizingRateCalculator implements InterestRateCalculator{
	@Override
	public double calculate(int years, double rate, int startSum) {
		for(int i =0;i<years;i++){
			startSum+=startSum*rate;
		}
		return startSum;
	}

	public CapitalizingRateCalculator(){}
}
