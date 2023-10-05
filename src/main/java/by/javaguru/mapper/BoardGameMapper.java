package by.javaguru.mapper;

import by.javaguru.dto.BoardGameDto;
import by.javaguru.entity.BoardGame;

public class BoardGameMapper implements Mapper<BoardGame, BoardGameDto> {
        private static final BoardGameMapper INSTANCE = new BoardGameMapper();

        private BoardGameMapper() {

        }

        public static BoardGameMapper getInstance() {
            return INSTANCE;
        }

        @Override
        public BoardGameDto mapFrom(BoardGame bg) {
            return BoardGameDto.builder()
                    .name(bg.getName())
                    .price(bg.getPrice())
                    .year(bg.getYear())
                    .language(bg.getLanguage())
                    .publisher(bg.getPublisher())
                    .minPlayers(bg.getMinPlayers())
                    .maxPlayers(bg.getMaxPlayers())
                    .build();
        }
    }
