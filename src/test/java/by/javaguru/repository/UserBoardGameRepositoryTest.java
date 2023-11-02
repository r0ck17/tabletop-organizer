package by.javaguru.repository;

import by.javaguru.entity.BoardGame;
import by.javaguru.entity.UserBoardGame;
import by.javaguru.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class UserBoardGameRepositoryTest {
    private UserBoardGameRepository userBoardGameRepository = UserBoardGameRepository.getInstance();

    @Test
    void getBoardGamesCountTest() {
        Map<BoardGame, Long> gamesCount = userBoardGameRepository.getBoardGamesCount();

        assertThat(gamesCount).hasSize(3);

        assertThat(gamesCount.values()).contains(1L, 2L, 1L);
    }

    @Test
    void findUserGamesById() {
        Long userId = 2L;
        List<UserBoardGame> userGamesById = userBoardGameRepository.findUserGamesById(userId);

        assertThat(userGamesById).hasSize(2);
    }

    @Test
    void testSecondLevelCache() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session1 = sessionFactory.openSession();
        Session session2 = sessionFactory.openSession();

        session1.beginTransaction();
        session2.beginTransaction();

        UserBoardGame userBoardGame1 = session1.find(UserBoardGame.class, 1L);
        UserBoardGame userBoardGame2 = session2.find(UserBoardGame.class, 1L);

        BoardGame boardGame = session1.find(BoardGame.class, 1L);
        BoardGame boardGame1 = session2.find(BoardGame.class, 1L);

        session1.getTransaction().commit();
        session2.getTransaction().commit();

    }
}
