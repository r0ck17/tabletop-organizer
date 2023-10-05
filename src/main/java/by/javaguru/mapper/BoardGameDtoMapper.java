package by.javaguru.mapper;

import by.javaguru.dto.BoardGameDto;
import by.javaguru.entity.BoardGame;

public class BoardGameDtoMapper implements Mapper<BoardGameDto, BoardGame> {
    private static final BoardGameDtoMapper INSTANCE = new BoardGameDtoMapper();

    private BoardGameDtoMapper() {

    }

    public static BoardGameDtoMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public BoardGame mapFrom(BoardGameDto dto) {
        return BoardGame.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .year(dto.getYear())
                .language(dto.getLanguage())
                .publisher(dto.getPublisher())
                .minPlayers(dto.getMinPlayers())
                .maxPlayers(dto.getMaxPlayers())
                .build();
    }
}
