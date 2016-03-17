package com.nimda.calculator.calc.evaluator.lexer;

public enum Separator implements Token {
	COMMA;
	
	@Override
	public String toString() {		
		return ",";
	}
}
