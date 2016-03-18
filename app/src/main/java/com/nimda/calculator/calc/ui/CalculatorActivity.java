package com.nimda.calculator.calc.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.nimda.calculator.R;
import com.nimda.calculator.calc.logic.Calculator;


public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String CALCULATOR = "CALCULATOR";
    private static final int VIBRATE_TIME = 15;
    private static final String TAG = "MY_LOG";
    private Calculator calculator;
    private Vibrator vibrator;
    private TextView outputView;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        Typeface tf_lite = Typeface.createFromAsset(getAssets(), "Lato-Light.ttf");
        Typeface tf = Typeface.createFromAsset(getAssets(), "Lato-Thin.ttf");
        calculator = new Calculator(this);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        outputView = (TextView) findViewById(R.id.outputView);
        if (outputView != null) {
            outputView.setTypeface(tf_lite, Typeface.BOLD);
        }

        Button buttonZero = (Button) findViewById(R.id.buttonZero);
        Button buttonOne = (Button) findViewById(R.id.buttonOne);
        Button buttonTwo = (Button) findViewById(R.id.buttonTwo);
        Button buttonThree = (Button) findViewById(R.id.buttonThree);
        Button buttonFour = (Button) findViewById(R.id.buttonFour);
        Button buttonFive = (Button) findViewById(R.id.buttonFive);
        Button buttonSix = (Button) findViewById(R.id.buttonSix);
        Button buttonSeven = (Button) findViewById(R.id.buttonSeven);
        Button buttonEight = (Button) findViewById(R.id.buttonEight);
        Button buttonNine = (Button) findViewById(R.id.buttonNine);

        Button buttonRightArrow = (Button) findViewById(R.id.buttonRightArrow);
        Button buttonDiv = (Button) findViewById(R.id.buttonDiv);
        Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        Button buttonSubtract = (Button) findViewById(R.id.buttonSubtract);
        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
        Button buttonDecimal = (Button) findViewById(R.id.buttonDecimal);
        Button buttonC = (Button) findViewById(R.id.buttonClear);
        Button buttonEquals = (Button) findViewById(R.id.buttonEquals);
        Button buttonToggle = (Button) findViewById(R.id.buttonToggleSign);

        Button buttonLeftPar = (Button) findViewById(R.id.buttonLeftPar);
        Button buttonRightPar = (Button) findViewById(R.id.buttonRightPar);
        Button buttonTangent = (Button) findViewById(R.id.buttonTangent);
        Button buttonCosine = (Button) findViewById(R.id.buttonCosine);
        Button buttonSine = (Button) findViewById(R.id.buttonSine);
        Button buttonSquareRoot = (Button) findViewById(R.id.buttonSquareRoot);
        Button buttonln = (Button) findViewById(R.id.buttonln);
        Button buttonPercent = (Button) findViewById(R.id.buttonPercent);


        buttonZero.setOnClickListener(this);
        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        buttonFour.setOnClickListener(this);
        buttonFive.setOnClickListener(this);
        buttonSix.setOnClickListener(this);
        buttonSeven.setOnClickListener(this);
        buttonEight.setOnClickListener(this);
        buttonNine.setOnClickListener(this);

        buttonRightArrow.setOnClickListener(this);
        buttonDiv.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonSubtract.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
        buttonDecimal.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonEquals.setOnClickListener(this);
        buttonToggle.setOnClickListener(this);

        Button[] buttons = {buttonZero, buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight,
                buttonNine, buttonRightArrow, buttonDiv, buttonMultiply, buttonSubtract, buttonAdd, buttonDecimal, buttonC,
                buttonEquals, buttonToggle};
        for (Button btn : buttons) {
            if (btn != null) {

                buttonZero.setTypeface(tf, Typeface.BOLD);
                buttonNine.setTypeface(tf, Typeface.BOLD);
                buttonEight.setTypeface(tf, Typeface.BOLD);
                buttonSeven.setTypeface(tf, Typeface.BOLD);
                buttonSix.setTypeface(tf, Typeface.BOLD);
                buttonFive.setTypeface(tf, Typeface.BOLD);
                buttonFour.setTypeface(tf, Typeface.BOLD);
                buttonThree.setTypeface(tf, Typeface.BOLD);
                buttonTwo.setTypeface(tf, Typeface.BOLD);
                buttonOne.setTypeface(tf, Typeface.BOLD);
                buttonRightArrow.setTypeface(tf, Typeface.BOLD);
                buttonDiv.setTypeface(tf, Typeface.BOLD);
                buttonMultiply.setTypeface(tf, Typeface.BOLD);
                buttonSubtract.setTypeface(tf, Typeface.BOLD);
                buttonAdd.setTypeface(tf, Typeface.BOLD);
                buttonToggle.setTypeface(tf, Typeface.BOLD);
                buttonDecimal.setTypeface(tf, Typeface.BOLD);
                buttonEquals.setTypeface(tf, Typeface.BOLD);
                buttonC.setTypeface(tf, Typeface.BOLD);

            }
        }

        Button[] buttons_land = {buttonLeftPar, buttonRightPar, buttonTangent, buttonCosine, buttonSine, buttonSquareRoot,
                buttonln, buttonPercent};

        for (Button btn : buttons_land) {
            if (btn != null) {

                buttonLeftPar.setOnClickListener(this);
                buttonRightPar.setOnClickListener(this);
                buttonTangent.setOnClickListener(this);
                buttonCosine.setOnClickListener(this);
                buttonSine.setOnClickListener(this);
                buttonSquareRoot.setOnClickListener(this);
                buttonln.setOnClickListener(this);
                buttonPercent.setOnClickListener(this);

                buttonPercent.setTypeface(tf, Typeface.BOLD);
                buttonLeftPar.setTypeface(tf, Typeface.BOLD);
                buttonRightPar.setTypeface(tf, Typeface.BOLD);
                buttonTangent.setTypeface(tf, Typeface.BOLD);
                buttonCosine.setTypeface(tf, Typeface.BOLD);
                buttonSine.setTypeface(tf, Typeface.BOLD);
                buttonSquareRoot.setTypeface(tf, Typeface.BOLD);
                buttonln.setTypeface(tf, Typeface.BOLD);
            }
        }

        addVibrateListenersToButtons((ViewGroup) findViewById(R.id.rootView), new VibratorTouchListener());
    }

    @Override
    public void onClick(View v) {

        if (v != null) {
            final int id = v.getId();

            switch (id) {
                case R.id.buttonDiv:
                    calculator.selectDivide();
                    break;
                case R.id.buttonOne:
                    calculator.selectDigit(1);
                    break;
                case R.id.buttonTwo:
                    calculator.selectDigit(2);
                    break;
                case R.id.buttonThree:
                    calculator.selectDigit(3);
                    break;
                case R.id.buttonFour:
                    calculator.selectDigit(4);
                    break;
                case R.id.buttonFive:
                    calculator.selectDigit(5);
                    break;
                case R.id.buttonSix:
                    calculator.selectDigit(6);
                    break;
                case R.id.buttonSeven:
                    calculator.selectDigit(7);
                    break;
                case R.id.buttonEight:
                    calculator.selectDigit(8);
                    break;
                case R.id.buttonNine:
                    calculator.selectDigit(9);
                    break;
                case R.id.buttonZero:
                    calculator.selectDigit(0);
                    break;
                case R.id.buttonMultiply:
                    calculator.selectMultiply();
                    break;
                case R.id.buttonSubtract:
                    calculator.selectSubtract();
                    break;
                case R.id.buttonAdd:
                    calculator.selectAdd();
                    break;
                case R.id.buttonClear:
                    calculator.selectCe();
                    break;
                case R.id.buttonDecimal:
                    calculator.selectDecimal();
                    break;
                case R.id.buttonEquals:
                    calculator.selectEquals();
                    break;
                case R.id.buttonPercent:
                    calculator.selectPerc();
                    break;
                case R.id.buttonRightArrow:
                    calculator.selectRightArrow();
                    break;
                case R.id.buttonSine:
                    calculator.selectSin();
                    break;
                case R.id.buttonCosine:
                    calculator.selectCos();
                    break;
                case R.id.buttonTangent:
                    calculator.selectTan();
                    break;
                case R.id.buttonln:
                    calculator.selectLn();
                    break;
                case R.id.buttonLeftPar:
                    calculator.selectLeftParenthesis();
                    break;
                case R.id.buttonRightPar:
                    calculator.selectRightParenthesis();
                    break;
                case R.id.buttonSquareRoot:
                    calculator.selectSqrtX();
                    break;
                case R.id.buttonToggleSign:
                    calculator.selectPlusMinus();
                    break;
            }
        }
        updateOutput();
    }

    class VibratorTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if (event != null && event.getAction() == MotionEvent.ACTION_DOWN)
                vibrator.vibrate(VIBRATE_TIME);
            return false;
        }
    }

    private void updateOutput() {
        outputView.setText(calculator.getExpression());
    }


    private void addVibrateListenersToButtons(ViewGroup root, VibratorTouchListener listener) {
        if (root != null) {
            final int childCount = root.getChildCount();

            for (int i = 0; i < childCount; i++) {
                final View child = root.getChildAt(i);
                if (child instanceof Button) {
                    child.setOnTouchListener(listener);
                } else if (child instanceof ViewGroup) {
                    addVibrateListenersToButtons((ViewGroup) child, listener);
                }
            }
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putSerializable(CALCULATOR, calculator);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
        Log.i(TAG, "onRestoreInstanceState");
        if (outputView != null) {
            outputView.setText(calculator.getExpression());
        }
        calculator = (Calculator) savedState.getSerializable(CALCULATOR);

        if (calculator != null) {
            outputView.setText(calculator.getExpression());
        }
    }
}