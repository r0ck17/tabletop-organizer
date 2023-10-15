package by.javaguru.dao;

import by.javaguru.entity.UserRole;
import by.javaguru.exception.DaoException;
import by.javaguru.validator.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class UserRoleDao implements Dao<Integer, UserRole> {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static UserRoleDao INSTANCE = new UserRoleDao();

    private UserRoleDao() {
    }

    public static UserRoleDao getInstance() {
        return INSTANCE;
    }

    @Override
    public UserRole save(UserRole userRole) {
        Transaction transaction = null;

        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.persist(userRole);
            transaction.commit();

            return userRole;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e);
        }
    }

    @Override
    public boolean update(UserRole userRole) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.merge(userRole);
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
            UserRole role = session.get(UserRole.class, id);
            session.remove(role);
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
    public Optional<UserRole> findById(Integer id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            UserRole userRole = session.get(UserRole.class, id);
            session.getTransaction().commit();

            return Optional.ofNullable(userRole);
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<UserRole> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<UserRole> tickets = session.createQuery("from UserRole", UserRole.class).getResultList();
            session.getTransaction().commit();

            return tickets;
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }
}
