package by.javaguru.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateUserDto {

    @NotBlank(message = "Никнейм не может быть пустым")
    @Size(min = 3, max = 30, message = "Длина никнейма от 3 до 30 символов")
    private String username;

    @Email(message = "Email должен быть корректным")
    @NotBlank(message = "Email не может быть пустым")
    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 3, max = 30, message = "Длина пароля от 3 до 30 символов")
    private String password;
}
