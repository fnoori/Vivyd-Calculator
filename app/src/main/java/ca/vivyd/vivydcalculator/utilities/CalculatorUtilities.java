package ca.vivyd.vivydcalculator.utilities;

import android.util.Log;
import android.widget.TextView;

import ca.vivyd.vivydcalculator.calc_logic.CustomOperators;

/**
 * Created by Farzam on 2016-06-23.
 *
 * A general multi-purpose class
 */
public class CalculatorUtilities {

    public CalculatorUtilities(){}

    public String replaceForCalculations(String incoming){
        return incoming
                .replace("log(", "log10(").replace("ln(", "log(")
                .replace("×", "*").replace("÷", "/")
                .replace("√(", "sqrt(").replace("−", "-")
                .replace("%", "%(*1)").replace("ᴇ", "e");
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
                .replace("log10(", "log(").replace("e", "ᴇ");
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
