package org.sparkan.rule;



import org.sparkan.operator.BinaryOperator;
import org.sparkan.operator.Operator;
import org.sparkan.operator.UnaryOperator;
import org.sparkan.validator.ValidationError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class BaseRule implements Rule {
    protected final String fieldName;
    protected final Operator<Object> operator;
    protected final Object expectedVal;
    protected final String errorMessage;

    public BaseRule(String fieldName, Operator<Object> operator, Object expectedVal, String errorMessage) {
        this.fieldName = fieldName;
        this.operator = operator;
        this.expectedVal = expectedVal;
        this.errorMessage = errorMessage;
    }

    @Override
    public List<ValidationError> validate(Map<String, Object> jsonData) {
        List<ValidationError> errors = new ArrayList<>();
        Optional<Object> fieldValue = getNestedFieldValue(jsonData, fieldName);

        if (!fieldValue.isPresent()  || !applyOperator(fieldValue.get())) {
            errors.add(new ValidationError(fieldName, errorMessage));
        }

        return errors;
    }

    protected boolean applyOperator(Object currVal) {
        switch (operator.getOperatorType()) {
            case BINARY:
                return applyBinaryOperator(currVal);
            case UNARY:
                return applyUnaryOperator(currVal);
            default:
                throw new UnsupportedOperationException("Unknown operator type.");
        }
    }

    protected boolean applyBinaryOperator(Object currVal) {
        BinaryOperator<Object> binaryOp = (BinaryOperator<Object>) operator;
        return binaryOp.operate(currVal, expectedVal);
    }

    protected boolean applyUnaryOperator(Object currVal) {
        UnaryOperator<Object> unaryOp = (UnaryOperator<Object>) operator;
        return unaryOp.operate(currVal);
    }

    private Optional<Object> getNestedFieldValue(Map<String, Object> jsonData, String fieldName) {
        String[] keys = fieldName.split("\\.");
        Map<String, Object> currentMap = jsonData;
        Object value = null;

        for (int i = 0; i < keys.length; i++) {
            value = currentMap.get(keys[i]);
            if (value instanceof Map) {
                currentMap = (Map<String, Object>) value;
            } else if (i < keys.length - 1) {
                return Optional.empty(); // The path doesn't lead to a map as expected.
            }
        }

        return Optional.ofNullable(value);
    }
}
