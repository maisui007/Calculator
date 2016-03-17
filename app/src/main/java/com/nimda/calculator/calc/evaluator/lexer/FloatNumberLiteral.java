package com.nimda.calculator.calc.evaluator.lexer;

public class FloatNumberLiteral extends NumberLiteral {
	public final float value;

	public FloatNumberLiteral(float value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}