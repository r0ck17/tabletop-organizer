package by.javaguru.repository;

import by.javaguru.entity.BoardGame;
import by.javaguru.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

public class BoardGameRepository extends BaseRepository<Long, BoardGame> {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final BoardGameRepository INSTANCE = new BoardGameRepository(sessionFactory.getCurrentSession());
    private final Logger logger = LoggerFactory.getLogger(BoardGameRepository.class);

    public BoardGameRepository(EntityManager entityManager) {
        super(entityManager, BoardGame.class);
    }


    public static BoardGameRepository getInstance() {
        return INSTANCE;
    }
}
