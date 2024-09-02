package org.sparkan.operator;

public class GreaterThanOperator<T extends Comparable<T>> implements BinaryOperator<T> {
    @Override
    public boolean operate(T currVal, T expectedVal) {
        return currVal.compareTo(expectedVal) > 0;
    }
}

