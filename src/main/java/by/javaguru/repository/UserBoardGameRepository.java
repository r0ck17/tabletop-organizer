package by.javaguru.repository;

import by.javaguru.entity.BoardGame;
import by.javaguru.entity.UserBoardGame;
import by.javaguru.exception.DaoException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserBoardGameRepository extends BaseRepository<Long, UserBoardGame> {
    private static final UserBoardGameRepository INSTANCE = new UserBoardGameRepository();
    private final Logger logger = LoggerFactory.getLogger(UserBoardGameRepository.class);

    public UserBoardGameRepository() {
        super(UserBoardGame.class);
    }

    public static UserBoardGameRepository getInstance() {
        return INSTANCE;
    }

    public List<UserBoardGame> findUserGamesById(Long userId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<UserBoardGame> query = session.createQuery(
                    "SELECT BG from UserBoardGame BG where BG.user.id = :id ",
                    UserBoardGame.class);
            query.setParameter("id", userId);
            List<UserBoardGame> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        } catch (HibernateException e) {
            logger.error("Error finding UserBoardGame entity", e);
            logger.debug("{}", userId);
            throw new DaoException(e);
        }
    }

    /**
     * Возвращает список всех игр в системе и количество пользователей, которые имеют данную игру в коллекции.
     * Будет использоваться для отображения статистики
     */
    public Map<BoardGame, Long> getBoardGamesCount() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Map<BoardGame, Long> games = session.createQuery(
                            """
                                    SELECT bg, count(*) FROM UserBoardGame ubg JOIN ubg.boardGame bg GROUP BY bg
                                    """,
                            Tuple.class)
                    .getResultStream()
                    .collect(Collectors.toMap(
                            tuple -> tuple.get(0, BoardGame.class),
                            tuple -> tuple.get(1, Long.class)
                    ));
            session.getTransaction().commit();
            return games;
        } catch (HibernateException e) {
            logger.error("Error finding all games count", e);
            throw new DaoException(e);
        }
    }
}
