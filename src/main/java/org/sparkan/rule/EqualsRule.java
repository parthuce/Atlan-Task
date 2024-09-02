package org.sparkan.rule;


import org.sparkan.operator.EqualsOperator;

public class EqualsRule extends BaseRule {
    public EqualsRule(String fieldName, Object expectedVal, String errorMessage) {
        super(fieldName, new EqualsOperator<>(), expectedVal, errorMessage);
    }
}
