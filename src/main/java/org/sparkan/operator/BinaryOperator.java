package org.sparkan.operator;

public interface BinaryOperator<T> extends Operator<T> {

    @Override
    default OperatorType getOperatorType() {
        return OperatorType.BINARY;
    }

    boolean operate(T currVal, T expectedVal);
}
