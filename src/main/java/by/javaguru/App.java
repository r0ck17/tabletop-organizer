package by.javaguru;

import by.javaguru.validator.HibernateUtil;
import org.hibernate.SessionFactory;

public class App {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.close();
    }
}
