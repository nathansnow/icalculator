package org.nsnow.itools.calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

    public static String removeBlank(String input) {
        return input.replaceAll("\\s", "");
    }

    public static double calculate(String op,double num1,double num2){
        if(Op.PLUS.getOp().equals(op)){
            return num1 + num2;
        }else if(Op.MINUS.getOp().equals(op)){
            return num1 - num2;
        }else if(Op.MULTIPLY.getOp().equals(op)){
            return num1 * num2;
        }else if(Op.DIVIDE.getOp().equals(op)){
            return num1 / num2;
        }
        throw new IllegalArgumentException("invalid operation");
    }

    public static boolean isNumber(String input) {
        String numberPattern = "^[0-9]*\\.?[0-9]+";
        Pattern pattern = Pattern.compile(numberPattern);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static Pair parseNumber(String input) {
        String numberPattern = "^[0-9]*\\.?[0-9]+";
        Pattern pattern = Pattern.compile(numberPattern);
        Matcher matcher = pattern.matcher(input);
        if(!matcher.find()){
            return null;
        }
        int position = matcher.end();
        String value = matcher.group(0);
        return new Pair(value,position);
    }

    public static String cut(String input,int lastIndex){
        return input.substring(lastIndex);
    }

    public static Pair parseOp(String input) {
        String numberPattern = "[\\+\\-\\*/()]{1}";
        Pattern pattern = Pattern.compile(numberPattern);
        Matcher matcher = pattern.matcher(input);
        if(!matcher.find()){
            return null;
        }
        int position = matcher.end();
        String value = matcher.group(0);
        return new Pair(value,position);
    }


}
