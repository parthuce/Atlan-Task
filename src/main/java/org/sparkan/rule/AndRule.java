package org.sparkan.rule;


import org.sparkan.validator.ValidationError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AndRule implements Rule {
    private final List<Rule> rules;

    public AndRule(List<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public List<ValidationError> validate(Map<String, Object> jsonData) {
        List<ValidationError> errors = new ArrayList<>();
        for (Rule rule : rules) {
            errors.addAll(rule.validate(jsonData));
        }
        return errors;
    }
}
