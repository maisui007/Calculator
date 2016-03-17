package com.nimda.calculator.calc.evaluator.lexer;

public enum PredefinedFunction implements Token {
	PERC, POW, ABS, SIN, COS, TAN, LN, SQRT;
	
	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
