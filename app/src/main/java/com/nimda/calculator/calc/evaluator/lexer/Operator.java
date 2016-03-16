//   Copyright 2012 Digipom Inc.
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

package com.nimda.calculator.calc.evaluator.lexer;

import static com.nimda.calculator.calc.evaluator.lexer.Operator.Associativity.LEFT;
import static com.nimda.calculator.calc.evaluator.lexer.Operator.Associativity.RIGHT;

public enum Operator implements Token {
	PERCENT(0,LEFT), ADD(0, LEFT), SUBTRACT(0, LEFT), MULTIPLY(1, LEFT), DIVIDE(1, LEFT), NEGATION(2, RIGHT), POWER(3, RIGHT);

	public static enum Associativity {
		LEFT, RIGHT
	}

	public final int precedence;
	public final Associativity associativity;

	private Operator(int precedence, Associativity associativity) {
		this.precedence = precedence;
		this.associativity = associativity;
	}
	
	@Override
	public String toString() {
		if (this == ADD) {
			return "+";
		} else if (this == SUBTRACT || this == NEGATION) {
			return "-";
		} else if (this == MULTIPLY) {
			return "*";
		} else if (this == DIVIDE) {
			return "/";
		} else if (this == PERCENT) {
			return "%";
		} else {
			return "^";
		}
	}
}
