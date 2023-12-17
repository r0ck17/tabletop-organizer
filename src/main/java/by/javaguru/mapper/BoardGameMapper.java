package by.javaguru.mapper;

import by.javaguru.dto.BoardGameDto;
import by.javaguru.entity.BoardGame;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoardGameMapper {

    BoardGameDto toDto(BoardGame boardGame);
    BoardGame toModel(BoardGameDto boardGameDto);
}
