package com.nimda.calculator.calc.evaluator;

import com.nimda.calculator.calc.evaluator.exception.ParseException;
import com.nimda.calculator.calc.evaluator.lexer.BigDecimalNumberLiteral;
import com.nimda.calculator.calc.evaluator.lexer.DoubleNumberLiteral;
import com.nimda.calculator.calc.evaluator.lexer.FloatNumberLiteral;
import com.nimda.calculator.calc.evaluator.lexer.Identifier;
import com.nimda.calculator.calc.evaluator.lexer.NumberLiteral;
import com.nimda.calculator.calc.evaluator.lexer.Operator;
import com.nimda.calculator.calc.evaluator.lexer.PredefinedFunction;
import com.nimda.calculator.calc.evaluator.lexer.Token;

import java.math.BigDecimal;
import java.util.List;

public abstract class PostfixEvaluator {
	static class FlatToken {
		static final FlatToken UNDEFINED = new FlatToken();

		static final int TYPE_UNDEFINED = 0;
		static final int TYPE_IDENTIFIER = 1;
		static final int TYPE_NUMBER_LITERAL = 2;
		static final int TYPE_OPERATOR = 3;
		static final int TYPE_PREDEF_FUNCTION = 4;

		static final int OPERATOR_ADD = 0;
		static final int OPERATOR_SUBTRACT = 1;
		static final int OPERATOR_MULTIPLY = 2;
		static final int OPERATOR_DIVIDE = 3;
		static final int OPERATOR_POWER = 4;
		static final int OPERATOR_NEGATE = 5;
		static final int OPERATOR_PERCENTAGE = 6;

		static final int FUNCTION_ABS = 0;
		static final int FUNCTION_SIN = 1;
		static final int FUNCTION_COS = 2;
		static final int FUNCTION_TAN = 3;
		static final int FUNCTION_POW = 4;
		static final int FUNCTION_LN = 5;
		static final int FUNCTION_SQRT = 6;

		final int type;

		int typeEnum;
		String name;
		float floatValue;
		double doubleValue;
		BigDecimal bigDecimalValue;

		private FlatToken() {
			type = TYPE_UNDEFINED;
		}

		FlatToken(Token fromToken, NumberPrecision numberPrecision) throws ParseException {
			if (fromToken instanceof Identifier) {
				type = TYPE_IDENTIFIER;
				name = ((Identifier) fromToken).name;
			} else if (fromToken instanceof NumberLiteral) {
				type = TYPE_NUMBER_LITERAL;

				switch (numberPrecision) {
					case BIG_DECIMAL:
						bigDecimalValue = ((BigDecimalNumberLiteral) fromToken).value;
						break;
					case DOUBLE:
						doubleValue = ((DoubleNumberLiteral) fromToken).value;
						break;
					case FLOAT:
					default:
						floatValue = ((FloatNumberLiteral) fromToken).value;
						break;
				}
			} else if (fromToken instanceof Operator) {
				type = TYPE_OPERATOR;

				switch ((Operator) fromToken) {
					case ADD:
						typeEnum = OPERATOR_ADD;
						break;
					case SUBTRACT:
						typeEnum = OPERATOR_SUBTRACT;
						break;
					case MULTIPLY:
						typeEnum = OPERATOR_MULTIPLY;
						break;
					case DIVIDE:
						typeEnum = OPERATOR_DIVIDE;
						break;
					case POWER:
						typeEnum = OPERATOR_POWER;
						break;
					case NEGATION:
						typeEnum = OPERATOR_NEGATE;
						break;
					case PERCENT:
						typeEnum = OPERATOR_PERCENTAGE;
						break;
				}
			} else if (fromToken instanceof PredefinedFunction) {
				type = TYPE_PREDEF_FUNCTION;

				switch ((PredefinedFunction) fromToken) {
					case ABS:
						typeEnum = FUNCTION_ABS;
						break;
					case SIN:
						typeEnum = FUNCTION_SIN;
						break;
					case COS:
						typeEnum = FUNCTION_COS;
						break;
					case TAN:
						typeEnum = FUNCTION_TAN;
						break;
					case POW:
						typeEnum = FUNCTION_POW;
						break;
					case LN:
						typeEnum = FUNCTION_LN;
						break;
					case SQRT:
						typeEnum = FUNCTION_SQRT;
						break;
				}
			} else {
				throw new ParseException("Unexpected token " + fromToken);
			}
		}
	}

	protected final FlatToken[] postfixExpression;

	PostfixEvaluator(String input, NumberPrecision numberPrecision) throws ParseException {
		final List<Token> parsedExpression = new ShuntingYardParser(input, numberPrecision).parse();

		postfixExpression = new FlatToken[parsedExpression.size()];
		int counter = 0;

		for (Token token : parsedExpression) {
			postfixExpression[counter++] = new FlatToken(token, numberPrecision);
		}
	}

	FlatToken getIdentifier(String name) {
		for (final FlatToken token : postfixExpression) {
			if (token.type == FlatToken.TYPE_IDENTIFIER && token.name.equalsIgnoreCase(name)) {
				return token;
			}
		}
		return FlatToken.UNDEFINED;
	}			
}