package by.javaguru.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateUserDto {

    private String username;
    private String email;
    private String password;
}
