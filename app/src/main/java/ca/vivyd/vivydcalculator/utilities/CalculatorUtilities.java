package ca.vivyd.vivydcalculator.utilities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.util.Log;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.prefs.PreferenceChangeEvent;

import ca.vivyd.vivydcalculator.MainActivity;
import ca.vivyd.vivydcalculator.calc_logic.CalculatorButtons;
import ca.vivyd.vivydcalculator.calc_logic.CustomOperators;

/**
 * Created by Farzam on 2016-06-23.
 *
 * A general multi-purpose class
 */
public class CalculatorUtilities {
    private Context context;

    public static final String[] ALL_BUTTS = {"ADD", "SUB", "MUL", "DIV", "NUM", "DOT", "BRACKET_OPEN", "BRACKET_CLOSE",
            "SIN", "COS", "TAN", "LOG", "LN", "PI", "EULER", "SQRT", "POWER",
            "FACT", "SQR", "NRT", "PRCNT", "CBRT", "INVERSE", "CSTM", "EE", "ANS", "DEG_RAND", "EQUAL", "DEL", "CLR"};
    public static final String[] DEG_RAD = {"DEG", "RAD"};

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
                .replace("log10(", "log(").replace("§", "ᴇ")
                .replace("cosd(", "cos(").replace("sind(", "sin(")
                .replace("tand(", "tan(");
    }

    public int determineCursorLocation(String type, int cursorLocation,
                                       String valueToAppend){
        if (type.equals(ALL_BUTTS[11]) || type.equals(ALL_BUTTS[8]) || type.equals(ALL_BUTTS[9]) || type.equals(ALL_BUTTS[10])) {
            return cursorLocation + 4;
        } else if (type.equals(ALL_BUTTS[12]) || type.equals(ALL_BUTTS[22])) {
            return cursorLocation + 3;
        } else if (type.equals(ALL_BUTTS[18]) ||type.equals(ALL_BUTTS[21]) || type.equals(ALL_BUTTS[19]) || type.equals(ALL_BUTTS[15])) {
            return cursorLocation + 2;
        } else if (type.equals(ALL_BUTTS[23]) || type.equals(ALL_BUTTS[25])) {
            return cursorLocation + valueToAppend.length();
        }else {
            return cursorLocation + 1;
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

    public String convertToScientific(String incoming, LinearLayout display){
        double value = Double.parseDouble(incoming);
        NumberFormat formatter = new DecimalFormat();

        Paint paint = new Paint();
        paint.setTextSize(MainActivity.defaultTxtSize*MainActivity.scale);
        float string_pxLen = paint.measureText(incoming);

        Log.d("SCITIME", "string_pxLen =" + string_pxLen);
        if(string_pxLen > display.getWidth() - MainActivity.pixelCushion) {
            formatter = new DecimalFormat("0.######E0");
            return formatter.format(value);
        }else{
            return incoming;
        }
    }

    public boolean checkIfMoreOperandIsPossible(String prevInput, String currentInputType, String prevInputType,
                                                boolean isExceptionToRule) {
        return (prevInputType != null) && !((prevInputType.equals(ALL_BUTTS[0]) ||
                prevInputType.equals(ALL_BUTTS[3]) ||
                prevInputType.equals(ALL_BUTTS[2]) ||
                prevInputType.equals(ALL_BUTTS[20]))&&
                currentInputType.equals(ALL_BUTTS[1]))&&
                (prevInputType.equals(ALL_BUTTS[0]) ||
                prevInputType.equals(ALL_BUTTS[3]) ||
                prevInputType.equals(ALL_BUTTS[2]) ||
                prevInputType.equals(ALL_BUTTS[20])||
                prevInputType.equals(ALL_BUTTS[1]))&&
                !isExceptionToRule;
    }
}
