package by.javaguru.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CreateUserDto {
    String login;
    String password;
    String name;
    Integer roleId;
}
