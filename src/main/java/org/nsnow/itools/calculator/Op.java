package org.nsnow.itools.calculator;

public enum Op {

    PLUS("+",1),MINUS("-",1),MULTIPLY("*",2),DIVIDE("/",2)
    , LEFT_BRACKET("(",0), RIGHT_BRACKET(")",0);

    private String op;
    private Integer priority;

    Op(String op, Integer priority) {
        this.op = op;
        this.priority = priority;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getOp() {
        return op;
    }

    public static Op parseOp(String op){
        for(Op val: Op.values()){
            if(val.getOp().equals(op)){
                return val;
            }
        }
        return null;
    }


}
