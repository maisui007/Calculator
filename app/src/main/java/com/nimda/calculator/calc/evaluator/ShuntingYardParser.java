package com.nimda.calculator.calc.evaluator;

import com.nimda.calculator.calc.evaluator.exception.ParseException;
import com.nimda.calculator.calc.evaluator.lexer.Identifier;
import com.nimda.calculator.calc.evaluator.lexer.Lexer;
import com.nimda.calculator.calc.evaluator.lexer.NumberLiteral;
import com.nimda.calculator.calc.evaluator.lexer.Operator;
import com.nimda.calculator.calc.evaluator.lexer.Operator.Associativity;
import com.nimda.calculator.calc.evaluator.lexer.Parenthesis;
import com.nimda.calculator.calc.evaluator.lexer.PredefinedFunction;
import com.nimda.calculator.calc.evaluator.lexer.Separator;
import com.nimda.calculator.calc.evaluator.lexer.Token;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class ShuntingYardParser {
	private final Lexer lexer;
	private final List<Token> outputList = new ArrayList<Token>();
	private final Stack<Token> stack = new Stack<Token>();

	private Token nextToken;

	ShuntingYardParser(String input, NumberPrecision numberPrecision) throws ParseException {
		this.lexer = new Lexer(input, numberPrecision);
		consume();
	}

	List<Token> parse() throws ParseException {
		while (nextToken != Token.EOF) {
			if (nextToken instanceof NumberLiteral || nextToken instanceof Identifier) {
				outputList.add(nextToken);
			} else if (nextToken instanceof PredefinedFunction || nextToken == Parenthesis.OPEN) {
				stack.push(nextToken);
			} else if (nextToken == Separator.COMMA) {
				try {
					popToLeftParenthesisToOutput();
				} catch (EmptyStackException e) {
					throw new ParseException("Misplaced comma or mis-matched parenthesis.");
				}
			} else if (nextToken instanceof Operator) {
				final Operator o1 = (Operator) nextToken;

				while (!stack.isEmpty() && stack.peek() instanceof Operator) {
					final Operator o2 = (Operator) stack.peek();

					if ((o1.associativity == Associativity.LEFT && o1.precedence <= o2.precedence)
					 || (o1.associativity == Associativity.RIGHT && o1.precedence < o2.precedence)) {
						popStackTopToOutput();
					} else {
						break;
					}
				}

				stack.push(nextToken);
			} else if (nextToken == Parenthesis.CLOSE) {
				try {
					popToLeftParenthesisToOutput();
				} catch (EmptyStackException e) {
					throw new ParseException("Mis-matched parenthesis.");
				}

				// Pop the left parenthesis from the stack, but not to the
				// output queue.
				stack.pop();

				if (!stack.isEmpty() && stack.peek() instanceof PredefinedFunction) {
					popStackTopToOutput();
				}
			}

			consume();
		}

		while (!stack.isEmpty()) {
			if (stack.peek() instanceof Parenthesis) {
				throw new ParseException("Mis-matched parenthesis.");
			} else {
				popStackTopToOutput();
			}
		}

		return outputList;
	}

	private void popStackTopToOutput() {
		outputList.add(stack.pop());
	}

	private void popToLeftParenthesisToOutput() throws EmptyStackException {
		while (stack.peek() != Parenthesis.OPEN) {
			popStackTopToOutput();
		}

	}

	private void consume() throws ParseException {
		nextToken = lexer.nextToken();
	}
}
