package by.javaguru.repository;

import by.javaguru.entity.User;
import by.javaguru.exception.DaoException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class UserRepository extends BaseRepository<Long, User> {
    private static final UserRepository INSTANCE = new UserRepository();
    private final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    public UserRepository() {
        super(User.class);
    }

    public static UserRepository getInstance() {
        return INSTANCE;
    }

    public Optional<User> getUserByEmailAndPassword(String email, String password) {
        Query<User> query;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            query = session.createQuery("from User U where U.login =: email " +
                                        "AND U.password = : password", User.class);
            query.setParameter("email", email);
            query.setParameter("password", password);

            User user = query.getResultList().stream().findFirst().orElse(null);
            Optional<User> result = Optional.ofNullable(user);
            session.getTransaction().commit();

            return result;
        } catch (HibernateException e) {
            logger.error("Error finding User by email and password", e);
            throw new DaoException(e);
        }
    }

//    public boolean addGameToUser(User user, Long boardgameId) {
//        // TODO : implement
//        return false;
//    }
//
//    public boolean deleteGameFromUser(User user, Long boardgameId) {
//        // TODO : implement
//        return false;
//    }

}
