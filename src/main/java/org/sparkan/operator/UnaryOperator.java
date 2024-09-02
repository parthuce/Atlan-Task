package org.sparkan.operator;

public interface UnaryOperator<T> extends Operator<T> {

    @Override
    default OperatorType getOperatorType() {
        return OperatorType.UNARY;
    }

    boolean operate(T currVal);
}
