package org.sparkan.operator;

public interface Operator<T> {

    OperatorType getOperatorType();

    enum OperatorType {
        UNARY,
        BINARY
    }
}
