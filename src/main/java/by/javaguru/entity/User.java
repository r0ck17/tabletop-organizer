package by.javaguru.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Long id;
    private Integer roleId;
    private String name;
    private String login;
    private String password;
}
