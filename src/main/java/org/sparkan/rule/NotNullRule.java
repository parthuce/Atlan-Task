package org.sparkan.rule;


import org.sparkan.operator.NotNullOperator;

public class NotNullRule extends BaseRule {
    public NotNullRule(String fieldName, String errorMessage) {
        super(fieldName, new NotNullOperator<>(), null, errorMessage);
    }
}

