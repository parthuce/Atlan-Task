package org.sparkan.rule;


import org.sparkan.validator.ValidationError;

import java.util.List;
import java.util.Map;

public interface Rule {
    List<ValidationError> validate(Map<String, Object> jsonData);
}
