package org.sparkan.operator;

public class LessThanOperator<T extends Comparable<T>> implements BinaryOperator<T> {
    @Override
    public boolean operate(T currVal, T expectedVal) {
        return currVal.compareTo(expectedVal) < 0;
    }
}