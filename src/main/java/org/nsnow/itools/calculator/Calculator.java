package org.nsnow.itools.calculator;

import org.nsnow.itools.calculator.models.Op;
import org.nsnow.itools.calculator.models.Record;
import org.nsnow.itools.calculator.utils.CommonUtil;
import org.nsnow.itools.calculator.utils.ExpressionUtil;

import java.util.Scanner;
import java.util.Stack;

public class Calculator {

    private static Stack<Record> recordStack = new Stack<>();

    public static double redo() {
        if (recordStack.isEmpty()) {
            throw new IllegalArgumentException("no operation executed yet!");
        }
        Record record = recordStack.peek();
        double left = record.getResult();
        double right = record.getRight();
        String op = record.getOp();
        double newResult = CommonUtil.calculate(op, left, right);
        saveRecord(new Record(left, right, op, newResult));
        return newResult;
    }

    public static double undo() {
        if (recordStack.isEmpty()) {
            throw new IllegalArgumentException("no operation executed yet!");
        }
        Record record = recordStack.peek();
        double left = record.getResult();
        double right = record.getRight();
        String op = Op.parseOp(record.getOp()).getReversedOp();
        double newResult = CommonUtil.calculate(op, left, right);
        removeTop();
        return newResult;
    }

    public static void saveRecord(Record opRecord) {
        recordStack.push(opRecord);
    }

    public static void removeTop() {
        recordStack.pop();
    }

    //表达式：1.2+2*3-(4/2+2*3)-8
    //结果应该是：-8.8
    public static void main(String[] args) {
        System.out.println("请输入需要计算的表达式：");
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.next();
        expression = CommonUtil.removeBlank(expression);
        //中缀表达式转为后缀表达式
        Stack<String> postStack = ExpressionUtil.infixToPostfix(expression);
        Stack<String> reversedStack = new Stack<>();
        while (!postStack.isEmpty()) {
            reversedStack.push(postStack.pop());
        }

        double result = ExpressionUtil.evaluatePostfix(reversedStack);
        //求值后缀表达式
        System.out.println("表达式计算结果为：" + result);

        double redoResult = redo();

        System.out.println("重复上一次操作后的结果为：" + redoResult);

        double undoResult = undo();

        System.out.println("撤销上一次操作后的结果为：" + undoResult);

        undoResult = undo();

        System.out.println("撤销上一次操作后的结果为：" + undoResult);

        redoResult = redo();

        System.out.println("重复上一次操作后的结果为：" + redoResult);
    }

}
