package by.javaguru.mapper;

import by.javaguru.dto.UserDto;
import by.javaguru.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<User, UserDto> {
    private static final UserMapper INSTANCE = new UserMapper();

    public static UserMapper getInstance() {
        return INSTANCE;
    }
    
    @Override
    public UserDto mapFrom(User object) {
        return UserDto.builder()
                .id(object.getId())
                .name(object.getName())
                .login(object.getLogin())
                .roleId(object.getRoleId())
                .build();
    }
}
