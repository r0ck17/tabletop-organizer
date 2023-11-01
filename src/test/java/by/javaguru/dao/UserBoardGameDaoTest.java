package by.javaguru.dao;

import by.javaguru.entity.BoardGame;
import by.javaguru.entity.UserBoardGame;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class UserBoardGameDaoTest {
    private UserBoardGameDao userBoardGameDao = UserBoardGameDao.getInstance();

    @Test
    void getBoardGamesCountTest() {
        Map<BoardGame, Long> gamesCount = userBoardGameDao.getBoardGamesCount();

        assertThat(gamesCount).hasSize(3);

        assertThat(gamesCount.values()).containsExactly(1L, 2L, 1L);
    }

    @Test
    void findUserGamesById() {
        Long userId = 2L;
        List<UserBoardGame> userGamesById = userBoardGameDao.findUserGamesById(userId);

        assertThat(userGamesById).hasSize(2);
    }
}
