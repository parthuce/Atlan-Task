package org.sparkan.rule;


import org.sparkan.validator.ValidationError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrRule implements Rule {
    private final List<Rule> rules;

    public OrRule(List<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public List<ValidationError> validate(Map<String, Object> jsonData) {
        List<ValidationError> errors = new ArrayList<>();
        for (Rule rule : rules) {
            List<ValidationError> ruleErrors = rule.validate(jsonData);
            if (ruleErrors.isEmpty()) {
                return new ArrayList<>(); // No errors, so OR condition is satisfied
            }
            errors.addAll(ruleErrors);
        }
        return errors;
    }
}

