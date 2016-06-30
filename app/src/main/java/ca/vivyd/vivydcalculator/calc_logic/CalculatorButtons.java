package ca.vivyd.vivydcalculator.calc_logic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.graphics.drawable.TransitionDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.operator.Operator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

import ca.vivyd.vivydcalculator.MainActivity;
import ca.vivyd.vivydcalculator.R;
import ca.vivyd.vivydcalculator.menu.MenuActivity;
import ca.vivyd.vivydcalculator.sqlite_database.DatabaseTable;
import ca.vivyd.vivydcalculator.sqlite_database.HistoryData;
import ca.vivyd.vivydcalculator.utilities.CalculatorUtilities;

/**
 * Created by Farzam on 2016-05-04.
 *
 * Listeners, button logic, error handling ...
 */
public class CalculatorButtons implements View.OnClickListener, View.OnTouchListener, View.OnLongClickListener {
    private static final String[] NUMBERS_ARRAY = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "."};
    private static final String[] OPERANDS_ARRAY = {"+", "-", "/", "*", "%", "(", ")"};
    private static final String[] ADVANCED_OPERANDS_ARRAY = {"sin(", "cos(", "tan(", "log(", "ln(", "π",
                                                        "e", "sqrt(", "|=", "^", "^2", "!", "^3", "^-1", "ᴇ"};
    private static final String CUSTOM_BUTTON = "";
    private static final String[] VALUES_NOT_ALLOWED_BEFORE = {"+", "−", "/", "*", "%", "(","sin(",
                                                        "cos(", "tan(", "log(", "ln(", "sqrt(", "|="};

    private static final String[] tmp = {"+", "−", "/", "*"};

    private static final String BLANK_STRING = "";
    private static final String NULL_STRING = null;
    private static final String DEGREE = "DEG";
    private static final String RADIAN = "RAD";

    private enum ALL_BUTTONS {
        ADD, SUB, MUL, DIV, NUM, DOT, BRACKET_OPEN, BRACKET_CLOSE,
        SIN, COS, TAN, LOG, LN, PI, EULER, SQRT, POWER,
        FACT, SQR, NRT, PRCNT, CBRT, INVERSE, CSTM, EE, DEG_RAND, EQUAL, DEL, CLR
    }
    private enum BASIC_AND_ADVANCED_OPERANDS {
        ADD, SUB, MUL, DIV, BRACKET_OPEN, SIN, COS, TAN, LOG, LN,
        SQRT, NRT
    }
    private enum DEG_RAD {DEG, RAD}

    private Context context;
    private EditText answerView;
    private TextView equationView;
    private TextView rightBraceCounter;
    private TextView leftBraceCounter;
    private Button var1Button;
    private Button var2Button;
    private Button var3Button;
    private Button degRandButton;
    private String expressionEvalString;
    private String expressionDisplayString;

    // Playing with this, to see if performance improves
    private String expressionToEvaluate;

    private String solution;
    private String previousInput;
    private String[] userDefValue1;
    private String[] userDefValue2;
    private String[] userDefValue3;
    private DEG_RAD trigType;
    private int cursorLocation;
    private int dotCounter;
    private int openBrace;
    private int closeBrace;
    private boolean isAnswer;
    private HistoryData historyData;
    private CustomOperators customOperators;
    private CalculatorUtilities calculatorUtilities;
    private UserDefinedButtons userDefButtons;

    public CalculatorButtons(Context context, EditText answerView,
                             TextView equationView, ArrayList<Button> commonButtons,
                             ArrayList<Button> commonOperands, TextView leftBraceCounter,
                             TextView rightBraceCounter, Button degRandButton){
        this.context = context;
        this.answerView = answerView;
        this.equationView = equationView;
        this.leftBraceCounter = leftBraceCounter;
        this.rightBraceCounter = rightBraceCounter;
        this.degRandButton = degRandButton;

        expressionDisplayString = null;
        expressionEvalString = null;
        expressionToEvaluate = null;
        solution = null;
        userDefValue1 = new String[2];
        userDefValue2 = new String[2];
        userDefValue3 = new String[2];
        previousInput = "blah";
        trigType = DEG_RAD.RAD;
        cursorLocation = 0;
        dotCounter = 0;
        openBrace = 0;
        closeBrace = 0;
        isAnswer = false;

        historyData = new HistoryData(context);
        historyData.clearDatabase();
        customOperators = new CustomOperators();
        calculatorUtilities = new CalculatorUtilities();
        userDefButtons = new UserDefinedButtons(context);

        for(Button curr : commonButtons){
            curr.setOnClickListener(this);
            curr.setOnTouchListener(this);
        }

        for(Button curr : commonOperands){
            curr.setOnClickListener(this);
            curr.setOnTouchListener(this);
        }
        equationView.setOnClickListener(this);
        degRandButton.setOnClickListener(this);

        MainActivity.afterInitAllButtonsTime = (int) System.currentTimeMillis();
        int totalTime = MainActivity.afterInitAllButtonsTime - MainActivity.startTime;
        Toast.makeText(context, "Total time to init all compenents: " + totalTime, Toast.LENGTH_LONG).show();
    }

    /**
     * This constructor should only be used for testing purposes
     * */
    public CalculatorButtons(){
        expressionDisplayString = null;
        expressionEvalString = null;
        expressionToEvaluate = null;
        calculatorUtilities = new CalculatorUtilities();
        solution = null;

        trigType = DEG_RAD.RAD;

        openBrace = 0;
        closeBrace = 0;
        isAnswer = false;
        customOperators = new CustomOperators();
    }

    // AMAIR: Had to move the stuff from onClick to onTouch for animations to work. yolo.

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /*
            case R.id.sineButton:
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[0], ALL_BUTTONS.SIN, true);
                break;
            case R.id.cosineButton:
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[1], ALL_BUTTONS.COS, true);
                break;
            case R.id.tangentButton:
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[2], ALL_BUTTONS.TAN, true);
                break;
            case R.id.logButton:
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[3], ALL_BUTTONS.LOG, true);
                break;
            case R.id.lnButton:
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[4], ALL_BUTTONS.LN, true);
                break;
            case R.id.piButton:
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[5], ALL_BUTTONS.PI, true);
                break;
            case R.id.eButton:
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[6], ALL_BUTTONS.EULER, true);
                break;
            case R.id.sqrtButton:
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[7], ALL_BUTTONS.SQRT, true);
                break;
            case R.id.usrDefinedRoot:
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[8], ALL_BUTTONS.NRT, true);
                break;
            case R.id.pwrButton:
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[9], ALL_BUTTONS.POWER, true);
                break;
            case R.id.sqrButton:
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[10], ALL_BUTTONS.SQR, true);
                break;
            case R.id.facttButton:
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[11], ALL_BUTTONS.FACT, true);
                break;
            case R.id.cubButton:
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[12], ALL_BUTTONS.CBRT, true);
                break;
            case R.id.oneOverxButton:
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[13], ALL_BUTTONS.INVERSE, true);
                break;
            case R.id.var1Button:
                if(userDefValue1[0] != null || userDefValue1[1] != null){
                    addToExpressionToBeEvaluated(userDefValue1[1], ALL_BUTTONS.CSTM, true);
                }
                break;
            case R.id.var2Button:
                if(userDefValue2[0] != null || userDefValue2[1] != null){
                    addToExpressionToBeEvaluated(userDefValue2[1], ALL_BUTTONS.CSTM, true);
                }
                break;
            case R.id.var3Button:
                if(userDefValue3[0] != null || userDefValue3[1] != null){
                    addToExpressionToBeEvaluated(userDefValue3[1], ALL_BUTTONS.CSTM, true);
                }
                break;
                */
            case R.id.degRandButton:
                if(trigType.equals(DEG_RAD.DEG)){
                    trigType = DEG_RAD.RAD;
                    degRandButton.setText(RADIAN);
                }else{
                    trigType = DEG_RAD.DEG;
                    degRandButton.setText(DEGREE);
                }
                break;
            case R.id.eqnView:
                Log.d("EQUATION_VIEW", equationView.getText().toString());
                if(equationView.getText() != BLANK_STRING){
                    countNumberOfBrackets(equationView.getText().toString());
                    answerView.setText(BLANK_STRING);
                    answerView.setText(equationView.getText().toString());
                    answerView.setSelection(answerView.getText().length());

                    expressionToEvaluate = calculatorUtilities.replaceForCalculations(
                            answerView.getText().toString()
                    );

                    //expressionEvalString = answerView.getText().toString();
                    //expressionEvalString = calculatorUtilities.replaceForCalculations(expressionEvalString);

                    isAnswer = !isAnswer;
                }
                break;
            default:
                break;
        }
    }

    public void countNumberOfBrackets(String equationView){
        openBrace = 0;
        closeBrace = 0;
        char[] equationArr = equationView.toCharArray();
        for(char curr : equationArr){
            Log.d("CURR", curr+"");
            if(curr == '('){openBrace++;}
            else if(curr == ')'){closeBrace++;}
        }

        leftBraceCounter.setText(String.valueOf(openBrace));
        rightBraceCounter.setText(String.valueOf(closeBrace));
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.var1Button:
                userDefButtons.launchUserInputDialog(this, var1Button);
                break;
            case R.id.var2Button:
                userDefButtons.launchUserInputDialog(this, var2Button);
                break;
            case R.id.var3Button:
                userDefButtons.launchUserInputDialog(this, var3Button);
                break;
            default:
                break;
        }
        return false;
    }

    public void setUserDefinedValues(Button whichButton, String varName, String varValue){
        SharedPreferences prefs;
        SharedPreferences.Editor editor;
        switch (whichButton.getId()){
            case R.id.var1Button:
                userDefValue1[0] = varName;
                userDefValue1[1] = varValue;
                var1Button.setText(userDefValue1[0]);
                prefs = context.getSharedPreferences("CalcData", Context.MODE_PRIVATE);
                editor = prefs.edit();
                editor.putString("Var1Name", userDefValue1[0]);
                editor.putString("Var1Value", userDefValue1[1]);
                editor.apply();
                break;
            case R.id.var2Button:
                userDefValue2[0] = varName;
                userDefValue2[1] = varValue;
                var2Button.setText(userDefValue2[0]);
                prefs = context.getSharedPreferences("CalcData", Context.MODE_PRIVATE);
                editor = prefs.edit();
                editor.putString("Var2Name", userDefValue2[0]);
                editor.putString("Var2Value", userDefValue2[1]);
                editor.apply();
                break;
            case R.id.var3Button:
                userDefValue3[0] = varName;
                userDefValue3[1] = varValue;
                var3Button.setText(userDefValue3[0]);
                prefs = context.getSharedPreferences("CalcData", Context.MODE_PRIVATE);
                editor = prefs.edit();
                editor.putString("Var3Name", userDefValue3[0]);
                editor.putString("Var3Value", userDefValue3[1]);
                editor.apply();
                break;
            default:
                break;
        }

    }

    // AMAIR: Had to move the stuff from onClick to onTouch for animations to work. yolo.
    int startTran = 50;
    int endTran = 300;
    int fast_endTran = 150;
    int transComplete = 0;
    double scalefactor = 2;
    private Rect rect;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        TransitionDrawable transition = (TransitionDrawable) v.getBackground();
        switch (v.getId()) {
            case R.id.zeroButton:
                animBtnLogic(v, transition, event, "num", 0);
                break;
            case R.id.oneButton:
                animBtnLogic(v, transition, event, "num", 1);
                break;
            case R.id.twoButton:
                animBtnLogic(v, transition, event, "num", 2);
                break;
            case R.id.threeButton:
                animBtnLogic(v, transition, event, "num", 3);
                break;
            case R.id.fourButton:
                animBtnLogic(v, transition, event, "num", 4);
                break;
            case R.id.fiveButton:
                animBtnLogic(v, transition, event, "num", 5);
                break;
            case R.id.sixButton:
                animBtnLogic(v, transition, event, "num", 6);
                break;
            case R.id.sevenButton:
                animBtnLogic(v, transition, event, "num", 7);
                break;
            case R.id.eightButton:
                animBtnLogic(v, transition, event, "num", 8);
                break;
            case R.id.nineButton:
                animBtnLogic(v, transition, event, "num", 9);
                break;
            case R.id.dotButton:
                animBtnLogic(v, transition, event, "num", 10);
                break;
            case R.id.addButton:
                animBtnLogic(v, transition, event, "add", 0);
                break;
            case R.id.minusButton:
                animBtnLogic(v, transition, event, "sub", 0);
                break;
            case R.id.multButton:
                animBtnLogic(v, transition, event, "mul", 0);
                break;
            case R.id.divButton:
                animBtnLogic(v, transition, event, "div", 0);
                break;
            case R.id.prcntButton:
                animBtnLogic(v, transition, event, "prcnt", 0);
                break;
            case R.id.openBrace:
                animBtnLogic(v, transition, event, "open_brace", 0);
                break;
            case R.id.closeBrace:
                animBtnLogic(v, transition, event, "close_brace", 0);
                break;
            case R.id.clrButton:
                animBtnLogic(v, transition, event, "clr", 0);
                break;
            case R.id.delButton:
                animBtnLogic(v, transition, event, "del", 0);
                break;
            case R.id.eqlButton:
                animBtnLogic(v, transition, event, "eql", 0);
                break;
            case R.id.menuButton:
                animBtnLogic(v, transition, event, "menu", 0);
                break;
            case R.id.sineButton:
                animBtnLogic(v, transition, event, "sin", 0);
                break;
            case R.id.cosineButton:
                animBtnLogic(v, transition, event, "cos", 0);
                break;
            case R.id.tangentButton:
                animBtnLogic(v, transition, event, "tan", 0);
                break;
            case R.id.logButton:
                animBtnLogic(v, transition, event, "log", 0);
                break;
            case R.id.lnButton:
                animBtnLogic(v, transition, event, "ln", 0);
                break;
            case R.id.piButton:
                animBtnLogic(v, transition, event, "pi", 0);
                break;
            case R.id.eButton:
                animBtnLogic(v, transition, event, "euler", 0);
                break;
            case R.id.sqrtButton:
                animBtnLogic(v, transition, event, "sqrt", 0);
                break;
            case R.id.usrDefinedRoot:
                animBtnLogic(v, transition, event, "nrt", 0);
                break;
            case R.id.pwrButton:
                animBtnLogic(v, transition, event, "power", 0);
                break;
            case R.id.sqrButton:
                animBtnLogic(v, transition, event, "sqr", 0);
                break;
            case R.id.facttButton:
                animBtnLogic(v, transition, event, "fact", 0);
                break;
            case R.id.cubButton:
                animBtnLogic(v, transition, event, "cbrt", 0);
                break;
            case R.id.oneOverxButton:
                animBtnLogic(v, transition, event, "inverse", 0);
                break;
            case R.id.powerOfTenButton:
                animBtnLogic(v, transition, event, "ᴇ", 0);
                break;
            case R.id.var1Button:
                animBtnLogic(v, transition, event, "user1", 0);
                break;
            case R.id.var2Button:
                animBtnLogic(v, transition, event, "user2", 0);
                break;
            case R.id.var3Button:
                animBtnLogic(v, transition, event, "user3", 0);
                break;
            default:
                break;
        }
        return false;
    }


    public void animBtnLogic(View v, TransitionDrawable transition, MotionEvent event, String type, int num) {
        SharedPreferences prefs = context.getSharedPreferences("CalcData", Context.MODE_PRIVATE);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            transComplete = 0;
            rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
            transition.startTransition(startTran);
        }
        else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (transComplete == 1){
                return;
            }
            switch (type) {
                case "num":
                    addToExpressionToBeEvaluated(NUMBERS_ARRAY[num], ALL_BUTTONS.NUM, true);
                    break;
                case "add":
                    addToExpressionToBeEvaluated(OPERANDS_ARRAY[0], ALL_BUTTONS.ADD, false);
                    break;
                case "sub":
                    addToExpressionToBeEvaluated(OPERANDS_ARRAY[1], ALL_BUTTONS.SUB, true);
                    break;
                case "mul":
                    addToExpressionToBeEvaluated(OPERANDS_ARRAY[3], ALL_BUTTONS.DIV, false);
                    break;
                case "div":
                    addToExpressionToBeEvaluated(OPERANDS_ARRAY[2], ALL_BUTTONS.MUL, false);
                    break;
                case "prcnt":
                    addToExpressionToBeEvaluated(OPERANDS_ARRAY[4], ALL_BUTTONS.PRCNT, false);
                    break;
                case "open_brace":
                    addToExpressionToBeEvaluated(OPERANDS_ARRAY[5], ALL_BUTTONS.BRACKET_OPEN, true);
                    break;
                case "close_brace":
                    addToExpressionToBeEvaluated(OPERANDS_ARRAY[6], ALL_BUTTONS.BRACKET_CLOSE, true);
                    break;
                case "del":
                    deleteButtonLogic();
                    break;
                case "clr":
                    clearButtonLogic();
                    break;
                case "eql":
                    equalButtonLogic();
                    break;
                case "menu":
                    Intent toMenu = new Intent();
                    toMenu.setClass(context, MenuActivity.class);
                    context.startActivity(toMenu);
                    // Apparently this next line is not best practice, but I don't know what else to do
                    // at the moment. AmairJ
                    ((Activity)context).overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    break;
                case "sin":
                    addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[0], ALL_BUTTONS.SIN, true);
                    break;
                case "cos":
                    addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[1], ALL_BUTTONS.COS, true);
                    break;
                case "tan":
                    addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[2], ALL_BUTTONS.TAN, true);
                    break;
                case "log":
                    addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[3], ALL_BUTTONS.LOG, true);
                    break;
                case "ln":
                    addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[4], ALL_BUTTONS.LN, true);
                    break;
                case "pi":
                    addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[5], ALL_BUTTONS.PI, true);
                    break;
                case "euler":
                    addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[6], ALL_BUTTONS.EULER, true);
                    break;
                case "sqrt":
                    addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[7], ALL_BUTTONS.SQRT, true);
                    break;
                case "nrt":
                    addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[8], ALL_BUTTONS.NRT, true);
                    break;
                case "power":
                    addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[9], ALL_BUTTONS.POWER, true);
                    break;
                case "sqr":
                    addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[10], ALL_BUTTONS.SQR, true);
                    break;
                case "fact":
                    addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[11], ALL_BUTTONS.FACT, true);
                    break;
                case "cbrt":
                    addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[12], ALL_BUTTONS.CBRT, true);
                    break;
                case "inverse":
                    addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[13], ALL_BUTTONS.INVERSE, true);
                    break;
                case "ᴇ":
                    addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[14], ALL_BUTTONS.EE, true);
                    break;
                case "user1":
                    if(!userDefValue1[0].equals("+") || !userDefValue1[1].equals("+")){
                        addToExpressionToBeEvaluated(userDefValue1[1], ALL_BUTTONS.CSTM, true);
                    }else{
                        userDefButtons.launchUserInputDialog(this, var1Button);
                    }
                    break;
                case "user2":
                    if(!userDefValue2[0].equals("+") || !userDefValue2[1].equals("+")){
                        addToExpressionToBeEvaluated(userDefValue2[1], ALL_BUTTONS.CSTM, true);
                    }else{
                        userDefButtons.launchUserInputDialog(this, var2Button);
                    }
                    break;
                case "user3":
                    if(!userDefValue3[0].equals("+") || !userDefValue3[1].equals("+")){
                        addToExpressionToBeEvaluated(userDefValue3[1], ALL_BUTTONS.CSTM, true);
                    }else{
                        userDefButtons.launchUserInputDialog(this, var3Button);
                    }
                    break;
            }
            transition.reverseTransition(endTran);
        }
        else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                if (transComplete != 1) {
                    transition.reverseTransition(fast_endTran);
                    transComplete = 1;
                }
            }
        }
    }

    public void addAdvanceOperands(ArrayList<Button> advancedOperands){
        SharedPreferences prefs = context.getSharedPreferences("CalcData", context.MODE_PRIVATE);
        for(Button curr : advancedOperands){
            if(curr.getId() == R.id.var1Button){
                var1Button = curr; curr.setOnLongClickListener(this);
                userDefValue1[0] = prefs.getString("Var1Name", "+");
                userDefValue1[1] = prefs.getString("Var1Value", "+");
                if(!userDefValue1[0].equals("+") && !userDefValue1[1].equals("+")){
                    curr.setText(userDefValue1[0]);
                }
            }
            else if(curr.getId() == R.id.var2Button){
                var2Button = curr; curr.setOnLongClickListener(this);
                userDefValue2[0] = prefs.getString("Var2Name", "+");
                userDefValue2[1] = prefs.getString("Var2Value", "+");
                if(!userDefValue2[0].equals("+") && !userDefValue2[1].equals("+")){
                    curr.setText(userDefValue2[0]);
                }
            }
            else if(curr.getId() == R.id.var3Button){
                var3Button = curr; curr.setOnLongClickListener(this);
                userDefValue3[0] = prefs.getString("Var3Name", "+");
                userDefValue3[1] = prefs.getString("Var3Value", "+");
                if(!userDefValue3[0].equals("+") && !userDefValue3[1].equals("+")){
                    curr.setText(userDefValue3[0]);
                }
            }
            curr.setOnTouchListener(this);
        }
    }

    public void deleteButtonLogic(){
        if(answerView.getText().length() > 0){
            int indexFrom = answerView.getSelectionStart()-1;
            int indexTo = answerView.getSelectionStart();


            if(answerView.getText().toString().contains(".")){dotCounter = 0;}
            if(answerView.getText().toString().charAt(indexFrom) == '('){
                openBrace--;
                leftBraceCounter.setText(String.valueOf(openBrace));
            }else if(answerView.getText().toString().charAt(indexFrom) == ')'){
                closeBrace--;
                rightBraceCounter.setText(String.valueOf(closeBrace));
            }

            expressionToEvaluate = calculatorUtilities.replaceForAnsViewDisplay(answerView.getText().toString().substring(0, indexFrom)
                    + answerView.getText().toString().substring(indexTo));
            answerView.setText(expressionToEvaluate);
            answerView.setSelection(indexFrom);
            expressionToEvaluate = calculatorUtilities.replaceForCalculations(expressionToEvaluate);


            if(expressionToEvaluate.length() > 0){
                Log.d("EXPRESSION_IN_DEL", expressionToEvaluate);
            }else{
                Log.d("EXPRESSION_IN_DEL", "NULL");
            }

            if(expressionToEvaluate.length() < 1){
                expressionToEvaluate = BLANK_STRING;
                answerView.setText(BLANK_STRING);
            }
            /*
            expressionDisplayString = answerView.getText().toString().substring(0, indexFrom)
                    + answerView.getText().toString().substring(indexTo);
            expressionEvalString = expressionDisplayString;
            answerView.setText(expressionDisplayString);
            answerView.setSelection(indexFrom);

            if(expressionDisplayString.length() == 1){
                expressionDisplayString = BLANK_STRING;
                expressionEvalString = BLANK_STRING;
                answerView.setText(BLANK_STRING);
            }
            */
        }
    }

    public void clearButtonLogic(){
        if(answerView.getText().length() < 1){equationView.setText(BLANK_STRING);}

        answerView.setText(BLANK_STRING);
        expressionEvalString = BLANK_STRING;
        expressionDisplayString = BLANK_STRING;
        expressionToEvaluate = BLANK_STRING;
        dotCounter = 0;
        openBrace = 0;
        closeBrace = 0;
        rightBraceCounter.setText(String.valueOf(openBrace));
        leftBraceCounter.setText(String.valueOf(closeBrace));
        previousInput = "blah";
    }


    public void equalButtonLogic(){
        if(answerView.getText().length() > 0){
            try{
                if(calculatorUtilities.isBracketCorrect(openBrace, closeBrace)){
                    String forEquationView = calculatorUtilities.repalaceForEquViewDisplay(expressionToEvaluate);

                    List<Operator> operatorList = new ArrayList<>();
                    operatorList.add(customOperators.getFactorialOperator());
                    operatorList.add(customOperators.getNrt());
                    operatorList.add(customOperators.getPercentageOperator());

                    List<Function> functionList = new ArrayList<>();
                    if(trigType.equals(DEG_RAD.DEG)){
                        functionList.add(customOperators.getSinDegrees());
                        functionList.add(customOperators.getCosDegrees());
                        functionList.add(customOperators.getTanDegrees());
                        expressionToEvaluate = calculatorUtilities.replaceForDegrees(expressionToEvaluate);
                    }else{functionList.clear();}

                    Expression calc = new ExpressionBuilder(expressionToEvaluate)
                            .operator(operatorList)
                            .functions(functionList)
                            .build();

                    NumberFormat numFormat = new DecimalFormat("##.##########");
                    solution = String.valueOf(numFormat.format(calc.evaluate()));
                    equationView.setText(forEquationView);
                    if(solution.contains(".")){dotCounter = 1;}

                    answerView.setText(calculatorUtilities.replaceForAnsViewDisplay(solution));
                    answerView.setSelection(answerView.getText().length());

                    //** There are some bugs on how the history is stored, will fix
                    historyData.insertData(expressionToEvaluate, solution);
                    ArrayList<String> tmp_equation_list = historyData.getData(DatabaseTable.FeedEntry.EQUATION);
                    ArrayList<String> tmp_solution_list = historyData.getData(DatabaseTable.FeedEntry.SOLUTION);
                    for(int i = 0; i < tmp_equation_list.size(); i++){
                        Log.d("EQUATION_HISTORY", tmp_equation_list.get(i));
                        Log.d("SOLUTION_HISTORY", tmp_solution_list.get(i));
                    }

                    calculatorUtilities.postEqualLogic(isAnswer, customOperators, openBrace,
                            closeBrace, rightBraceCounter, leftBraceCounter, previousInput);
                }else{
                    do{
                        closeBrace++;
                        expressionToEvaluate = expressionToEvaluate + ")";
                    }while(closeBrace < openBrace);
                    equalButtonLogic();
                }

            }catch(IllegalArgumentException | EmptyStackException e){
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }catch(ArithmeticException e){
                Toast.makeText(context, "Arithmetic Exception", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }


    public void determinePercentLogic(int percentIndex, int lengthOfExpressionEvalString){
        if(expressionToEvaluate.contains("%")){
            percentIndex = expressionToEvaluate.indexOf("%");
            lengthOfExpressionEvalString = expressionToEvaluate.length();
        }
        if(percentIndex == lengthOfExpressionEvalString-1){
            expressionToEvaluate = expressionToEvaluate + "-0";
        }else{
            customOperators.setIsComplexPercentage(true);
        }
    }

    /**
     * This method should only be used for testing purposes
     * */
    public void equalLogicForTesting(){
        List<Operator> operatorList = new ArrayList<>();
        operatorList.add(customOperators.getFactorialOperator());
        operatorList.add(customOperators.getNrt());
        //operatorList.add(customOperators.getPercentageOperator());

        List<Function> functionList = new ArrayList<>();

        Log.d("TRIG_TYPE", trigType+"");
        if(trigType.equals(DEG_RAD.DEG)){
            functionList.add(customOperators.getSinDegrees());
            functionList.add(customOperators.getCosDegrees());
            functionList.add(customOperators.getTanDegrees());

            if(expressionToEvaluate != null){
                Log.d("EXPRESSION_EVAL", expressionToEvaluate);
            }else{
                Log.d("EXPRESSION_EVAL", "NULL");
            }

            if(calculatorUtilities != null){
                Log.d("CALC_UTIL", "NOT NULL");
            }else{
                Log.d("CALC_UTIL", "NULL");
            }

            expressionToEvaluate = calculatorUtilities.replaceForDegrees(expressionToEvaluate);
        }else{functionList.clear();}

        int percentIndex = 0;
        int lengthOfExpressionEvalString = expressionToEvaluate.length();
        determinePercentLogic(percentIndex, lengthOfExpressionEvalString);

        Expression calc = new ExpressionBuilder(expressionToEvaluate)
                .operator(operatorList)
                .functions(functionList)
                .build();

        NumberFormat numFormat = new DecimalFormat("##.##########");
        solution = String.valueOf(numFormat.format(calc.evaluate()));
        if(solution.contains(".")){dotCounter = 1;}
        isAnswer = true;
        customOperators.setIsComplexPercentage(false);
        openBrace = 0;
        closeBrace = 0;
    }

    public void addToExpressionToBeEvaluated(String valueToAppend, ALL_BUTTONS type, boolean isExceptionToRule){
        String prevInput;
        int cursorLocation = 0;

        if(isAnswer){
            if(type.equals(ALL_BUTTONS.NUM)){answerView.setText(BLANK_STRING);}
            isAnswer = false;
        }
        checkBrace(type);
        prevInput = calculatorUtilities.getPreviousInput(expressionToEvaluate);
        if(calculatorUtilities.checkIfMoreOperandIsPossible(prevInput, isExceptionToRule)){return;}

        cursorLocation = answerView.getSelectionStart();
        expressionToEvaluate = calculatorUtilities.replaceForAnsViewDisplay(
                answerView.getText().insert(cursorLocation, valueToAppend).toString());

        answerView.setText(expressionToEvaluate);
        expressionToEvaluate = calculatorUtilities.replaceForCalculations(expressionToEvaluate);
        //answerView.setSelection(answerView.getText().length());

        if(type.equals(ALL_BUTTONS.LOG) || type.equals(ALL_BUTTONS.SIN) || type.equals(ALL_BUTTONS.COS)
                || type.equals(ALL_BUTTONS.TAN)){
            answerView.setSelection(cursorLocation+4);
        }else if(type.equals(ALL_BUTTONS.LN)){answerView.setSelection(cursorLocation+3);}
        else{answerView.setSelection(cursorLocation+1);}
    }

    public void checkBrace(ALL_BUTTONS type){
        if(type.equals(ALL_BUTTONS.BRACKET_OPEN) || type.equals(ALL_BUTTONS.SIN)
                || type.equals(ALL_BUTTONS.COS) || type.equals(ALL_BUTTONS.TAN)
                || type.equals(ALL_BUTTONS.LN) || type.equals(ALL_BUTTONS.LOG)
                || type.equals(ALL_BUTTONS.SQRT)){
            openBrace++;
            leftBraceCounter.setText(String.valueOf(openBrace));
        }
        if(type.equals(ALL_BUTTONS.BRACKET_CLOSE)){
            closeBrace++;
            rightBraceCounter.setText(String.valueOf(closeBrace));
        }
    }

    /**
     * Should only be used for testing
     * */
    public String setExpressionEvalString(String toEvaluate){
        expressionToEvaluate = toEvaluate;
        equalLogicForTesting();
        return solution;
    }

    public void changeTrigType(){
        if(trigType.equals(DEG_RAD.RAD)){trigType = DEG_RAD.DEG;}
        else if(trigType.equals(DEG_RAD.DEG)){trigType = DEG_RAD.RAD;}
    }
}
