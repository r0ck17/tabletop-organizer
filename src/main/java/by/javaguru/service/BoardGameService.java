package by.javaguru.service;

import by.javaguru.dao.BoardGameDao;
import by.javaguru.dto.BoardGameDto;
import by.javaguru.dto.UserBoardGameDto;
import by.javaguru.entity.BoardGame;
import by.javaguru.mapper.BoardGameDtoMapper;
import by.javaguru.mapper.BoardGameMapper;
import by.javaguru.mapper.UserBoardGameMapper;

import java.util.List;
import java.util.Optional;

public final class BoardGameService {
    private static final BoardGameService INSTANCE = new BoardGameService();
    private static final BoardGameDtoMapper BOARD_GAME_DTO_MAPPER = BoardGameDtoMapper.getInstance();
    private static final BoardGameMapper BOARD_GAME_MAPPER = BoardGameMapper.getInstance();
    private static final UserBoardGameMapper USER_BOARD_GAME_MAPPER = UserBoardGameMapper.getInstance();
    private static BoardGameDao boardGameDao = BoardGameDao.getInstance();

    private BoardGameService() {

    }

    public static BoardGameService getInstance() {
        return INSTANCE;
    }

    public boolean save(BoardGameDto boardGameDto) {
        BoardGame boardGame = BOARD_GAME_DTO_MAPPER.mapFrom(boardGameDto);
        return boardGameDao.save(boardGame) != null;
    }

    public boolean update(BoardGameDto boardGameDto) {
        BoardGame boardGame = BOARD_GAME_DTO_MAPPER.mapFrom(boardGameDto);
        return boardGameDao.update(boardGame);
    }

    public boolean delete(Long id) {
        return boardGameDao.delete(id);
    }

    public Optional<BoardGameDto> findById(Long id) {
        return boardGameDao.findById(id)
                .map((g) -> Optional.of(BOARD_GAME_MAPPER.mapFrom(g)))
                .orElse(Optional.empty());
    }

    public List<BoardGameDto> findAll() {
        return boardGameDao.findAll().stream()
                .map((game) -> BOARD_GAME_MAPPER.mapFrom(game))
                .toList();
    }

    public List<UserBoardGameDto> findUserGamesById(Long userId) {
        return boardGameDao.findUserGamesById(userId).stream()
                .map(USER_BOARD_GAME_MAPPER::mapFrom)
                .toList();
    }
}
