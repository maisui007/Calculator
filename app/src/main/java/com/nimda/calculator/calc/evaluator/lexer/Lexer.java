package com.nimda.calculator.calc.evaluator.lexer;

import  com.nimda.calculator.calc.evaluator.NumberPrecision;
import  com.nimda.calculator.calc.evaluator.exception.ParseException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Lexer {
	private static final char EOF_CHAR = (char) -1;

	private final Map<String, PredefinedFunction> predefinedFunctions = new HashMap<String, PredefinedFunction>();
	private final Map<String, Identifier> previouslyFoundIdentifiers = new HashMap<String, Identifier>();
	private final String input;
	private final NumberPrecision numberPrecision;

	private int currentPosition;
	private int maxPosition;
	private char currentCharacter;
	private Token currentToken;

	public Lexer(String input, NumberPrecision numberPrecision) {
		this.input = input.trim().toLowerCase();
		this.numberPrecision = numberPrecision;

		for (PredefinedFunction predefinedFunction : PredefinedFunction.values()) {
			predefinedFunctions.put(predefinedFunction.name().toLowerCase(), predefinedFunction);
		}

		currentPosition = 0;
		maxPosition = input.length() - 1;

		peek();
	}

	private void peek() {
		currentCharacter = currentPosition <= maxPosition ? input.charAt(currentPosition) : EOF_CHAR;
	}

	public Token nextToken() throws ParseException {
		if (isEOF()) {
			currentToken = Token.EOF;
		} else if (isWhitespace()) {
			skipWhitespace();
			return nextToken();
		} else if (isDigit()) {
			currentToken = tokenizeNumberLiteral();
		} else if (isPartOfIdentifier()) {
			currentToken = tokenizeIdentifierOrPredefinedFunction();
		} else {
			currentToken = tokenizeSymbol();
		}

		return currentToken;
	}

	private boolean isEOF() {
		return currentCharacter == EOF_CHAR;
	}

	private boolean isWhitespace() {
		return Character.isWhitespace(currentCharacter);
	}

	private void skipWhitespace() {
		do {
			consume();
		} while (isWhitespace());
	}

	private void consume() {
		currentPosition++;
		peek();
	}

	private boolean isDigit() {
		return Character.isDigit(currentCharacter);
	}

	private boolean isPartOfNumber() {
		return Character.isDigit(currentCharacter) || currentCharacter == '.';
	}

	private Token tokenizeNumberLiteral() throws ParseException {
		int startPosition = currentPosition;
		boolean isInScientificNotation = false;

		do {
			consume();

			if (currentCharacter == 'E' || currentCharacter == 'e') {
				isInScientificNotation = true;
				consume();

				if (currentCharacter == '+' || currentCharacter == '-') {
					consume();
				} else {
					throw new ParseException("Invalid scientific number around "
							+ input.substring(startPosition, currentCharacter) + ": expected '+' or '-'");
				}

				while (isDigit()) {
					consume();
				}
			}
		} while (isPartOfNumber());

		switch (numberPrecision) {
			case BIG_DECIMAL:
				return new BigDecimalNumberLiteral(new BigDecimal(input.substring(startPosition, currentPosition)));
			case DOUBLE:
				if (isInScientificNotation) {
					return new DoubleNumberLiteral(
							new BigDecimal(input.substring(startPosition, currentPosition)).doubleValue());
				} else {
					return new DoubleNumberLiteral(Double.parseDouble(input.substring(startPosition, currentPosition)));
				}
			default:
			case FLOAT:
				if (isInScientificNotation) {
					return new FloatNumberLiteral(
							new BigDecimal(input.substring(startPosition, currentPosition)).floatValue());
				} else {
					return new FloatNumberLiteral(Float.parseFloat(input.substring(startPosition, currentPosition)));
				}
		}
	}

	private boolean isPartOfIdentifier() {
		return Character.isLetter(currentCharacter);
	}

	private Token tokenizeIdentifierOrPredefinedFunction() {
		int startPosition = currentPosition;

		do {
			consume();
		} while (isPartOfIdentifier());

		final String value = input.substring(startPosition, currentPosition);

		final PredefinedFunction predefinedFunction = predefinedFunctions.get(value);

		if (predefinedFunction != null) {
			return predefinedFunction;
		} else {
			final Identifier identifier = previouslyFoundIdentifiers.get(value);

			if (identifier != null) {
				return identifier;
			} else {
				final Identifier newIdentifier = new Identifier(value);
				previouslyFoundIdentifiers.put(value, newIdentifier);
				return newIdentifier;
			}
		}
	}

	private Token tokenizeSymbol() throws ParseException {
		final char symbol = currentCharacter;
		consume();

		if (symbol == '+') {
			return Operator.ADD;
		} else if (symbol == '-') {
			if (currentToken instanceof NumberLiteral || currentToken instanceof Identifier
					|| currentToken instanceof PredefinedFunction) {
				return Operator.SUBTRACT;
			} else {
				return Operator.NEGATION;
			}
		} else if (symbol == '*') {
			return Operator.MULTIPLY;
		} else if (symbol == '/') {
			return Operator.DIVIDE;
		} else if (symbol == '^') {
			return Operator.POWER;
		} else if (symbol == '(') {
			return Parenthesis.OPEN;
		} else if (symbol == ')') {
			return Parenthesis.CLOSE;
		} else if (symbol == ',') {
			return Separator.COMMA;
		}
		else if (symbol == '%') {
			return Operator.PERCENT;
		}

		else {
			throw new ParseException("tokenizeSymbol(): Could not recognize symbol " + symbol);
		}
	}
}