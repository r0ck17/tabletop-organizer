package by.javaguru.validator;

import by.javaguru.entity.UserBoardGame;
import by.javaguru.entity.BoardGame;
import by.javaguru.entity.User;
import by.javaguru.entity.UserRole;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


@UtilityClass
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .configure()
                    .addAnnotatedClass(UserRole.class)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(BoardGame.class)
                    .addAnnotatedClass(UserBoardGame.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }

}
