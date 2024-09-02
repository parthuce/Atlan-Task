package org.sparkan.validator;


import com.github.erosb.jsonsKema.*;
import org.json.JSONObject;

public class JSONSchemaValidator {

    private final Schema schema;

    public JSONSchemaValidator(String schemaJson) {
        this.schema = new SchemaLoader(schemaJson).load();
    }

    public boolean validateSchema(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            Validator validator = Validator.create(schema, new ValidatorConfig(FormatValidationPolicy.ALWAYS));
            JsonValue instance = new JsonParser(jsonObject.toString()).parse();
            ValidationFailure failure = validator.validate(instance);
           return failure == null;
        } catch (Exception e) {
            return false;
        }
    }
}
