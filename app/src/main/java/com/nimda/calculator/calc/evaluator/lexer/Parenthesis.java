package com.nimda.calculator.calc.evaluator.lexer;

public enum Parenthesis implements Token {
	OPEN, CLOSE;

	@Override
	public String toString() {		
		if (this == OPEN) {
			return "(";
		} else {
			return ")";
		}
	}
}
