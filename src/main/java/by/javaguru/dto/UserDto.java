package by.javaguru.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    Long id;
    String login;
    String name;
    Integer roleId;
}
