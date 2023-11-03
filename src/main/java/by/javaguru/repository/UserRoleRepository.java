package by.javaguru.repository;

import by.javaguru.entity.UserRole;
import by.javaguru.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserRoleRepository extends BaseRepository<Integer, UserRole> {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final UserRoleRepository INSTANCE = new UserRoleRepository();
    private final Logger logger = LoggerFactory.getLogger(UserRoleRepository.class);

    public UserRoleRepository() {
        super(UserRole.class);
    }

    public static UserRoleRepository getInstance() {
        return INSTANCE;
    }
}
