package by.javaguru.service;

import by.javaguru.dto.BoardGameDto;
import by.javaguru.dto.UserBoardGameDto;
import by.javaguru.entity.BoardGame;
import by.javaguru.mapper.BoardGameDtoMapper;
import by.javaguru.mapper.BoardGameMapper;
import by.javaguru.mapper.UserBoardGameMapper;
import by.javaguru.repository.BoardGameRepository;
import by.javaguru.repository.UserBoardGameRepository;

import java.util.List;
import java.util.Optional;

public final class BoardGameService {
    private static final BoardGameService INSTANCE = new BoardGameService();
    private static final BoardGameDtoMapper BOARD_GAME_DTO_MAPPER = BoardGameDtoMapper.getInstance();
    private static final BoardGameMapper BOARD_GAME_MAPPER = BoardGameMapper.getInstance();
    private static final UserBoardGameMapper USER_BOARD_GAME_MAPPER = UserBoardGameMapper.getInstance();
    private static final BoardGameRepository BOARD_GAME_REPOSITORY = BoardGameRepository.getInstance();
    private static final UserBoardGameRepository USER_BOARD_GAME_REPOSITORY = UserBoardGameRepository.getInstance();

    private BoardGameService() {

    }

    public static BoardGameService getInstance() {
        return INSTANCE;
    }

    public boolean save(BoardGameDto boardGameDto) {
        BoardGame boardGame = BOARD_GAME_DTO_MAPPER.mapFrom(boardGameDto);
        return BOARD_GAME_REPOSITORY.save(boardGame) != null;
    }

    public void update(BoardGameDto boardGameDto) {
        BoardGame boardGame = BOARD_GAME_DTO_MAPPER.mapFrom(boardGameDto);
    }

    public void delete(Long id) {
        BOARD_GAME_REPOSITORY.delete(id);
    }

    public Optional<BoardGameDto> findById(Long id) {
        return BOARD_GAME_REPOSITORY.findById(id)
                .map((g) -> Optional.of(BOARD_GAME_MAPPER.mapFrom(g)))
                .orElse(Optional.empty());
    }

    public List<BoardGameDto> findAll() {
        return BOARD_GAME_REPOSITORY.findAll().stream()
                .map(BOARD_GAME_MAPPER::mapFrom)
                .toList();
    }

    public List<UserBoardGameDto> findUserGamesById(Long userId) {
        return USER_BOARD_GAME_REPOSITORY.findUserGamesById(userId).stream()
                .map(USER_BOARD_GAME_MAPPER::mapFrom)
                .toList();
    }
}
