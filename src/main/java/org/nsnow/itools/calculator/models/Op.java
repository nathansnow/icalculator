package org.nsnow.itools.calculator.models;

public enum Op {

    PLUS("+", 1, "-"), MINUS("-", 1, "+"), MULTIPLY("*", 2, "/"), DIVIDE("/", 2, "*"), LEFT_BRACKET("(", 0, null), RIGHT_BRACKET(")", 0, null);

    private String op;
    private Integer priority;

    private String reversedOp;

    Op(String op, Integer priority, String reversedOp) {
        this.op = op;
        this.priority = priority;
        this.reversedOp = reversedOp;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getOp() {
        return op;
    }

    public String getReversedOp() {
        return reversedOp;
    }

    public static Op parseOp(String op) {
        for (Op val : Op.values()) {
            if (val.getOp().equals(op)) {
                return val;
            }
        }
        return null;
    }


}
