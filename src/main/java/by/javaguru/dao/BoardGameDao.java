package by.javaguru.dao;

import by.javaguru.entity.BoardGame;
import by.javaguru.exception.DaoException;
import by.javaguru.validator.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BoardGameDao implements Dao<Long, BoardGame> {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static BoardGameDao INSTANCE = new BoardGameDao();

    private BoardGameDao() {
    }

    public static BoardGameDao getInstance() {
        return INSTANCE;
    }

    @Override
    public BoardGame save(BoardGame boardgame) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.persist(boardgame);
            transaction.commit();
            return boardgame;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e);
        }
    }

    @Override
    public boolean update(BoardGame boardgame) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.merge(boardgame);
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
    public boolean delete(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            BoardGame boardGame = session.get(BoardGame.class, id);
            session.remove(boardGame);
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
    public Optional<BoardGame> findById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            BoardGame boardGame = session.get(BoardGame.class, id);
            session.getTransaction().commit();

            return Optional.ofNullable(boardGame);
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<BoardGame> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<BoardGame> boardGames = session.createQuery("from BoardGame", BoardGame.class).getResultList();
            session.getTransaction().commit();

            return boardGames;
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    public List<BoardGame> findUserGamesById(Long userId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<BoardGame> query = session.createQuery("SELECT BG from BoardGame BG where BG.id = :id ", BoardGame.class);
            query.setParameter("id", userId);
            List<BoardGame> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    private static BoardGame getBoardGameFromResultSet(ResultSet resultSet) throws SQLException {
        return BoardGame.builder()
                .name(resultSet.getString("name"))
                .price(resultSet.getInt("price"))
                .year(resultSet.getShort("year"))
                .language(resultSet.getString("language"))
                .publisher(resultSet.getString("publisher"))
                .minPlayers(resultSet.getShort("min_players"))
                .maxPlayers(resultSet.getShort("max_players"))
                .build();
    }
}
