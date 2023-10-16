package by.javaguru.dto;

import by.javaguru.entity.UserRole;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    Long id;
    String login;
    String name;
    UserRole userRole;
}
