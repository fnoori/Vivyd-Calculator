package ca.vivyd.vivydcalculator.calc_logic;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.operator.Operator;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

import ca.vivyd.vivydcalculator.Exceptions.BadBracketsException;
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
    private static final String VAR1_NAME= "Var1Name";
    private static final String VAR1_VALUE= "Var1Value";
    private static final String VAR2_NAME= "Var2Name";
    private static final String VAR2_VALUE= "Var2Value";
    private static final String VAR3_NAME= "Var3Name";
    private static final String VAR3_VALUE= "Var3Value";

    private static final String[] NUMBERS_ARRAY = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "."};
    private static final String[] OPERANDS_ARRAY = {"+", "-", "/", "*", "%", "(", ")"};
    private static final String[] ADVANCED_OPERANDS_ARRAY = {"sin(", "cos(", "tan(", "log(", "ln(", "π",
                                                        "e", "sqrt(", "|=", "^", "^2", "!", "^3", "^-1", "ᴇ"};
    private static final String BLANK_STRING = "";
    private static final String NULL_STRING = null;
    public static final String DEGREE = "DEG";
    public static final String RADIAN = "RAD";
    private static final String EMPTY_STACK_MSG =  "ERROR";
    private static final String ILLEGAL_ARGUMENT_MSG = "Incorrect Parameter";
    private static final String ARITH_MSG = "Can't divide by 0";
    private static final String BAD_BRAC_MSG = "Incorrect Brackets";

    public static String DEG_RAND_STATE;
    private static String ERROR_MSG = "ERROR";
    private int overage;

    /*
    public enum ALL_BUTTONS {
        ADD, SUB, MUL, DIV, NUMBER, DOT, BRACKET_OPEN, BRACKET_CLOSE,
        SIN, COS, TAN, LOG, LN, PI, EULER, SQRT, POWER,
        FACT, SQR, NRT, PRCNT, CBRT, INVERSE, CSTM, EE, ANS, DEG_RAND, EQUAL, DEL, CLR
    }
    public enum DEG_RAD {DEG, RAD}
    */


    private Context context;
    private Activity curr_activity;
    private LinearLayout display;
    private EditText answerView;
    private TextView equationView;
    private TextView rightBracketCounter;
    private TextView leftBracketCounter;
    private Button var1Button;
    private Button var2Button;
    private Button var3Button;
    private Button degRandButton;
    private String expressionEvalString;
    private String expressionDisplayString;
    private List<Operator> operatorList;
    private List<Function> functionList;


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
    public static int openBracket;
    public static int closeBracket;
    private boolean isAnswer;
    private boolean isError;
    private HistoryData historyData;
    private CustomOperators customOperators;
    private static CalculatorUtilities calculatorUtilities;
    private UserDefinedButtons userDefButtons;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private SharedPreferencesLogic sharedPrefsLogic;
    //private ALL_BUTTONS prevInputEnum;
    private String previousInputType;

    public CalculatorButtons(Context context, LinearLayout display, EditText answerView,
                             TextView equationView, ArrayList<Button> commonButtons,
                             ArrayList<Button> commonOperands, TextView leftBraceCounter,
                             TextView rightBraceCounter, Button degRandButton){
        this.context = context;
        this.curr_activity = (Activity) context;
        this.display = display;
        this.answerView = answerView;
        this.equationView = equationView;
        this.leftBracketCounter = leftBraceCounter;
        this.rightBracketCounter = rightBraceCounter;
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
        openBracket = 0;
        closeBracket = 0;
        isAnswer = false;
        isError = false;

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

        operatorList = new ArrayList<>();
        operatorList.add(customOperators.getFactorialOperator());
        operatorList.add(customOperators.getNrt());
        operatorList.add(customOperators.getPercentageOperator());
        operatorList.add(customOperators.getTenToPowerOfOperator());

        functionList = new ArrayList<>();
        functionList.add(customOperators.getSinDegrees());
        functionList.add(customOperators.getCosDegrees());
        functionList.add(customOperators.getTanDegrees());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.degRandButton:
                if(trigType.equals(CalculatorUtilities.DEG_RAD[1])){
                    setDeg(degRandButton);
                }else{
                    setRad(degRandButton);
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
        openBracket = 0;
        closeBracket = 0;
        char[] equationArr = equationView.toCharArray();
        for(char curr : equationArr){
            if(curr == '('){
                openBracket++;}
            else if(curr == ')'){
                closeBracket++;}
        }

        leftBracketCounter.setText(String.valueOf(openBracket));
        rightBracketCounter.setText(String.valueOf(closeBracket));
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
                if(equationView.getText() != BLANK_STRING){
                    countNumberOfBrackets(equationView.getText().toString());
                    answerView.setText(BLANK_STRING);
                    answerView.setText(equationView.getText().toString());
                    answerView.setSelection(answerView.getText().length());
                    //openBracket = 0;
                    //closeBracket = 0;
                    resizeAnsView("eqnView");

                    expressionToEvaluate = calculatorUtilities.replaceForCalculations(answerView.getText().toString());
                    isAnswer = false;
                    setBraceColor();
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
                sharedPrefsLogic.variableDataInput(VAR1_NAME, userDefValue1[0],
                         VAR1_VALUE, userDefValue1[1]);
                break;
            case R.id.var2Button:
                userDefValue2[0] = varName;
                userDefValue2[1] = varValue;
                var2Button.setText(userDefValue2[0]);
                sharedPrefsLogic.variableDataInput(VAR2_NAME, userDefValue2[0],
                        VAR2_VALUE, userDefValue2[1]);
                break;
            case R.id.var3Button:
                userDefValue3[0] = varName;
                userDefValue3[1] = varValue;
                var3Button.setText(userDefValue3[0]);
                sharedPrefsLogic.variableDataInput(VAR3_NAME, userDefValue3[0],
                        VAR3_VALUE, userDefValue3[1]);
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
                if (MainActivity.screenOrientation == Configuration.ORIENTATION_PORTRAIT)
                    resizeAnsView(type);
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
                addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[8], CalculatorUtilities.ALL_BUTTS[19], true);
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
                userDefValue1[0] = prefs.getString(VAR1_NAME, PLUS_SYMBOL);
                userDefValue1[1] = prefs.getString(VAR1_VALUE, PLUS_SYMBOL);
                curr.setText(userDefValue1[0]);

            }
            else if(curr.getId() == R.id.var2Button){
                var2Button = curr; curr.setOnLongClickListener(this);
                userDefValue2[0] = prefs.getString(VAR2_NAME, PLUS_SYMBOL);
                userDefValue2[1] = prefs.getString(VAR3_VALUE, PLUS_SYMBOL);
                curr.setText(userDefValue2[0]);
            }
            else if(curr.getId() == R.id.var3Button){
                var3Button = curr; curr.setOnLongClickListener(this);
                userDefValue3[0] = prefs.getString(VAR3_NAME, PLUS_SYMBOL);
                userDefValue3[1] = prefs.getString(VAR3_VALUE, PLUS_SYMBOL);
                curr.setText(userDefValue3[0]);
            }
            curr.setOnTouchListener(this);
        }
    }

    public void deleteButtonLogic(){
        if(answerView.getText().length() > 0){

            if (answerView.getText().length() == 1 && checkEqnView()){
                equationView.setText("");
            }

            int indexFrom = answerView.getSelectionStart()-1;
            int indexTo = answerView.getSelectionStart();

            if(indexFrom >= 0){
                if(answerView.getText().toString().contains(".")){dotCounter = 0;}
                if(answerView.getText().toString().charAt(indexFrom) == '('){
                    openBracket--;
                    if(openBracket < 0){
                        openBracket = 0;}
                    leftBracketCounter.setText(String.valueOf(openBracket));
                }else if(answerView.getText().toString().charAt(indexFrom) == ')'){
                    closeBracket--;
                    if(closeBracket < 0){
                        closeBracket = 0;}
                    rightBracketCounter.setText(String.valueOf(closeBracket));
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
    }

    public void clearButtonLogic(){
        if(answerView.getText().length() < 1 || checkEqnView()){equationView.setText(BLANK_STRING);}
        answerView.setText(BLANK_STRING);
        expressionEvalString = BLANK_STRING;
        expressionDisplayString = BLANK_STRING;
        expressionToEvaluate = BLANK_STRING;
        dotCounter = 0;
        openBracket = 0;
        closeBracket = 0;
        rightBracketCounter.setText(String.valueOf(openBracket));
        leftBracketCounter.setText(String.valueOf(closeBracket));
        previousInput = STRING_PLACE_HOLDER;
        isError = false;
    }


    public void equalButtonLogic(){
        String previousExpressionToEvaluate = expressionToEvaluate;
        Log.d("PREVIOUS_IS", previousExpressionToEvaluate);
        Log.d("NUM_OPEN_BRACKET", openBracket+"");
        Log.d("NUM_CLOSE_BRACKET", closeBracket+"");

        if(answerView.getText().length() > 0) {
            try {
                if (calculatorUtilities.isBracketCorrect(openBracket, closeBracket)) {
                    String forEquationView = calculatorUtilities.repalaceForEquViewDisplay(expressionToEvaluate);
                    if(trigType.equals(CalculatorUtilities.DEG_RAD[0])){
                        expressionToEvaluate = calculatorUtilities.replaceForDegrees(expressionToEvaluate);
                    }
                    Expression calc = new ExpressionBuilder(expressionToEvaluate).operator(operatorList)
                            .functions(functionList)
                            .build();

                    NumberFormat numFormat = new DecimalFormat("##.##########");
                    solution = String.valueOf(numFormat.format(calc.evaluate()));
                    Animation sweep = AnimationUtils.loadAnimation(context, R.anim.sweepity_sweep);
                    equationView.startAnimation(sweep);
                    equationView.setText(forEquationView);
                    if (solution.contains(".")) {dotCounter = 1;}
                    solution = calculatorUtilities.convertToScientific(solution, display);

                    Animation sweep_fast = AnimationUtils.loadAnimation(context, R.anim.sweepity_sweep_fast);
                    answerView.startAnimation(sweep_fast);
                    answerView.setText(calculatorUtilities.replaceForAnsViewDisplay(solution));
                    answerView.setSelection(answerView.getText().length());

                    //Hopefully this does not cause bugs
                    openBracket = 0;
                    closeBracket = 0;

                    //** There are some bugs on how the history is stored, will fix
                    historyData.insertData(expressionToEvaluate, solution);
                    ArrayList<String> tmp_equation_list = historyData.getData(DatabaseTable.FeedEntry.EQUATION);
                    ArrayList<String> tmp_solution_list = historyData.getData(DatabaseTable.FeedEntry.SOLUTION);
                    for (int i = 0; i < tmp_equation_list.size(); i++) {
                        Log.d("EQUATION_HISTORY", tmp_equation_list.get(i));
                        Log.d("SOLUTION_HISTORY", tmp_solution_list.get(i));
                    }

                    calculatorUtilities.postEqualLogic(isAnswer, customOperators, openBracket,
                            closeBracket, rightBracketCounter, leftBracketCounter, previousInput);
                    isAnswer = true;
                    sharedPrefsLogic.generalPurposeDataInput("ANS", solution);
                } else {
                    if (closeBracket > openBracket) {
                        do {
                            openBracket++;
                            expressionToEvaluate = "(" + expressionToEvaluate;
                        } while (openBracket < closeBracket);
                    } else {
                        do {
                            closeBracket++;
                            expressionToEvaluate = expressionToEvaluate + ")";
                        } while (closeBracket < openBracket);
                    }
                    answerView.setText(expressionToEvaluate);
                    answerView.setSelection(answerView.getText().toString().length());
                    equalButtonLogic();
                }
            } catch (IllegalArgumentException e) {
                errorAnim(ILLEGAL_ARGUMENT_MSG);
                e.printStackTrace();
            } catch (EmptyStackException e) {
                errorAnim(EMPTY_STACK_MSG);
                e.printStackTrace();
            } catch (ArithmeticException e) {
                errorAnim(ARITH_MSG);
                e.printStackTrace();
            }
        }
    }

    public void errorAnim(String errorMsg) {
        int durationERR = 550;
        final int durationRel = 600;
        final int start_colorAccent = Themer.colorArray.get(Themer.COLOR_ACCENT);
        final int end_colorComp = Themer.colorArray.get(Themer.COLOR_COMP);
        final int colorTextScreen = Themer.colorArray.get(Themer.COLOR_TEXT_SCREEN);
        final String currEqn = equationView.getText().toString();


        // Change Background color
        ValueAnimator colorAnim = ValueAnimator.ofObject(new ArgbEvaluator(), start_colorAccent, end_colorComp);
        colorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                display.setBackgroundColor((int) animation.getAnimatedValue());
            }
        });
        colorAnim.setDuration(durationERR);
        colorAnim.setInterpolator(new DecelerateInterpolator());
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.setRepeatCount(1);
        colorAnim.start();

        // Shake the equation in ansView that caused the Error
        Animation shake = AnimationUtils.loadAnimation(context, R.anim.shakey_shake);
        answerView.startAnimation(shake);

        // change eqnView to error alert, reverse only if eqnView was not empty and did not have error msg
        ValueAnimator textAnim = ValueAnimator.ofObject(new ArgbEvaluator(), start_colorAccent, colorTextScreen);
        textAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                equationView.setTextColor((int) animation.getAnimatedValue());
            }
        });
        textAnim.setDuration(durationERR);
        textAnim.setInterpolator(new DecelerateInterpolator());
        if (!checkEqnView(currEqn)) {
            textAnim.setRepeatMode(ValueAnimator.REVERSE);
            textAnim.setRepeatCount(1);
        }
        textAnim.start();
        equationView.setText(errorMsg);

        final Handler handler = new Handler();

        // If the previous stored element in eqnView was an error or was empty, the new error msg persists
        // Otherwise, it returns to the stored equation
        if (!checkEqnView(currEqn)) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ValueAnimator textRelease = ValueAnimator.ofObject(new ArgbEvaluator(), start_colorAccent, colorTextScreen);
                    textRelease.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            equationView.setTextColor((int) animation.getAnimatedValue());
                        }
                    });
                    textRelease.setDuration(durationRel);
                    textRelease.setInterpolator(new LinearInterpolator());
                    equationView.setText(currEqn);
                    textRelease.start();
                }
            }, durationERR * 2);
        }

        //isError = true;
        //resetBrace();
    }

    public void addToExpressionToBeEvaluated(String valueToAppend, String type, boolean isExceptionToRule){
        String prevInput;
        int cursorLocation = 0;
        if(isAnswer || isError){
            if(type.equals(CalculatorUtilities.ALL_BUTTS[4]) ||
                    type.equals(CalculatorUtilities.ALL_BUTTS[8]) ||
                    type.equals(CalculatorUtilities.ALL_BUTTS[9]) ||
                    type.equals(CalculatorUtilities.ALL_BUTTS[10]) ||
                    type.equals(CalculatorUtilities.ALL_BUTTS[11]) ||
                    type.equals(CalculatorUtilities.ALL_BUTTS[12]) ||
                    type.equals(CalculatorUtilities.ALL_BUTTS[13]) ||
                    type.equals(CalculatorUtilities.ALL_BUTTS[15])){
                answerView.setText(BLANK_STRING);
            }
            openBracket = 0;
            closeBracket = 0;
            isAnswer = false;
            isError = false;
        }
        checkBrackets(type);
        prevInput = calculatorUtilities.getPreviousInput(expressionToEvaluate);

        cursorLocation = answerView.getSelectionStart();
        if(calculatorUtilities.checkIfMoreOperandIsPossible(prevInput, type, previousInputType, isExceptionToRule)){
            return;
        }
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
            openBracket++;
            leftBracketCounter.setText(String.valueOf(openBracket));
        }
        if(type.equals(CalculatorUtilities.ALL_BUTTS[7])){
            closeBracket++;
            rightBracketCounter.setText(String.valueOf(closeBracket));
        }
    }

    public void  setBraceColor() {
        if (!leftBracketCounter.getText().equals(rightBracketCounter.getText())) {
            leftBracketCounter.setTextColor(Themer.colorArray.get(Themer.COLOR_COMP));
            rightBracketCounter.setTextColor(Themer.colorArray.get(Themer.COLOR_COMP));
        }
        else {
            leftBracketCounter.setTextColor(Themer.colorArray.get(Themer.COLOR_NUMPAD_DARK));
            rightBracketCounter.setTextColor(Themer.colorArray.get(Themer.COLOR_NUMPAD_DARK));
        }
    }

    // RETURNS: Returns true if string in EqnView is not a number/eqn
    public boolean checkEqnView(){
        String currEqn = equationView.getText().toString();
        return  currEqn.equals(ILLEGAL_ARGUMENT_MSG) || currEqn.equals(EMPTY_STACK_MSG)
                    || currEqn.equals(ARITH_MSG) || currEqn.equals("");
    }
    // REQUIRES: Pass in the String from EqnView
    public boolean checkEqnView(String currEqn){
        return  currEqn.equals(ILLEGAL_ARGUMENT_MSG) || currEqn.equals(EMPTY_STACK_MSG)
                || currEqn.equals(ARITH_MSG) || currEqn.equals("");
    }


    public void resizeAnsView(String type) {

        Paint paint = answerView.getPaint();
        float ansWidth_string = paint.measureText(answerView.getText().toString());
        MainActivity.ansSize = MainActivity.pixelsToSp(context, answerView.getTextSize());

        if (type.equals("eql") || type.equals("eqnView")){
            if (type.equals("eql") && checkEqnView(equationView.getText().toString()))
                return;
            MainActivity.ansSize = MainActivity.defaultTxtSize;
            answerView.setTextSize(TypedValue.COMPLEX_UNIT_SP, MainActivity.ansSize);
            Log.d("RESIZE", " ansWidth_string at eql_resize: " + ansWidth_string);
            while (ansWidth_string > display.getWidth() - MainActivity.pixelCushion
                    && MainActivity.ansSize > MainActivity.minText_size) {
                MainActivity.ansSize -= 2;
                answerView.setTextSize(TypedValue.COMPLEX_UNIT_SP, MainActivity.ansSize);
                ansWidth_string = paint.measureText(answerView.getText().toString());
                overage = 0;
                Log.d("RESIZE", " eql button resize is occuring");
                Log.d("RESIZE", " new ansWidth_string: " + ansWidth_string);
            }
        }else if (ansWidth_string >= (display.getWidth() - MainActivity.pixelCushion)
                && MainActivity.ansSize >= MainActivity.minText_size
                && !type.equals("del")){
            /**
             *  Animate the size reduction DISABLED BECAUSE it causes jittering/jank on low end devices (Moto G)
             */
            /*
            ValueAnimator textScale_in = ValueAnimator.ofFloat(ansSize, ansSize-2);
            textScale_in.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    answerView.setTextSize(TypedValue.COMPLEX_UNIT_SP, (float) animation.getAnimatedValue());
                }
            });
            textScale_in.setDuration(200);
            //textScale_in.setInterpolator(new LinearInterpolator());
            textScale_in.start();
            */
            MainActivity.ansSize -= 2;
            answerView.setTextSize(TypedValue.COMPLEX_UNIT_SP, MainActivity.ansSize);
        }else if (ansWidth_string >= (display.getWidth() - MainActivity.pixelCushion)
                && MainActivity.ansSize < MainActivity.minText_size
                && !type.equals("del")) {
            overage++;
        }else if (type.equals("del") && MainActivity.ansSize < MainActivity.defaultTxtSize
                    && ansWidth_string < display.getWidth() && overage == 0) {
                MainActivity.ansSize += 2;
                answerView.setTextSize(TypedValue.COMPLEX_UNIT_SP, MainActivity.ansSize);
        }else if (ansWidth_string >= (display.getWidth() - MainActivity.pixelCushion)
                && MainActivity.ansSize < MainActivity.minText_size
                && type.equals("del")) {
            overage--;
        }else if (type.equals("clr") || (isAnswer && ansWidth_string < display.getWidth() - MainActivity.pixelCushion)){
            answerView.setTextSize(TypedValue.COMPLEX_UNIT_SP, MainActivity.defaultTxtSize);
            overage = 0;
        }
    }
}
