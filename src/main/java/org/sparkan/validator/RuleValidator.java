package org.sparkan.validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sparkan.rule.Rule;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RuleValidator {

    private final RuleRepository ruleRepository;
    private final JSONSchemaValidator jsonSchemaValidator;
    private final ValidationEngine validationEngine;
    private final ValidationErrorRepository validationErrorRepository;
    ObjectMapper objectMapper = new ObjectMapper();


    public RuleValidator(RuleRepository ruleRepository, JSONSchemaValidator jsonSchemaValidator,
                         ValidationEngine validationEngine, ValidationErrorRepository validationErrorRepository) {
        this.ruleRepository = ruleRepository;
        this.jsonSchemaValidator = jsonSchemaValidator;
        this.validationEngine = validationEngine;
        this.validationErrorRepository = validationErrorRepository;
    }

    public ValidationResult validate(String jsonData) {
        // Step 1: Validate JSON Schema
        boolean schemaValid = jsonSchemaValidator.validateSchema(jsonData);
        if (!schemaValid) {
            return new ValidationResult(false, Collections.singletonList(new ValidationError("Schema", "Invalid JSON structure")));
        }

        // Step 2: Fetch Rules
        List<Rule> rules = ruleRepository.getRules();

        Map<String, Object> context = convertJsonToMap(jsonData);
        // Step 3: Apply Rules
        List<ValidationError> errors = validationEngine.validate(context, rules);

        // Step 4: Save Errors
        errors.forEach(validationErrorRepository::saveError);

        // Step 5: Return Validation Result
        return new ValidationResult(errors.isEmpty(), errors);
    }

    private Map<String, Object> convertJsonToMap(String jsonData) {
        try {
            return objectMapper.readValue(jsonData, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
