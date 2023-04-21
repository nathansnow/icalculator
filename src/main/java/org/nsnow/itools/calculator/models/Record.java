package org.nsnow.itools.calculator.models;

public class Record {

    private double left;
    private double right;
    private String op;
    private double result;

    public Record(double left, double right, String op, double result) {
        this.left = left;
        this.right = right;
        this.op = op;
        this.result = result;
    }

    public double getLeft() {
        return left;
    }

    public double getRight() {
        return right;
    }

    public String getOp() {
        return op;
    }

    public double getResult() {
        return result;
    }
}
