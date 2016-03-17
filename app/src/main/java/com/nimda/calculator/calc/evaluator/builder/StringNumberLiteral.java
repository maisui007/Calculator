package com.nimda.calculator.calc.evaluator.builder;

import com.nimda.calculator.calc.evaluator.lexer.NumberLiteral;

class StringNumberLiteral extends NumberLiteral {
	private static final char NEGATIVE_CHAR = '-';
	private static final String DECIMAL_STR = ".";
	final StringBuilder value;

	StringNumberLiteral() {
		this.value = new StringBuilder();
	}

	StringNumberLiteral(String value) {
		this.value = new StringBuilder(value);
	}

	void addDecimalIfNeeded() {
		if (value.indexOf(DECIMAL_STR) == -1) {
			value.append(DECIMAL_STR);
		}
	}

	void togglePlusMinus() {
		if (value.length() > 0 && value.charAt(0) == NEGATIVE_CHAR) {
			value.deleteCharAt(0);
		} else {
			value.insert(0, NEGATIVE_CHAR);
		}
	}

	void appendDigit(int digit) {
		value.append(digit);
	}

	void deleteChar() {
		final int length = value.length();

		if (length > 0) {
			value.deleteCharAt(length - 1);
		}
	}

	boolean isEmpty() {
		return value.length() == 0;
	}

	@Override
	public String toString() {
		return value.toString();
	}
}