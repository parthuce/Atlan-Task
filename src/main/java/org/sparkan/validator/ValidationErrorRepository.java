package org.sparkan.validator;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorRepository {
    private final List<ValidationError> errorDatabase = new ArrayList<>();

    public void saveError(ValidationError validationError) {
        errorDatabase.add(validationError);
    }

    public List<ValidationError> getErrors(int jsonDataId) {
        // In a real implementation, this method would fetch errors associated with a specific JSON entry
        return errorDatabase;
    }
}

