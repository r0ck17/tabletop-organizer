package by.javaguru.repository;

import by.javaguru.entity.BoardGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardGameRepository extends BaseRepository<Long, BoardGame> {

    private static final BoardGameRepository INSTANCE = new BoardGameRepository();
    private final Logger logger = LoggerFactory.getLogger(BoardGameRepository.class);

    public BoardGameRepository() {
        super(BoardGame.class);
    }

    public static BoardGameRepository getInstance() {
        return INSTANCE;
    }
}
