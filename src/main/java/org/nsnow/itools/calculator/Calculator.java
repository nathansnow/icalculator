package org.nsnow.itools.calculator;

import java.util.Scanner;
import java.util.Stack;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //表达式：1.2+2*3-(4/2+2*3)-8
        //结果应该是：-8.8
        String expression = scanner.next();
        expression = CommonUtil.removeBlank(expression);
        //中缀表达式转为后缀表达式
        Stack<String> postStack = ExpressionUtil.infixToPostfix(expression);
        Stack<String> reversedStack = new Stack<>();
        while(!postStack.isEmpty()){
            reversedStack.push(postStack.pop());
        }
        //求值后缀表达式
        System.out.println(ExpressionUtil.evaluatePostfix(reversedStack));
    }

}
