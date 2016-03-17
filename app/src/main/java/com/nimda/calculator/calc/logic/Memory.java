package com.nimda.calculator.calc.logic;

import android.util.Log;

import com.nimda.calculator.calc.config.LoggerConfig;

import java.math.BigDecimal;
import java.util.Stack;

class Memory {
    private static final String TAG = "Memory";
    private final Stack<BigDecimal> answerStack = new Stack<BigDecimal>();
    private final String[] storedExpressions = new String[10];

    void clearAnswers() {
        answerStack.clear();
    }

    BigDecimal getMostRecentAnswer() {
        if (answerStack.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return answerStack.peek();
        }
    }

    void addAnswer(BigDecimal result) {
        answerStack.push(result);
    }

    void addExpressionToStore(int position, String expression) {
        if (position < 0 || position >= storedExpressions.length) {
            if (LoggerConfig.ON) {
                Log.w(TAG, "addToStore(): Invalid position: " + position);
            }
        } else {
            storedExpressions[position] = expression;
        }
    }

    String readExpressionFromStore(int position) {
        if (position < 0 || position >= storedExpressions.length) {
            if (LoggerConfig.ON) {
                Log.w(TAG, "addToStore(): Invalid position: " + position);
            }

            return "";
        } else {
            return storedExpressions[position] == null ? "" : storedExpressions[position];
        }
    }

    int getStoreSize() {
        return storedExpressions.length;
    }
}
