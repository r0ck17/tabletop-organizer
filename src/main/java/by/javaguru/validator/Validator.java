package by.javaguru.validator;

public interface Validator<T> {
    ValidatorResult validate(T object);
}
