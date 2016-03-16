package com.nimda.calculator.calc.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nimda.calculator.R;
import com.nimda.calculator.calc.logic.Calculator;

public class CalculatorActivity extends Activity {

    private static final String CALCULATOR = "CALCULATOR";


    private static final int VIBRATE_TIME = 20;
    private static final String TAG = "MY_LOG" ;
    private Calculator calculator;
    private Vibrator vibrator;

    private TextView outputView;

    public void onButtonClick(View v) {

        if (v !=null) {
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
                }
        }
        updateOutput();
    }

    class VibratorTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if (event !=null && event.getAction() == MotionEvent.ACTION_DOWN)
                vibrator.vibrate(VIBRATE_TIME);
            return false;
        }
    }

    private void updateOutput (){
        outputView.setText(calculator.getExpression());
    }


    /*
    *
    * This methods called right before we change orientation
    *
    * */

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.i(TAG, "onSaveInstanceState");
   /*     CharSequence textResult = calculator.getExpression();

        final TextView outputText = (TextView) findViewById(R.id.outputView);
        outState.putCharSequence("savedText", textResult);*/

        outState.putSerializable(CALCULATOR, calculator);
    }

    /*
    *
    * Display values in the UI that were saved right before orientation changed
    * */


    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
        Log.i(TAG, "onRestoreInstanceState");

  /*      final TextView outputText =
                (TextView) findViewById(R.id.outputView);

        CharSequence textResult = calculator.getExpression();

        outputText.setText(textResult);*/

        //updateOutput();
        outputView.setText(calculator.getExpression());
        calculator = (Calculator) savedState.getSerializable(CALCULATOR);
     //   assert calculator != null;
        if (calculator !=null) {
            outputView.setText(calculator.getExpression());
            //outputView.setText(calculator.toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        Typeface tf_lite = Typeface.createFromAsset(getAssets(),"fonts/Lato-Light.ttf");
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/Lato-Thin.ttf");
        calculator = new Calculator(this);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        outputView = (TextView) findViewById(R.id.outputView);
        outputView.setTypeface(tf_lite, Typeface.BOLD);

        /*
        * Numeric Buttons
        * */
        Button buttonNine = (Button) findViewById(R.id.buttonNine);
        buttonNine.setTypeface(tf, Typeface.BOLD);
        Button buttonEight = (Button) findViewById(R.id.buttonEight);
        buttonEight.setTypeface(tf, Typeface.BOLD);
        Button buttonSeven = (Button) findViewById(R.id.buttonSeven);
        buttonSeven.setTypeface(tf, Typeface.BOLD);
        Button buttonSix = (Button) findViewById(R.id.buttonSix);
        buttonSix.setTypeface(tf, Typeface.BOLD);
        Button buttonFive = (Button) findViewById(R.id.buttonFive);
        buttonFive.setTypeface(tf, Typeface.BOLD);
        Button buttonFour = (Button) findViewById(R.id.buttonFour);
        buttonFour.setTypeface(tf, Typeface.BOLD);
        Button buttonThree = (Button) findViewById(R.id.buttonThree);
        buttonThree.setTypeface(tf, Typeface.BOLD);
        Button buttonTwo = (Button) findViewById(R.id.buttonTwo);
        buttonTwo.setTypeface(tf, Typeface.BOLD);
        Button buttonOne = (Button) findViewById(R.id.buttonOne);
        buttonOne.setTypeface(tf, Typeface.BOLD);
        Button buttonZero = (Button) findViewById(R.id.buttonZero);
        buttonZero.setTypeface(tf, Typeface.BOLD);


        ////


        Button buttonRightArrow = (Button) findViewById(R.id.buttonRightArrow);
        buttonRightArrow.setTypeface(tf, Typeface.BOLD);
        Button buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonClear.setTypeface(tf, Typeface.BOLD);
        Button buttonDiv = (Button) findViewById(R.id.buttonDiv);
        buttonDiv.setTypeface(tf, Typeface.BOLD);
        Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        buttonMultiply.setTypeface(tf, Typeface.BOLD);
        Button buttonSubtract = (Button) findViewById(R.id.buttonSubtract);
        buttonSubtract.setTypeface(tf, Typeface.BOLD);
        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setTypeface(tf, Typeface.BOLD);
        Button buttonPercent = (Button) findViewById(R.id.buttonPercent);
        buttonPercent.setTypeface(tf, Typeface.BOLD);
        Button buttonDecimal = (Button) findViewById(R.id.buttonDecimal);
        buttonDecimal.setTypeface(tf, Typeface.BOLD);

        ///
        Button buttonC = (Button) findViewById(R.id.buttonClear);
        buttonC.setTypeface(tf, Typeface.BOLD);

        Button buttonEquals = (Button) findViewById(R.id.buttonEquals);
        buttonEquals.setTypeface(tf, Typeface.BOLD);

        addVibrateListenersToButtons((ViewGroup) findViewById(R.id.rootView), new VibratorTouchListener());

    }

    private void addVibrateListenersToButtons(ViewGroup root, VibratorTouchListener listener){
        final int childCount = root.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = root.getChildAt(i);

            if (child instanceof Button) {
                child.setOnTouchListener(listener);
            } else if (child instanceof ViewGroup) {
                addVibrateListenersToButtons((ViewGroup)child,listener);
            }
        }
    }
}
