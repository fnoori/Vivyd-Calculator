package ca.vivyd.vivydcalculator.calc_logic;

import android.util.Log;

import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.operator.Operator;

/**
 * Created by Farzam on 2016-06-20.
 *
 * Separate class for all the custom operators and functions
 */
public class CustomOperators {
    private boolean isComplexPercentage;

    private Operator factorialOperator = new Operator("!", 1, true, Operator.PRECEDENCE_POWER+1) {
        @Override
        public double apply(double... args) {

            final int arg = (int) args[0];
            if ((double) arg != args[0]) {
                throw new IllegalArgumentException("Operand for factorial has to be an integer");
            }
            if (arg < 0) {
                throw new IllegalArgumentException("The operand of the factorial can not be less than zero");
            }
            double result = 1;
            for (int i = 1; i <= arg; i++) {
                result *= i;
            }
            return result;
        }
    };

    private Operator nrt = new Operator("|=", 2, true, Operator.PRECEDENCE_DIVISION) {
        @Override
        public double apply(double... args) {

            final double nValue = args[0];
            final double arg = args[1];

            if(nValue < 1){
                throw new IllegalArgumentException("Root must be greater than 0");
            }

            double result = 1;
            result = Math.pow(arg, (1/nValue));
            return result;

        }
    };

    private Operator percentageOperator = new Operator("%", 1, true, Operator.PRECEDENCE_ADDITION) {
        @Override
        public double apply(double... args) {

            Log.d("ARG[0]", args[0] +"");

            return ((args[0] / 100));
        }
    };

    private Operator tenToPowerOfOperator = new Operator("§", 2, true, Operator.PRECEDENCE_MULTIPLICATION) {
        @Override
        public double apply(double... args) {
            return (args[0] * Math.pow(10, args[1]));
        }
    };

    private Function sinDegrees = new Function("sind", 1) {
        @Override
        public double apply(double... args) {
            return Math.sin(Math.toRadians(args[0]));
        }
    };

    private Function cosDegrees = new Function("cosd", 1) {
        @Override
        public double apply(double... args) {
            return Math.cos(Math.toRadians(args[0]));
        }
    };

    private Function tanDegrees = new Function("tand", 1) {
        @Override
        public double apply(double... args) {
            return Math.tan(Math.toRadians(args[0]));
        }
    };

    public CustomOperators(){
        isComplexPercentage = false;
    }
    public Operator getPercentageOperator(){
        return percentageOperator;
    }
    public Operator getFactorialOperator(){
        return factorialOperator;
    }
    public Operator getNrt(){
        return nrt;
    }
    public Operator getTenToPowerOfOperator(){return tenToPowerOfOperator;}
    public Function getSinDegrees(){
        return sinDegrees;
    }
    public Function getCosDegrees() {
        return cosDegrees;
    }
    public Function getTanDegrees(){
        return tanDegrees;
    }

    public void setIsComplexPercentage(boolean isComplexPercentage){
        this.isComplexPercentage = isComplexPercentage;
    }

    public boolean getIsComplexPercentage(){
        return isComplexPercentage;
    }
}
