package org.sparkan.operator;

public class EqualsOperator<T> implements BinaryOperator<T> {
    @Override
    public boolean operate(T currVal, T expectedVal) {
        return currVal.equals(expectedVal);
    }
}