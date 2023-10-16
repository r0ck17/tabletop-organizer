package by.javaguru.dao;

import by.javaguru.entity.User;
import by.javaguru.exception.DaoException;
import by.javaguru.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Integer, User> {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final UserDao INSTANCE = new UserDao();

    private UserDao() {
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @Override
    public User save(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            return user;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e);
        }
    }

    @Override
    public boolean update(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            transaction.commit();

            return true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> findById(Integer id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.getTransaction().commit();

            return Optional.ofNullable(user);
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("from User", User.class).getResultList();
            session.getTransaction().commit();

            return users;
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
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
        }
    }

    public boolean addGameToUser(User user, Long boardgameId) {
        // TODO : implement
        return false;
    }

    public boolean deleteGameFromUser(User user, Long boardgameId) {
        // TODO : implement
        return false;
    }

}
