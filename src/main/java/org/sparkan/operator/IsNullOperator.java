package org.sparkan.operator;

public class IsNullOperator<T> implements UnaryOperator<T> {
    @Override
    public boolean operate(Object currVal) {
        return currVal == null;
    }
}

