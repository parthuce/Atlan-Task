package org.sparkan.operator;

public class NotNullOperator<T> implements UnaryOperator<T> {
    @Override
    public boolean operate(T currVal) {
        return currVal != null;
    }
}
