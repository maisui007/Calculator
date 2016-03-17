package com.nimda.calculator.calc.evaluator.lexer;

public class DoubleNumberLiteral extends NumberLiteral {
	public final double value;

	public DoubleNumberLiteral(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}