package com.frontApp.rateCalculator;


public class NonCapitalizingRateCalculator implements InterestRateCalculator {
	@Override
	public double calculate(int years, double rate, int startSum) {
		double resultSum=startSum;
		for (int i = 0; i < years; i++) {
			resultSum+=startSum*rate;
		}
		return resultSum;
	}
	public NonCapitalizingRateCalculator(){}
}
