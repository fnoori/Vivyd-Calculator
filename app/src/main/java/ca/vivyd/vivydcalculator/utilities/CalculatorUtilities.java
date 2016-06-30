package ca.vivyd.vivydcalculator.utilities;

import android.util.Log;
import android.widget.TextView;

import ca.vivyd.vivydcalculator.calc_logic.CalculatorButtons;
import ca.vivyd.vivydcalculator.calc_logic.CustomOperators;

/**
 * Created by Farzam on 2016-06-23.
 *
 * A general multi-purpose class
 */
public class CalculatorUtilities {
    public enum ALL_BUTTONS {
        ADD, SUB, MUL, DIV, NUM, DOT, BRACKET_OPEN, BRACKET_CLOSE,
        SIN, COS, TAN, LOG, LN, PI, EULER, SQRT, POWER,
        FACT, SQR, NRT, PRCNT, CBRT, INVERSE, CSTM, EE, DEG_RAND, EQUAL, DEL, CLR
    }

    public CalculatorUtilities(){}

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
                .replace("%(×1)", "%");
    }

    public String repalaceForEquViewDisplay(String incoming){
        return incoming
                .replace("*", "×").replace("/", "÷")
                .replace("-", "−").replace("sqrt(", "√(")
                .replace("%(×1)", "%").replace("log(", "ln(")
                .replace("log10(", "log(").replace("§", "ᴇ");
    }

    public int determineCursorLocation(CalculatorButtons.ALL_BUTTONS type, int cursorLocation){
        switch (type){
            case LOG:
            case SIN:
            case COS:
            case TAN:
                return cursorLocation+4;
            case LN:
                return cursorLocation+3;
            case SQRT:
                return cursorLocation+2;
            case SQR:
            case CBRT:
            case INVERSE:
            case NRT:
                return cursorLocation;
            default:
                return cursorLocation+1;
        }

/*
        if(type.equals(CalculatorButtons.ALL_BUTTONS.LOG)
                || type.equals(CalculatorButtons.ALL_BUTTONS.SIN)
                || type.equals(CalculatorButtons.ALL_BUTTONS.COS)
                || type.equals(CalculatorButtons.ALL_BUTTONS.TAN)){
            return cursorLocation+4;
        }else if(type.equals(CalculatorButtons.ALL_BUTTONS.LN)){
            return cursorLocation+3;
        }else if(type.equals(CalculatorButtons.ALL_BUTTONS.SQRT)){
            return cursorLocation+2;
        }else if(type.equals(CalculatorButtons.ALL_BUTTONS.SQR)
                || type.equals(CalculatorButtons.ALL_BUTTONS.CBRT) || type.equals(CalculatorButtons.ALL_BUTTONS.INVERSE)
                || type.equals(CalculatorButtons.ALL_BUTTONS.NRT)){
            return cursorLocation;
        }else{
            return cursorLocation+1;
        }
*/
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

    public boolean checkIfMoreOperandIsPossible(String prevInput, boolean isExceptionToRule) {
        return (prevInput.equals("+") ||
                prevInput.equals("/") || prevInput.equals("*") ||
                prevInput.equals("×") || prevInput.equals("÷")) && !isExceptionToRule;
    }
}
