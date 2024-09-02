package org.sparkan.operator;

import java.util.Collection;

public class IsNotEmptyOperator<T> implements UnaryOperator<T> {
    @Override
    public boolean operate(T currVal) {
        if (currVal instanceof String) {
            return !((String) currVal).isEmpty();
        } else if (currVal instanceof Collection) {
            return !((Collection<?>) currVal).isEmpty();
        } else {
            throw new UnsupportedOperationException("IsNotEmptyOperator supports only String or Collection types.");
        }
    }
}

