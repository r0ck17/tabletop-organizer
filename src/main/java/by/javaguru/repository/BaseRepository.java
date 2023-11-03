package by.javaguru.repository;

import by.javaguru.entity.BaseEntity;
import by.javaguru.exception.DaoException;
import by.javaguru.util.HibernateUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BaseRepository<K extends Serializable, E extends BaseEntity<K>>
        implements Repository<K, E> {

    @Getter
    protected static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final Class<E> clazz;

    @Override
    public E save(E entity) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
            return entity;
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(E entity) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(K id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.delete(session.find(clazz, id));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<E> findById(K id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Optional<E> e = Optional.ofNullable(session.find(clazz, id));
            session.getTransaction().commit();
            return e;
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<E> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            CriteriaQuery<E> criteria = session.getCriteriaBuilder().createQuery(clazz);
            criteria.from(clazz);
            List<E> resultList = session.createQuery(criteria).getResultList();
            session.getTransaction().commit();
            return resultList;
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }
}
