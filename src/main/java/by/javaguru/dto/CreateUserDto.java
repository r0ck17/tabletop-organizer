package by.javaguru.dto;

import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@Builder
@Value
public class CreateUserDto {
    @NotEmpty(message = "Email не может быть пустым")
    @Email(message = "Email должен быть корректным")
    String login;

    @NotEmpty(message = "Пароль не может быть пустым")
    String password;

    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно быть от 2 до 30 символов")
    String name;
    Integer roleId;
}
