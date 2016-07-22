package ca.vivyd.vivydcalculator.utilities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.prefs.PreferenceChangeEvent;

import ca.vivyd.vivydcalculator.calc_logic.CalculatorButtons;
import ca.vivyd.vivydcalculator.calc_logic.CustomOperators;

/**
 * Created by Farzam on 2016-06-23.
 *
 * A general multi-purpose class
 */
public class CalculatorUtilities {
    private Context context;

    public CalculatorUtilities(Context context){
        this.context = context;
    }

    public String replaceForCalculations(String incoming){
        return incoming
                .replace("log(", "log10(").replace("ln(", "log(")
                .replace("×", "*").replace("÷", "/")
                .replace("√(", "sqrt(").replace("−", "-")
                .replace("%", "%(*1)").replace("ᴇ", "§");
    }

    public String replaceForAnsViewDisplay(String incoming){
        return incoming
                .replace("*", "×").replace("/", "÷")
                .replace("-", "−").replace("sqrt(", "√(")
                .replace("%(×1)", "%").replace("E", "ᴇ");
    }

    public String repalaceForEquViewDisplay(String incoming){
        return incoming
                .replace("*", "×").replace("/", "÷")
                .replace("-", "−").replace("sqrt(", "√(")
                .replace("%(×1)", "%").replace("log(", "ln(")
                .replace("log10(", "log(").replace("§", "ᴇ");
    }

    public int determineCursorLocation(CalculatorButtons.ALL_BUTTONS type, int cursorLocation,
                                       String valueToAppend){
        switch (type){
            case LOG:
            case SIN:
            case COS:
            case TAN:
                return cursorLocation+4;
            case SQRT:
            case SQR:
            case LN:
            case INVERSE:
                return cursorLocation+3;
            case CBRT:
            case NRT:
                return cursorLocation+2;
            case CSTM:
            case ANS:
                return cursorLocation + valueToAppend.length();
            default:
                return cursorLocation+1;
        }
    }

    public String replaceForDegrees(String stringToModify){
        return stringToModify.replace("sin(", "sind(")
                .replace("cos(", "cosd("). replace("tan(", "tand(");
    }

    public String replacePercentageForCalc(String stringToModify){
        return stringToModify.replace("%", "(*1)");
    }

    public String replacePercentageForDisplay(String stringToModify){
        return stringToModify.replace("(*1)", "%");
    }

    public boolean isBracketCorrect(int openBrace, int closeBrace){
        return openBrace == closeBrace;
    }

    public void postEqualLogic(boolean isAnswer, CustomOperators customOperators,
                               int openBrace, int closeBrace, TextView rightBraceCounter,
                               TextView leftBraceCounter, String previousInput){
        isAnswer = true;
        customOperators.setIsComplexPercentage(false);
        openBrace = 0;
        closeBrace = 0;
        rightBraceCounter.setText(String.valueOf(openBrace));
        leftBraceCounter.setText(String.valueOf(closeBrace));
        previousInput = "blah";
    }

    public String getPreviousInput(String incoming){
        if(incoming != null){
            Log.d("INCOMING", incoming);
        }else{
            Log.d("INCOMING", "NULL");
        }
        String toReturn;
        if(incoming != null && incoming.length() > 0){
            toReturn = String.valueOf(incoming.charAt(incoming.length()-1));
        }else{
            toReturn = "blah";
        }
        return toReturn;
    }

    public boolean isPreviousValueNumeric(String incoming) {
        return (incoming.length() > 0) && StringUtils.isNumeric(String.valueOf(incoming.charAt(incoming.length() - 1)));
    }

    public String convertToScientific(String incoming){
        double value = Double.parseDouble(incoming);
        NumberFormat formatter = new DecimalFormat();

        if(incoming.length() > 15) {
            formatter = new DecimalFormat("0.####E0");
            return formatter.format(value);
        }else{
            return incoming;
        }
    }

    public boolean checkIfMoreOperandIsPossible(String prevInput, CalculatorButtons.ALL_BUTTONS currentInput, CalculatorButtons.ALL_BUTTONS prevInputEnum,
                                                boolean isExceptionToRule) {

        if(prevInputEnum != null){Log.d("PREV_INPUT", prevInputEnum.toString());}
        Log.d("CURR_INPUT", currentInput.toString());

        return (prevInputEnum != null) && !((prevInputEnum.equals(CalculatorButtons.ALL_BUTTONS.ADD) ||

                prevInputEnum.equals(CalculatorButtons.ALL_BUTTONS.DIV) ||

                prevInputEnum.equals(CalculatorButtons.ALL_BUTTONS.MUL) ||

                //prevInput.equals("×") || prevInput.equals("÷") ||

                prevInputEnum.equals(CalculatorButtons.ALL_BUTTONS.PRCNT))
                &&
                currentInput.equals(CalculatorButtons.ALL_BUTTONS.SUB))
                &&
                (prevInputEnum.equals(CalculatorButtons.ALL_BUTTONS.ADD) ||

                prevInputEnum.equals(CalculatorButtons.ALL_BUTTONS.DIV) ||

                prevInputEnum.equals(CalculatorButtons.ALL_BUTTONS.MUL) ||

                //prevInput.equals("×") || prevInput.equals("÷") ||

                prevInputEnum.equals(CalculatorButtons.ALL_BUTTONS.PRCNT)||

                prevInputEnum.equals(CalculatorButtons.ALL_BUTTONS.SUB) /*|| prevInput.equals("−")*/)
                &&
                !isExceptionToRule;
        /*
        return prevInputEnum != null && !(prevInputEnum.equals(CalculatorButtons.ALL_BUTTONS.ADD) ||
                prevInputEnum.equals(CalculatorButtons.ALL_BUTTONS.DIV) ||
                prevInputEnum.equals(CalculatorButtons.ALL_BUTTONS.MUL) ||
                prevInputEnum.equals(CalculatorButtons.ALL_BUTTONS.PRCNT)
                && (currentInput != null && (currentInput.equals(CalculatorButtons.ALL_BUTTONS.SUB))))
                && (prevInputEnum.equals(CalculatorButtons.ALL_BUTTONS.ADD) ||
                prevInputEnum.equals(CalculatorButtons.ALL_BUTTONS.PRCNT) ||
                prevInputEnum.equals(CalculatorButtons.ALL_BUTTONS.SUB)) && !isExceptionToRule;


                        return !((prevInput.equals("+") ||
                        prevInput.equals("/") || prevInput.equals("*") ||
                        prevInput.equals("×") || prevInput.equals("÷") ||
                        prevInput.equals("%(*1)"))
                        && currentInput.equals(CalculatorButtons.ALL_BUTTONS.SUB))
                        && (prevInput.equals("+") ||
                        prevInput.equals("/") || prevInput.equals("*") ||
                        prevInput.equals("×") || prevInput.equals("÷") || prevInput.equals("%(*1)")
                        || prevInput.equals("-") || prevInput.equals("−")) && !isExceptionToRule;
         */
    }
}
