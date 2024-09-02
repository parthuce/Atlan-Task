package org.sparkan;

import org.sparkan.rule.*;
import org.sparkan.validator.*;

import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        // Initialize dependencies
        RuleRepository inMemoryRuleRepository = new RuleRepository(); // Assuming an in-memory implementation

        Rule equalsRule = new EqualsRule("person.age", 30, "Age must be 30.");
        Rule notNullRule = new NotNullRule("person.name", "Name must not be null.");

        List<Rule> rules = Arrays.asList(equalsRule, notNullRule);

        Rule andRule = new AndRule(rules);
        Rule orRule = new OrRule(rules);

        inMemoryRuleRepository.addRule(andRule);
        inMemoryRuleRepository.addRule(orRule);

        JSONSchemaValidator jsonSchemaValidator = new JSONSchemaValidator(
                "{\n" +
                        "  \"$schema\": \"https://json-schema.org/draft/2020-12/schema\",\n" +
                        "  \"type\": \"object\",\n" +
                        "  \"properties\": {\n" +
                        "    \"person\": {\n" +
                        "      \"type\": \"object\",\n" +
                        "      \"properties\": {\n" +
                        "        \"name\": {\n" +
                        "          \"type\": \"string\"\n" +
                        "        },\n" +
                        "        \"age\": {\n" +
                        "          \"type\": \"integer\",\n" +
                        "          \"minimum\": 0\n" +
                        "        }\n" +
                        "      },\n" +
                        "      \"required\": [\"name\", \"age\"],\n" +
                        "      \"additionalProperties\": false\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"required\": [\"person\"],\n" +
                        "  \"additionalProperties\": false\n" +
                        "}");

        // Example schema
        ValidationResult result = getValidationResult(inMemoryRuleRepository, jsonSchemaValidator);

        // Print validation result
        System.out.println("Validation successful: " + result.isValid());
        for (ValidationError error : result.getErrors()) {
            System.out.println("Error: " + error.getFieldName() + " - " + error.getErrorMessage());
        }
    }

    private static ValidationResult getValidationResult(RuleRepository inMemoryRuleRepository, JSONSchemaValidator jsonSchemaValidator) {
        ValidationEngine validationEngine = new ValidationEngine();
        ValidationErrorRepository inMemoryValidationErrorRepository = new ValidationErrorRepository(); // Assuming an in-memory implementation

        // Create RuleValidator instance
        RuleValidator ruleValidator = new RuleValidator(inMemoryRuleRepository, jsonSchemaValidator, validationEngine, inMemoryValidationErrorRepository);

        // Example JSON data
        String jsonData = "{\n" +
                "  \"person\": {\n" +
                "    \"name\": \"John\",\n" +
                "    \"age\": 39\n" +
                "  }\n" +
                "}";

        // Validate JSON data
        return ruleValidator.validate(jsonData);
    }
}
