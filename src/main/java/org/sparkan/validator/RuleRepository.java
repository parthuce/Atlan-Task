package org.sparkan.validator;


import org.sparkan.rule.Rule;

import java.util.ArrayList;
import java.util.List;

public class RuleRepository {
    private final List<Rule> rules = new ArrayList<>();

    public List<Rule> getRules() {
        return rules;
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public void removeRule(int ruleId) {
        rules.removeIf(rule -> rules.indexOf(rule) == ruleId);
    }

    public void updateRule(int ruleId, Rule updatedRule) {
        rules.set(ruleId, updatedRule);
    }
}

