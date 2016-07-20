package ca.vivyd.vivydcalculator;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import ca.vivyd.vivydcalculator.calc_logic.CalculatorButtons;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    /*
    private CalculatorButtons calculatorButtons;
    private String solutionGot;
    private String solutionExpected;
    private String toEvaluate;

    private NumberFormat numFormat;
    */

    public ApplicationTest() {
        super(Application.class);
        /*
        calculatorButtons = new CalculatorButtons();
        solutionExpected = null;
        solutionGot = null;
        toEvaluate = null;

        numFormat = new DecimalFormat("##.##########");

        trivial_simple_test();
        multiple_bracket_test();
        trig_functions();
        single_num_percentage();
        double_num_percenage();
        change_to_degrees_trig();
        change_to_radians_trig();
        nth_root();
        complex_log_test();
        cube_root_nth_root_factorial_test();
        */
    }

    /*
    public void trivial_simple_test(){
        toEvaluate = "2+4";
        solutionGot = calculatorButtons.setExpressionEvalString(toEvaluate);
        solutionExpected = numFormat.format(6);
        display_expected_and_got();
        assertEquals(solutionExpected, solutionGot);
    }

    public void multiple_bracket_test(){
        toEvaluate = "(((2*(4+3)/5)-45)+234)/45";
        solutionGot = calculatorButtons.setExpressionEvalString(toEvaluate);
        solutionExpected = "4.2622222222";
        display_expected_and_got();
        assertEquals(solutionExpected, solutionGot);
    }

    public void trig_functions(){
        toEvaluate = "cos(((sin(90)*2)+240))";
        solutionGot = calculatorButtons.setExpressionEvalString(toEvaluate);
        solutionExpected = "-0.9934359144";
        display_expected_and_got();
        assertEquals(solutionExpected, solutionGot);
    }

    public void single_num_percentage(){
        toEvaluate = "45%";
        solutionGot = calculatorButtons.setExpressionEvalString(toEvaluate);
        solutionExpected = "0.45";
        display_expected_and_got();
        assertEquals(solutionExpected, solutionGot);
    }

    public void double_num_percenage(){
        toEvaluate = "23%78";
        solutionGot = calculatorButtons.setExpressionEvalString(toEvaluate);
        solutionExpected = "17.94";
        display_expected_and_got();
        assertEquals(solutionExpected, solutionGot);
    }

    public void change_to_degrees_trig(){
        toEvaluate = "sin(200)*cos(0)";
        calculatorButtons.changeTrigType();
        solutionGot = calculatorButtons.setExpressionEvalString(toEvaluate);
        solutionExpected = "-0.3420201433";
        display_expected_and_got();
        assertEquals(solutionExpected, solutionGot);
    }

    public void change_to_radians_trig(){
        toEvaluate = "sin(200)*cos(0)";
        calculatorButtons.changeTrigType();
        solutionGot = calculatorButtons.setExpressionEvalString(toEvaluate);
        solutionExpected = "-0.8732972972";
        display_expected_and_got();
        assertEquals(solutionExpected, solutionGot);
    }

    public void nth_root(){
        toEvaluate = "((3|=345)*(4|=456))/(2|=16)";
        solutionGot = calculatorButtons.setExpressionEvalString(toEvaluate);
        solutionExpected = "8.1025350372";
        display_expected_and_got();
        assertEquals(solutionExpected, solutionGot);
    }

    public void complex_log_test(){
        toEvaluate = "((log(25)*log10(234))/(sqrt(16)))*(3|=465)";
        solutionGot = calculatorButtons.setExpressionEvalString(toEvaluate);
        solutionExpected = "14.7706581457";
        display_expected_and_got();
        assertEquals(solutionExpected, solutionGot);
    }

    public void cube_root_nth_root_factorial_test(){
        calculatorButtons.changeTrigType();
        toEvaluate = "floor((((sqrt(928)*(3|=789))/((sin(90))+345))*(4|=102)))!";
        solutionGot = calculatorButtons.setExpressionEvalString(toEvaluate);
        solutionExpected = "2";
        display_expected_and_got();
        assertEquals(solutionExpected, solutionGot);
    }

    private void display_expected_and_got(){
        Log.d("SOLUTION_GOT", solutionGot);
        Log.d("SOLUTION_EXPECTED", solutionExpected);
    }

    private void reset_vars(){
        solutionExpected = null;
        solutionGot = null;
        toEvaluate = null;
    }
    */
}