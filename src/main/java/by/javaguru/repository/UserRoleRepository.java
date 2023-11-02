package by.javaguru.repository;

import by.javaguru.entity.UserRole;
import by.javaguru.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

public class UserRoleRepository extends BaseRepository<Integer, UserRole> {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final UserRoleRepository INSTANCE = new UserRoleRepository(sessionFactory.getCurrentSession());
    private final Logger logger = LoggerFactory.getLogger(UserRoleRepository.class);

    public UserRoleRepository(EntityManager entityManager) {
        super(entityManager, UserRole.class);
    }

    public static UserRoleRepository getInstance() {
        return INSTANCE;
    }
}
