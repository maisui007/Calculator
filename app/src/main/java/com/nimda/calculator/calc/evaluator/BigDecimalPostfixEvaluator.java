package com.nimda.calculator.calc.evaluator;

import com.nimda.calculator.calc.evaluator.exception.ParseException;

import java.math.BigDecimal;

public class BigDecimalPostfixEvaluator extends PostfixEvaluator {
	private final BigDecimal[] operandStack = new BigDecimal[4096];
	private int stackPointer = -1;

	public BigDecimalPostfixEvaluator(String input) throws ParseException {
		super(input, NumberPrecision.BIG_DECIMAL);
	}		

	public BigDecimal evaluate() throws ParseException {
		for (final FlatToken token : postfixExpression) {
			switch (token.type) {
				case FlatToken.TYPE_IDENTIFIER:
				case FlatToken.TYPE_NUMBER_LITERAL:
					operandStack[++stackPointer] = token.bigDecimalValue;
					break;
				case FlatToken.TYPE_OPERATOR:
					if (token.typeEnum == FlatToken.OPERATOR_NEGATE) {
						final BigDecimal a = operandStack[stackPointer--];
						operandStack[++stackPointer] = a.negate();
					} else {
						final BigDecimal b = operandStack[stackPointer--];
						final BigDecimal a = operandStack[stackPointer--];

						switch (token.typeEnum) {
							case FlatToken.OPERATOR_ADD:
								operandStack[++stackPointer] = a.add(b);
								break;
							case FlatToken.OPERATOR_SUBTRACT:
								operandStack[++stackPointer] = a.subtract(b);
								break;
							case FlatToken.OPERATOR_MULTIPLY:
								operandStack[++stackPointer] = a.multiply(b);
								break;
							case FlatToken.OPERATOR_PERCENTAGE:
								operandStack[++stackPointer] = (a.divide(BigDecimal.valueOf((100))).multiply(b));
								break;
							case FlatToken.OPERATOR_DIVIDE:
								stackPointer++;
								try {
									operandStack[stackPointer] = a.divide(b);
								} catch (ArithmeticException e) {
									// Try using double values
									// TODO: No, should use precision instead.
									operandStack[stackPointer] = new BigDecimal(a.doubleValue() / b.doubleValue());
								}
								
								break;
							case FlatToken.OPERATOR_POWER:
								operandStack[++stackPointer] = new BigDecimal(
										Math.pow(a.doubleValue(), b.doubleValue()));
								break;
						}
					}
					break;
				case FlatToken.TYPE_PREDEF_FUNCTION:
					if (token.typeEnum == FlatToken.FUNCTION_POW) {
						final BigDecimal b = operandStack[stackPointer--];
						final BigDecimal a = operandStack[stackPointer--];
						operandStack[++stackPointer] = new BigDecimal(Math.pow(a.doubleValue(), b.doubleValue()));
					} else {
						final BigDecimal a = operandStack[stackPointer--];

						switch (token.typeEnum) {
							case FlatToken.FUNCTION_ABS:
								operandStack[++stackPointer] = a.abs();
								break;
							case FlatToken.FUNCTION_SIN:
								operandStack[++stackPointer] = new BigDecimal(Math.sin(a.doubleValue()));
								break;
							case FlatToken.FUNCTION_COS:
								operandStack[++stackPointer] = new BigDecimal(Math.cos(a.doubleValue()));
								break;
							case FlatToken.FUNCTION_TAN:
								operandStack[++stackPointer] = new BigDecimal(Math.tan(a.doubleValue()));
								break;
							case FlatToken.FUNCTION_LN:
								operandStack[++stackPointer] = new BigDecimal(Math.log(a.doubleValue()));
								break;
							case FlatToken.FUNCTION_SQRT:
								operandStack[++stackPointer] = new BigDecimal(Math.sqrt(a.doubleValue()));
								break;
						}
					}
					break;
			}
		}

		if (stackPointer != 0) {
			stackPointer = -1;
			throw new ParseException("Error evaluating expression");
		}

		return operandStack[stackPointer--];
	}
}
