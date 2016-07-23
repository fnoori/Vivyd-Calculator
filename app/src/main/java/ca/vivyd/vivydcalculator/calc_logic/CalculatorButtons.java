package ca.vivyd.vivydcalculator.calc_logic;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
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
import ca.vivyd.vivydcalculator.themes.Themer;
import ca.vivyd.vivydcalculator.utilities.CalculatorUtilities;

/**
 * Created by Farzam on 2016-05-04.
 *
 * Listeners, button logic, error handling ...
 */
public class CalculatorButtons implements View.OnClickListener, View.OnTouchListener, View.OnLongClickListener {
    private static final String PLUS_SYMBOL = "+";
    private static final String SHARED_PREF_NAME = "CalcData";
    private static final String NUMBER = "num";
    private static final String STRING_PLACE_HOLDER = "blah";

    private static final String[] NUMBERS_ARRAY = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "."};
    private static final String[] OPERANDS_ARRAY = {"+", "-", "/", "*", "%", "(", ")"};
    private static final String[] ADVANCED_OPERANDS_ARRAY = {"sin(", "cos(", "tan(", "log(", "ln(", "π",
                                                        "e", "sqrt(", "|=", "^", "^2", "!", "^3", "^-1", "ᴇ"};
    private static final String BLANK_STRING = "";
    private static final String NULL_STRING = null;
    public static final String DEGREE = "DEG";
    public static final String RADIAN = "RAD";
    public static String DEG_RAND_STATE;
    private static String ERROR_MSG = "ERROR";

    /*
    public enum ALL_BUTTONS {
        ADD, SUB, MUL, DIV, NUMBER, DOT, BRACKET_OPEN, BRACKET_CLOSE,
        SIN, COS, TAN, LOG, LN, PI, EULER, SQRT, POWER,
        FACT, SQR, NRT, PRCNT, CBRT, INVERSE, CSTM, EE, ANS, DEG_RAND, EQUAL, DEL, CLR
    }
    public enum DEG_RAD {DEG, RAD}
    */


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
    //public static DEG_RAD trigType;
    private static String trigType;
    private int dotCounter;
    public static int openBrace;
    public static int closeBrace;
    private boolean isAnswer;
    private HistoryData historyData;
    private CustomOperators customOperators;
    private static CalculatorUtilities calculatorUtilities;
    private UserDefinedButtons userDefButtons;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private SharedPreferencesLogic sharedPrefsLogic;
    //private ALL_BUTTONS prevInputEnum;
    private String previousInputType;

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
        previousInput = STRING_PLACE_HOLDER;
        if (DEG_RAND_STATE != null) {
            Log.i("TrigStateChange", "DEG_RAND_STATE in calcButton: " + DEG_RAND_STATE);
            if (DEG_RAND_STATE.equals(DEGREE)) {
                trigType = CalculatorUtilities.DEG_RAD[0];
            }
            else
                trigType = CalculatorUtilities.DEG_RAD[1];
        }
        else {
            Log.i("TrigStateChange", "DEG_RAND_STATE was null");
            DEG_RAND_STATE = RADIAN;
            trigType = CalculatorUtilities.DEG_RAD[0];
        }
        dotCounter = 0;
        openBrace = 0;
        closeBrace = 0;
        isAnswer = false;

        historyData = new HistoryData(context);
        historyData.clearDatabase();
        customOperators = new CustomOperators();
        calculatorUtilities = new CalculatorUtilities(context);
        sharedPrefsLogic = new SharedPreferencesLogic(context, prefs, editor);
        userDefButtons = new UserDefinedButtons(context, sharedPrefsLogic);

        for(Button curr : commonButtons){
            curr.setOnClickListener(this);
            curr.setOnTouchListener(this);
        }

        for(Button curr : commonOperands){
            curr.setOnClickListener(this);
            curr.setOnTouchListener(this);
        }
        degRandButton.setOnClickListener(this);
        equationView.setOnLongClickListener(this);

        MainActivity.afterInitAllButtonsTime = (int) System.currentTimeMillis();
        int totalTime = MainActivity.afterInitAllButtonsTime - MainActivity.startTime;
        Toast.makeText(context, "Total time to init all compenents: " + totalTime, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.degRandButton:
                if(trigType.equals(CalculatorUtilities.DEG_RAD[1])){
                    setRad(degRandButton);
                }else{
                    setDeg(degRandButton);
                }
                break;

            default:
                break;
        }
    }

    public static void setRad(Button degRandButton) {
        trigType = CalculatorUtilities.DEG_RAD[1];
        degRandButton.setText(RADIAN);
        DEG_RAND_STATE = RADIAN;
    }

    public static void setDeg(Button degRandButton) {
        trigType = CalculatorUtilities.DEG_RAD[0];
        degRandButton.setText(DEGREE);
        DEG_RAND_STATE = DEGREE;
    }

    public void countNumberOfBrackets(String equationView){
        openBrace = 0;
        closeBrace = 0;
        char[] equationArr = equationView.toCharArray();
        for(char curr : equationArr){
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
                    if(!userDefValue1[0].equals(PLUS_SYMBOL)){userDefButtons.launchUserInputDialog(this, var1Button);}
                break;
            case R.id.var2Button:
                    if(!userDefValue2[0].equals(PLUS_SYMBOL)){userDefButtons.launchUserInputDialog(this, var2Button);}
                break;
            case R.id.var3Button:
                    if(!userDefValue3[0].equals(PLUS_SYMBOL)){userDefButtons.launchUserInputDialog(this, var3Button);}
                break;
            case R.id.eqnView:
                Log.d("EQN_VIEW_LONG", "TRUE");
                if(equationView.getText() != BLANK_STRING){
                    countNumberOfBrackets(equationView.getText().toString());
                    answerView.setText(BLANK_STRING);
                    answerView.setText(equationView.getText().toString());
                    answerView.setSelection(answerView.getText().length());
                    openBrace = 0;
                    closeBrace = 0;

                    expressionToEvaluate = calculatorUtilities.replaceForCalculations(answerView.getText().toString());
                    isAnswer = false;
                }
                break;
            default:
                break;
        }
        return false;
    }

    public void setUserDefinedValues(Button whichButton, String varName, String varValue){
        switch (whichButton.getId()){
            case R.id.var1Button:
                userDefValue1[0] = varName;
                userDefValue1[1] = varValue;
                var1Button.setText(userDefValue1[0]);
                sharedPrefsLogic.variableDataInput("Var1Name", userDefValue1[0],
                         "Var1Value", userDefValue1[1]);
                break;
            case R.id.var2Button:
                userDefValue2[0] = varName;
                userDefValue2[1] = varValue;
                var2Button.setText(userDefValue2[0]);
                sharedPrefsLogic.variableDataInput("Var2Name", userDefValue2[0],
                        "Var2Value", userDefValue2[1]);
                break;
            case R.id.var3Button:
                userDefValue3[0] = varName;
                userDefValue3[1] = varValue;
                var3Button.setText(userDefValue3[0]);
                sharedPrefsLogic.variableDataInput("Var3Name", userDefValue3[0],
                        "Var3Value", userDefValue3[1]);
                break;
            default:
                break;
        }

    }

    int startTran = 50;
    int endTran = 600; //300
    int fast_endTran = 150;
    int transComplete = 0;
    String prevMotionEvent = "";
    int prevID = 0;
    double scalefactor = 2;
    private Rect rect;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        TransitionDrawable transition = (TransitionDrawable) v.getBackground();
        switch (v.getId()) {
            case R.id.zeroButton:
                animBtnLogic(v, transition, event, NUMBER, 0);
                break;
            case R.id.oneButton:
                animBtnLogic(v, transition, event, NUMBER, 1);
                break;
            case R.id.twoButton:
                animBtnLogic(v, transition, event, NUMBER, 2);
                break;
            case R.id.threeButton:
                animBtnLogic(v, transition, event, NUMBER, 3);
                break;
            case R.id.fourButton:
                animBtnLogic(v, transition, event, NUMBER, 4);
                break;
            case R.id.fiveButton:
                animBtnLogic(v, transition, event, NUMBER, 5);
                break;
            case R.id.sixButton:
                animBtnLogic(v, transition, event, NUMBER, 6);
                break;
            case R.id.sevenButton:
                animBtnLogic(v, transition, event, NUMBER, 7);
                System.out.println("sevena action = " + prevMotionEvent);
                break;
            case R.id.eightButton:
                animBtnLogic(v, transition, event, NUMBER, 8);
                break;
            case R.id.nineButton:
                animBtnLogic(v, transition, event, NUMBER, 9);
                break;
            case R.id.dotButton:
                animBtnLogic(v, transition, event, "dot", 10);
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
                setBraceColor();
                break;
            case R.id.closeBrace:
                animBtnLogic(v, transition, event, "close_brace", 0);
                setBraceColor();
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
            case R.id.ansButton:
                animBtnLogic(v, transition, event, "ans", 0);
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
        prevID = v.getId();
        return false;
    }

    public void setBraceColor() {
        if (!leftBraceCounter.getText().equals(rightBraceCounter.getText())) {
            leftBraceCounter.setTextColor(Themer.colorArray.get(Themer.COLOR_COMP));
            rightBraceCounter.setTextColor(Themer.colorArray.get(Themer.COLOR_COMP));
        }
        else {
            leftBraceCounter.setTextColor(Themer.colorArray.get(Themer.COLOR_NUMPAD_DARK));
            rightBraceCounter.setTextColor(Themer.colorArray.get(Themer.COLOR_NUMPAD_DARK));
        }
    }

    public void animBtnLogic(View v, TransitionDrawable transition, MotionEvent event, String type, int num) {
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        switch (event.getActionMasked()) {

            case MotionEvent.ACTION_DOWN:
                transComplete = 0;
                rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                transition.startTransition(startTran);
                System.out.println("prevM" + prevMotionEvent);
                prevMotionEvent = "ACTION_DOWN";
                //Log.i("ello", prevMotionEvent + " : " + num + " transcomplete = " + transComplete);
                break;

            case MotionEvent.ACTION_MOVE:
                if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                    if (transComplete != 1) {
                        transition.reverseTransition(fast_endTran);
                        transComplete = 1;
                    }
                }
                prevMotionEvent = "ACTION_MOVE";
                //Log.i("ello", prevMotionEvent + " : " + num + " transcomplete = " + transComplete);
                break;

            case MotionEvent.ACTION_UP:
                if (transComplete == 1){
                    return;
                }
                btnFunction(type, num);
                transition.reverseTransition(endTran);
                prevMotionEvent = "ACTION_UP";
                //Log.i("ello", prevMotionEvent + " : " + num + " transcomplete = " + transComplete);
                break;

        }
    }

    private void btnFunction(String type, int num) {
        switch (type) {
            case "dot":
                if(dotCounter == 1){break;}
                else{
                    addToExpressionToBeEvaluated(NUMBERS_ARRAY[10], CalculatorUtilities.ALL_BUTTS[5], true);
                    dotCounter = 1;
                }
                break;
            case NUMBER:
                addToExpressionToBeEvaluated(NUMBERS_ARRAY[num], CalculatorUtilities.ALL_BUTTS[4], true);
                break;
            case "add":
                addToExpressionToBeEvaluated(OPERANDS_ARRAY[0], CalculatorUtilities.ALL_BUTTS[0], false);
                dotCounter = 0;
                break;
            case "sub":
                addToExpressionToBeEvaluated(OPERANDS_ARRAY[1], CalculatorUtilities.ALL_BUTTS[1], false);
                dotCounter = 0;
                break;
            case "mul":
                addToExpressionToBeEvaluated(OPERANDS_ARRAY[3], CalculatorUtilities.ALL_BUTTS[2], false);
                dotCounter = 0;
                break;
            case "div":
                addToExpressionToBeEvaluated(OPERANDS_ARRAY[2], CalculatorUtilities.ALL_BUTTS[3], false);
                dotCounter = 0;
                break;
            case "prcnt":
                addToExpressionToBeEvaluated(OPERANDS_ARRAY[4], CalculatorUtilities.ALL_BUTTS[20], false);
                dotCounter = 0;
                break;
            case "open_brace":
                addToExpressionToBeEvaluated(OPERANDS_ARRAY[5], CalculatorUtilities.ALL_BUTTS[6], true);
                dotCounter = 0;
                break;
            case "close_brace":
                addToExpressionToBeEvaluated(OPERANDS_ARRAY[6], CalculatorUtilities.ALL_BUTTS[7], true);
                break;
            case "del":
                deleteButtonLogic();
                setBraceColor();
                break;
            case "clr":
                clearButtonLogic();
                setBraceColor();
                break;
            case "eql":
                equalButtonLogic();
                setBraceColor();
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
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[0], CalculatorUtilities.ALL_BUTTS[8], true);
                setBraceColor();
                dotCounter = 0;
                break;
            case "cos":
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[1], CalculatorUtilities.ALL_BUTTS[9], true);
                setBraceColor();
                dotCounter = 0;
                break;
            case "tan":
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[2], CalculatorUtilities.ALL_BUTTS[10], true);
                setBraceColor();
                dotCounter = 0;
                break;
            case "log":
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[3], CalculatorUtilities.ALL_BUTTS[11], true);
                setBraceColor();
                dotCounter = 0;
                break;
            case "ln":
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[4], CalculatorUtilities.ALL_BUTTS[12], true);
                setBraceColor();
                dotCounter = 0;
                break;
            case "pi":
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[5], CalculatorUtilities.ALL_BUTTS[13], true);
                dotCounter = 0;
                break;
            case "euler":
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[6], CalculatorUtilities.ALL_BUTTS[14], true);
                dotCounter = 0;
                break;
            case "sqrt":
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[7], CalculatorUtilities.ALL_BUTTS[15], true);
                setBraceColor();
                dotCounter = 0;
                break;
            case "nrt":
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[8], CalculatorUtilities.ALL_BUTTS[18], true);
                dotCounter = 0;
                break;
            case "power":
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[9], CalculatorUtilities.ALL_BUTTS[16], true);
                dotCounter = 0;
                break;
            case "sqr":
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[10], CalculatorUtilities.ALL_BUTTS[18], true);
                dotCounter = 0;
                break;
            case "fact":
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[11], CalculatorUtilities.ALL_BUTTS[17], true);
                dotCounter = 0;
                break;
            case "cbrt":
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[12], CalculatorUtilities.ALL_BUTTS[21], true);
                dotCounter = 0;
                break;
            case "inverse":
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[13], CalculatorUtilities.ALL_BUTTS[22], true);
                dotCounter = 0;
                break;
            case "ᴇ":
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[14], CalculatorUtilities.ALL_BUTTS[24], true);
                break;
            case "ans":
                addToExpressionToBeEvaluated(sharedPrefsLogic.getData("ANS"), CalculatorUtilities.ALL_BUTTS[25], true);
                break;
            case "user1":
                if(!userDefValue1[0].equals(PLUS_SYMBOL)){
                    addToExpressionToBeEvaluated(userDefValue1[1], CalculatorUtilities.ALL_BUTTS[23], true);
                }else{
                    userDefButtons.launchUserInputDialog(this, var1Button);
                }
                dotCounter = 0;
                break;
            case "user2":
                if(!userDefValue2[0].equals(PLUS_SYMBOL)){
                    addToExpressionToBeEvaluated(userDefValue2[1], CalculatorUtilities.ALL_BUTTS[23], true);
                }else{
                    userDefButtons.launchUserInputDialog(this, var2Button);
                }
                dotCounter = 0;
                break;
            case "user3":
                if(!userDefValue3[0].equals(PLUS_SYMBOL)){
                    addToExpressionToBeEvaluated(userDefValue3[1], CalculatorUtilities.ALL_BUTTS[23], true);
                }else{
                    userDefButtons.launchUserInputDialog(this, var3Button);
                }
                dotCounter = 0;
                break;
        }
    }

    public void addAdvanceOperands(ArrayList<Button> advancedOperands){
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        for(Button curr : advancedOperands){
            if(curr.getId() == R.id.var1Button){
                var1Button = curr; curr.setOnLongClickListener(this);
                userDefValue1[0] = prefs.getString("Var1Name", PLUS_SYMBOL);
                userDefValue1[1] = prefs.getString("Var1Value", PLUS_SYMBOL);
                curr.setText(userDefValue1[0]);

            }
            else if(curr.getId() == R.id.var2Button){
                var2Button = curr; curr.setOnLongClickListener(this);
                userDefValue2[0] = prefs.getString("Var2Name", PLUS_SYMBOL);
                userDefValue2[1] = prefs.getString("Var2Value", PLUS_SYMBOL);
                curr.setText(userDefValue2[0]);
            }
            else if(curr.getId() == R.id.var3Button){
                var3Button = curr; curr.setOnLongClickListener(this);
                userDefValue3[0] = prefs.getString("Var3Name", PLUS_SYMBOL);
                userDefValue3[1] = prefs.getString("Var3Value", PLUS_SYMBOL);
                curr.setText(userDefValue3[0]);
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
                if(openBrace < 0){openBrace = 0;}
                leftBraceCounter.setText(String.valueOf(openBrace));
            }else if(answerView.getText().toString().charAt(indexFrom) == ')'){
                closeBrace--;
                if(closeBrace < 0){closeBrace = 0;}
                rightBraceCounter.setText(String.valueOf(closeBrace));
            }

            expressionToEvaluate = calculatorUtilities.replaceForAnsViewDisplay(answerView.getText().toString().substring(0, indexFrom)
                    + answerView.getText().toString().substring(indexTo));

            if(calculatorUtilities.isPreviousValueNumeric(expressionToEvaluate)){previousInputType = CalculatorUtilities.ALL_BUTTS[4];}
            else{previousInputType = CalculatorUtilities.ALL_BUTTS[20];}
            isAnswer = false;

            answerView.setText(expressionToEvaluate);
            answerView.setSelection(indexFrom);
            expressionToEvaluate = calculatorUtilities.replaceForCalculations(expressionToEvaluate);

            if(expressionToEvaluate.length() < 1){
                expressionToEvaluate = BLANK_STRING;
                answerView.setText(BLANK_STRING);
            }
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
        previousInput = STRING_PLACE_HOLDER;
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
                    operatorList.add(customOperators.getTenToPowerOfOperator());

                    List<Function> functionList = new ArrayList<>();
                    if(trigType.equals(CalculatorUtilities.DEG_RAD[0])){
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
                    solution = calculatorUtilities.convertToScientific(solution);

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
                    isAnswer = true;
                    sharedPrefsLogic.generalPurposeDataInput("ANS", solution);
                }else{
                    do{
                        closeBrace++;
                        expressionToEvaluate = expressionToEvaluate + ")";
                    }while(closeBrace < openBrace);
                    equalButtonLogic();
                }

            }catch(IllegalArgumentException | EmptyStackException | ArithmeticException e){
                //Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
                errorAnim();
                e.printStackTrace();
                setBraceColor();
            }
        }
    }

    public void errorAnim() {
        int startColor = Themer.colorArray.get(Themer.COLOR_ACCENT);
        int endColor = Themer.colorArray.get(Themer.COLOR_BACKGROUND);
        ValueAnimator colorAnim = ValueAnimator.ofObject(new ArgbEvaluator(), startColor, endColor);
        colorAnim.setDuration(1000);
        colorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                answerView.setBackgroundColor((int) animation.getAnimatedValue());
                equationView.setBackgroundColor((int) animation.getAnimatedValue());
            }
        });
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.setRepeatCount(1);
        colorAnim.start();

        answerView.setText(ERROR_MSG);

    }


    public void addToExpressionToBeEvaluated(String valueToAppend, String type, boolean isExceptionToRule){
        String prevInput;
        int cursorLocation = 0;

        if(isAnswer){
            if(type.equals(CalculatorUtilities.ALL_BUTTS[4])){answerView.setText(BLANK_STRING);}
            isAnswer = false;
        }
        checkBrackets(type);
        prevInput = calculatorUtilities.getPreviousInput(expressionToEvaluate);
        if(calculatorUtilities.checkIfMoreOperandIsPossible(prevInput, type, previousInputType, isExceptionToRule)){return;}

        cursorLocation = answerView.getSelectionStart();
        expressionToEvaluate = calculatorUtilities.replaceForAnsViewDisplay(
                answerView.getText().insert(cursorLocation, valueToAppend).toString());

        answerView.setText(expressionToEvaluate);
        expressionToEvaluate = calculatorUtilities.replaceForCalculations(expressionToEvaluate);
        answerView.setSelection(calculatorUtilities.determineCursorLocation(type, cursorLocation, valueToAppend));
        previousInputType = type;
    }

    public void checkBrackets(String type){
        if(type.equals(CalculatorUtilities.ALL_BUTTS[6]) || type.equals(CalculatorUtilities.ALL_BUTTS[8])
                || type.equals(CalculatorUtilities.ALL_BUTTS[9]) || type.equals(CalculatorUtilities.ALL_BUTTS[10])
                || type.equals(CalculatorUtilities.ALL_BUTTS[12]) || type.equals(CalculatorUtilities.ALL_BUTTS[11])
                || type.equals(CalculatorUtilities.ALL_BUTTS[15])){
            openBrace++;
            leftBraceCounter.setText(String.valueOf(openBrace));
        }
        if(type.equals(CalculatorUtilities.ALL_BUTTS[7])){
            closeBrace++;
            rightBraceCounter.setText(String.valueOf(closeBrace));
        }
    }

    public void changeTrigType(){
        if(trigType.equals(CalculatorUtilities.DEG_RAD[1])){
            trigType = CalculatorUtilities.DEG_RAD[0];
            DEG_RAND_STATE = DEGREE;
        }
        else if(trigType.equals(CalculatorUtilities.DEG_RAD[0])){
            trigType = CalculatorUtilities.DEG_RAD[1];
            DEG_RAND_STATE = RADIAN;
        }
    }
}
