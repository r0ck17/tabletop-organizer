package by.javaguru.mapper;

import by.javaguru.dto.CreateUserDto;
import by.javaguru.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toModel(CreateUserDto createUserDto);
    CreateUserDto toDto(User user);
}