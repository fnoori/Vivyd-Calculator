package ca.vivyd.vivydcalculator.utilities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import ca.vivyd.vivydcalculator.calc_logic.CalculatorButtons;
import ca.vivyd.vivydcalculator.calc_logic.CustomOperators;

/**
 * Created by Farzam on 2016-06-23.
 *
 * A general multi-purpose class
 */
public class CalculatorUtilities {
    private Context context;
    public enum ALL_BUTTONS {
        ADD, SUB, MUL, DIV, NUM, DOT, BRACKET_OPEN, BRACKET_CLOSE,
        SIN, COS, TAN, LOG, LN, PI, EULER, SQRT, POWER,
        FACT, SQR, NRT, PRCNT, CBRT, INVERSE, CSTM, EE, DEG_RAND, EQUAL, DEL, CLR
    }

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
            case CBRT:
                return cursorLocation+2;
            case LN:
            case INVERSE:
                return cursorLocation+3;
            case NRT:
                return cursorLocation;
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

    public void saveToSharedPrefs(String sharedPrefsVarName, String sharedPrefsValue){
        SharedPreferences prefs;
        SharedPreferences.Editor editor;

        prefs = context.getSharedPreferences("CalcData", Context.MODE_PRIVATE);
        editor = prefs.edit();
        editor.putString(sharedPrefsVarName, sharedPrefsValue);
        editor.apply();
    }

    public String getFromSharedPrefs(String varToGet){
        SharedPreferences prefs = context.getSharedPreferences("CalcData", context.MODE_PRIVATE);
        return prefs.getString(varToGet, "");
    }

    public String convertToScientific(String incoming){
        double value = Double.parseDouble(incoming);
        NumberFormat formatter = new DecimalFormat();

        if(incoming.length() > 6) {
            formatter = new DecimalFormat("0.####E0");
            return formatter.format(value);
        }else{
            return incoming;
        }
    }

    public boolean checkIfMoreOperandIsPossible(String prevInput, boolean isExceptionToRule) {
        Log.d("PREV_INPUT", prevInput);
        return (prevInput.equals("+") ||
                prevInput.equals("/") || prevInput.equals("*") ||
                prevInput.equals("×") || prevInput.equals("÷") ||
                prevInput.equals("%(*1)")) && !isExceptionToRule;
    }
}
