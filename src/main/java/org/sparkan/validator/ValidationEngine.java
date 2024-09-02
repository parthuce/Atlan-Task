package org.sparkan.validator;


import org.sparkan.rule.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ValidationEngine {

    public List<ValidationError> validate(Map<String ,Object> context, List<Rule> rules) {
        List<ValidationError> errors = new ArrayList<>();

        for (Rule rule : rules) {
            errors.addAll(rule.validate(context));
        }
        return errors;
    }
}
