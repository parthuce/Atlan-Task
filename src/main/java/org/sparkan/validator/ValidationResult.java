package org.sparkan.validator;

import java.util.List;

public class ValidationResult {
    private final boolean isValid;
    private final List<ValidationError> errors;

    public ValidationResult(boolean isValid, List<ValidationError> errors) {
        this.isValid = isValid;
        this.errors = errors;
    }

    public boolean isValid() {
        return isValid;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "ValidationResult{" +
                "isValid=" + isValid +
                ", errors=" + errors +
                '}';
    }
}

