package com.nimda.calculator.calc.evaluator.lexer;

import java.math.BigDecimal;

public class BigDecimalNumberLiteral extends NumberLiteral {
	public final BigDecimal value;

	public BigDecimalNumberLiteral(BigDecimal value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value.toString();
	}
}