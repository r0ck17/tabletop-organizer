package by.javaguru.dao;

import by.javaguru.entity.UserBoardGame;
import by.javaguru.exception.DaoException;
import by.javaguru.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserBoardGameDao {
    private static final UserBoardGameDao INSTANCE = new UserBoardGameDao();
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final Logger logger = LoggerFactory.getLogger(UserBoardGameDao.class);

    private UserBoardGameDao() {
    }

    public static UserBoardGameDao getInstance() {
        return INSTANCE;
    }

    public List<UserBoardGame> findUserGamesById(Long userId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<UserBoardGame> query = session.createQuery("SELECT BG from UserBoardGame BG where BG.user.id = :id ", UserBoardGame.class);
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
}
