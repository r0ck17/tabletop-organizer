package by.javaguru.mapper;

import by.javaguru.dto.UserBoardGameDto;
import by.javaguru.entity.UserBoardGame;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BoardGameMapper.class})
public interface UserBoardGameMapper {

    UserBoardGameDto toDto(UserBoardGame userBoardGame);
    UserBoardGame toModel(UserBoardGameDto userBoardGameDto);
}
