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

    /**
     * 这里要注意精度
     */
    public static double calculate(String op, double num1, double num2) {
        if (op == null) throw new IllegalArgumentException("invalid expression!");
        //防止精度丢失，必须要将数字转成字符串来构造BigDecimal，不然没效果
        BigDecimal n1 = new BigDecimal(String.valueOf(num1));
        BigDecimal n2 = new BigDecimal(String.valueOf(num2));
        if (Op.PLUS.getOp().equals(op)) {
            return n1.add(n2).doubleValue();
        } else if (Op.MINUS.getOp().equals(op)) {
            return n1.subtract(n2).doubleValue();
        } else if (Op.MULTIPLY.getOp().equals(op)) {
            return n1.multiply(n2).doubleValue();
        } else if (Op.DIVIDE.getOp().equals(op)) {
            if (BigDecimal.ZERO.equals(n2)) throw new IllegalArgumentException("invalid expression!");
            return n1.divide(n2, 10, RoundingMode.HALF_UP).doubleValue();
        }
        throw new IllegalArgumentException("invalid operation");
    }

    public static boolean isNumber(String input) {
        return parseNumber(input) != null;
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

    /**
     * 将字符串input在lastIndex以前的字符截断
     */
    public static String cutHead(String input, int lastIndex) {
        return input.substring(lastIndex);
    }

    /**
     * 解析操作符
     */
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
