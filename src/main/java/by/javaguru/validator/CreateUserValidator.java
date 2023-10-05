package by.javaguru.validator;

import by.javaguru.dto.CreateUserDto;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserValidator implements Validator<CreateUserDto> {
    private static final CreateUserValidator INSTANCE = new CreateUserValidator();
    private final String PASSWORD_PATTERN = "[a-zA-Z0-9]{3,20}";
    private final String NAME_PATTERN = "[a-zA-Z0-9]{3,20}";
    private final String EMAIL_PATTERN = "[a-zA-Z0-9]{3,20}@[a-zA-Z0-9]{3,20}\\.(com|ru)";

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public ValidatorResult validate(CreateUserDto userDto) {
        ValidatorResult validatorResult = new ValidatorResult();

        if (!isMatchesRegexp(userDto.getLogin(), EMAIL_PATTERN)) {
            validatorResult.add(Error.of("invalid.email", "Некорректный email."));
        }

        if (!isMatchesRegexp(userDto.getPassword(), PASSWORD_PATTERN)) {
            validatorResult.add(Error.of("invalid.password", "Некорректный пароль."));
        }

        if (!isMatchesRegexp(userDto.getName(), NAME_PATTERN)) {
            validatorResult.add(Error.of("invalid.name", "Некорректное имя."));
        }

        return validatorResult;
    }

    private static boolean isMatchesRegexp(String value, String regexp) {
        return (value != null) && (value.matches(regexp));
    }
}
