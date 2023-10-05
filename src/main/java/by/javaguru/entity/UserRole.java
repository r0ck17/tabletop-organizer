package by.javaguru.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRole {
    private Integer id;
    private String role;
}
