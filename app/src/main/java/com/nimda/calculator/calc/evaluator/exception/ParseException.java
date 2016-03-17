package com.nimda.calculator.calc.evaluator.exception;

public class ParseException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ParseException() {
		super();
	}

	public ParseException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public ParseException(String detailMessage) {
		super(detailMessage);
	}

	public ParseException(Throwable throwable) {
		super(throwable);
	}
}
