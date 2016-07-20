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
    private static final String NUM = "num";
    private static final String STRING_PLACE_HOLDER = "blah";

    private static final String[] NUMBERS_ARRAY = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "."};
    private static final String[] OPERANDS_ARRAY = {"+", "-", "/", "*", "%", "(", ")"};
    private static final String[] ADVANCED_OPERANDS_ARRAY = {"sin(", "cos(", "tan(", "log(", "ln(", "π",
                                                        "e", "sqrt(", "|=", "^", "^2", "!", "^3", "^-1", "ᴇ"};
    private static final String BLANK_STRING = "";
    private static final String NULL_STRING = null;
    private static final String DEGREE = "DEG";
    private static final String RADIAN = "RAD";

    public enum ALL_BUTTONS {
        ADD, SUB, MUL, DIV, NUM, DOT, BRACKET_OPEN, BRACKET_CLOSE,
        SIN, COS, TAN, LOG, LN, PI, EULER, SQRT, POWER,
        FACT, SQR, NRT, PRCNT, CBRT, INVERSE, CSTM, EE, ANS, DEG_RAND, EQUAL, DEL, CLR
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
    public static int openBrace;
    public static int closeBrace;
    private boolean isAnswer;
    private boolean isOnTouchActive;
    private HistoryData historyData;
    private CustomOperators customOperators;
    private CalculatorUtilities calculatorUtilities;
    private UserDefinedButtons userDefButtons;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private SharedPreferencesLogic sharedPrefsLogic;

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
        trigType = DEG_RAD.RAD;
        cursorLocation = 0;
        dotCounter = 0;
        openBrace = 0;
        closeBrace = 0;
        isAnswer = false;
        isOnTouchActive = false;

        historyData = new HistoryData(context);
        historyData.clearDatabase();
        customOperators = new CustomOperators();
        calculatorUtilities = new CalculatorUtilities(context);
        userDefButtons = new UserDefinedButtons(context);

        sharedPrefsLogic = new SharedPreferencesLogic(context, prefs, editor);

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
        calculatorUtilities = new CalculatorUtilities(context);
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
                    openBrace = 0;
                    closeBrace = 0;

                    expressionToEvaluate = calculatorUtilities.replaceForCalculations(
                            answerView.getText().toString()
                    );

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
                    if(!userDefValue1[0].equals("+")){userDefButtons.launchUserInputDialog(this, var1Button);}
                break;
            case R.id.var2Button:
                    if(!userDefValue2[0].equals("+")){userDefButtons.launchUserInputDialog(this, var2Button);}
                break;
            case R.id.var3Button:
                    if(!userDefValue3[0].equals("+")){userDefButtons.launchUserInputDialog(this, var3Button);}
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

    // AMAIR: Had to move the stuff from onClick to onTouch for animations to work.
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
                animBtnLogic(v, transition, event, NUM, 0);
                break;
            case R.id.oneButton:
                animBtnLogic(v, transition, event, NUM, 1);
                break;
            case R.id.twoButton:
                animBtnLogic(v, transition, event, NUM, 2);
                break;
            case R.id.threeButton:
                animBtnLogic(v, transition, event, NUM, 3);
                break;
            case R.id.fourButton:
                animBtnLogic(v, transition, event, NUM, 4);
                break;
            case R.id.fiveButton:
                animBtnLogic(v, transition, event, NUM, 5);
                break;
            case R.id.sixButton:
                animBtnLogic(v, transition, event, NUM, 6);
                break;
            case R.id.sevenButton:
                animBtnLogic(v, transition, event, NUM, 7);
                break;
            case R.id.eightButton:
                animBtnLogic(v, transition, event, NUM, 8);
                break;
            case R.id.nineButton:
                animBtnLogic(v, transition, event, NUM, 9);
                break;
            case R.id.dotButton:
                animBtnLogic(v, transition, event, NUM, 10);
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
        return false;
    }

    private void setBraceColor() {
        if (!leftBraceCounter.getText().equals(rightBraceCounter.getText())) {
            leftBraceCounter.setTextColor(Themer.colorArray.get(Themer.COLOR_ACCENT));
            rightBraceCounter.setTextColor(Themer.colorArray.get(Themer.COLOR_ACCENT));
        }
        else {
            leftBraceCounter.setTextColor(Themer.colorArray.get(Themer.COLOR_NUMPAD_DARK));
            rightBraceCounter.setTextColor(Themer.colorArray.get(Themer.COLOR_NUMPAD_DARK));
        }
    }


    public void animBtnLogic(View v, TransitionDrawable transition, MotionEvent event, String type, int num) {
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
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
                case NUM:
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
                    setBraceColor();
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
                    setBraceColor();
                    break;
                case "cos":
                    addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[1], ALL_BUTTONS.COS, true);
                    setBraceColor();
                    break;
                case "tan":
                    addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[2], ALL_BUTTONS.TAN, true);
                    setBraceColor();
                    break;
                case "log":
                    addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[3], ALL_BUTTONS.LOG, true);
                    setBraceColor();
                    break;
                case "ln":
                    addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[4], ALL_BUTTONS.LN, true);
                    setBraceColor();
                    break;
                case "pi":
                    addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[5], ALL_BUTTONS.PI, true);
                    break;
                case "euler":
                    addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[6], ALL_BUTTONS.EULER, true);
                    break;
                case "sqrt":
                    addToExpressionToBeEvaluated(ADVANCED_OPERANDS_ARRAY[7], ALL_BUTTONS.SQRT, true);
                    setBraceColor();
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
                case "ans":
                    addToExpressionToBeEvaluated(calculatorUtilities.getFromSharedPrefs("ANS"), ALL_BUTTONS.ANS, true);
                    break;
                case "user1":
                    if(!userDefValue1[0].equals("+")){
                        addToExpressionToBeEvaluated(userDefValue1[1], ALL_BUTTONS.CSTM, true);
                    }else{
                        userDefButtons.launchUserInputDialog(this, var1Button);
                    }
                    break;
                case "user2":
                    if(!userDefValue2[0].equals("+")){
                        addToExpressionToBeEvaluated(userDefValue2[1], ALL_BUTTONS.CSTM, true);
                    }else{
                        userDefButtons.launchUserInputDialog(this, var2Button);
                    }
                    break;
                case "user3":
                    if(!userDefValue3[0].equals("+")){
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

                    Log.d("SOLUTION", solution);
                    calculatorUtilities.postEqualLogic(isAnswer, customOperators, openBrace,
                            closeBrace, rightBraceCounter, leftBraceCounter, previousInput);
                    calculatorUtilities.saveToSharedPrefs("ANS", solution);
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

        Log.d("TYPE", type.toString());

        answerView.setText(expressionToEvaluate);
        expressionToEvaluate = calculatorUtilities.replaceForCalculations(expressionToEvaluate);
        answerView.setSelection(calculatorUtilities.determineCursorLocation(type, cursorLocation, valueToAppend));
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

        Log.d("OPEN_BRACKET_CHECK", openBrace+"");
        Log.d("CLOSE_BRACKET_CHECK", closeBrace+"");
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
