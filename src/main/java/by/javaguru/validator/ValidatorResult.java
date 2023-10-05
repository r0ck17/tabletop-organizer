package by.javaguru.validator;

import java.util.ArrayList;
import java.util.List;

public class ValidatorResult {
    private final List<Error> errors = new ArrayList<>();

    public void add(Error error) {
        errors.add(error);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public List<Error> getErrors() {
        return errors;
    }
}
