package org.nsnow.itools.calculator;

import java.util.Stack;

public class ExpressionUtil {


    /**
     * 转为后缀表达式
     * @param expression 中缀表达式
     * @return 拆分后的后缀表达式堆栈
     */
    public static Stack<String> infixToPostfix(String expression) {
        Stack<String> resultStack = new Stack<>();
        Stack<String> tmpStack = new Stack<>();

        while (expression.length() > 0) {
            Pair pair = CommonUtil.parseNumber(expression);

            if (pair != null) {
                //是数字
                resultStack.push(pair.getValue());
                //将解析到的值拿掉
                expression = CommonUtil.cut(expression, pair.getPosition());
                continue;
            }
            Pair opPair = CommonUtil.parseOp(expression);
            if (opPair == null) {
                throw new IllegalArgumentException("Invalid Expression");
            }

            String value = opPair.getValue();
            if (Op.LEFT_BRACKET.getOp().equals(value)) {
                tmpStack.push(value);
                expression = CommonUtil.cut(expression, opPair.getPosition());
            } else if (Op.RIGHT_BRACKET.getOp().equals(value)) {
                while (!tmpStack.isEmpty() && !tmpStack.peek().equals(Op.LEFT_BRACKET.getOp())) {
                    resultStack.push(tmpStack.pop());
                }

                if (!tmpStack.isEmpty() && !tmpStack.peek().equals(Op.LEFT_BRACKET.getOp())) {
                    throw new IllegalArgumentException("Invalid Expression");
                } else {
                    tmpStack.pop();
                }
                expression = CommonUtil.cut(expression, opPair.getPosition());
            } else{

                while (!tmpStack.isEmpty() && Op.parseOp(value).getPriority() <= Op.parseOp(tmpStack.peek()).getPriority()) {
                    if (tmpStack.peek().equals(Op.LEFT_BRACKET.getOp())) {
                        throw new IllegalArgumentException("Invalid Expression");
                    }

                    resultStack.push(tmpStack.pop());
                }

                tmpStack.push(value);
                expression = CommonUtil.cut(expression, opPair.getPosition());
            }

        }

        while (!tmpStack.isEmpty()) {
            if (tmpStack.peek().equals(Op.LEFT_BRACKET.getOp())) {
                throw new IllegalArgumentException("Invalid Expression");
            }

            resultStack.push(tmpStack.pop());
        }

        return resultStack;
    }

    /**
     * 后缀表达式求值
     * @param postfix 后缀表达式对战
     * @return 值
     */
    public static double evaluatePostfix(Stack<String> postfix) {
        Stack<Double> stack = new Stack<>();
        while(!postfix.isEmpty()){

            String ch = postfix.pop();

            if(CommonUtil.isNumber(ch)) {
                stack.push(Double.parseDouble(ch));
                continue;
            }

            Pair pair = CommonUtil.parseOp(ch);
            if(pair == null){
                throw new IllegalArgumentException("invalid operation");
            }

            double num2 = stack.pop();
            double num1 = stack.pop();

            double result = CommonUtil.calculate(pair.getValue(),num1,num2);
            stack.push(result);
        }


        return stack.pop();
    }
}
