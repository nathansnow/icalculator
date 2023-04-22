package org.nsnow.itools.calculator.utils;

import org.nsnow.itools.calculator.models.Op;
import org.nsnow.itools.calculator.models.Pair;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

    public static String removeBlank(String input) {
        return input.replaceAll("\\s", "");
    }

    public static double calculate(String op, double num1, double num2) {
        BigDecimal n1 = new BigDecimal(num1);
        BigDecimal n2 = new BigDecimal(num2);
        if (Op.PLUS.getOp().equals(op)) {
            return n1.add(n2).doubleValue();
        } else if (Op.MINUS.getOp().equals(op)) {
            return n1.subtract(n2).doubleValue();
        } else if (Op.MULTIPLY.getOp().equals(op)) {
            return n1.multiply(n2).doubleValue();
        } else if (Op.DIVIDE.getOp().equals(op)) {
            return n1.divide(n2, 10, RoundingMode.HALF_UP).doubleValue();
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
        if (!matcher.find()) {
            return null;
        }
        int position = matcher.end();
        String value = matcher.group(0);
        return new Pair(value, position);
    }

    public static String cut(String input, int lastIndex) {
        return input.substring(lastIndex);
    }

    public static Pair parseOp(String input) {
        String numberPattern = "[\\+\\-\\*/()]{1}";
        Pattern pattern = Pattern.compile(numberPattern);
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) {
            return null;
        }
        int position = matcher.end();
        String value = matcher.group(0);
        return new Pair(value, position);
    }


}
