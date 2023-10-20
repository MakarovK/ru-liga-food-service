package ru.liga.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.hibernate.cfg.Configuration;
import ru.liga.entity.Courier;

import javax.persistence.NoResultException;

@Component
public class CourierDAO {
    private final SessionFactory sessionFactory;

    public CourierDAO() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public Courier getCourierById(Long id) {
        try (Session session = sessionFactory.openSession();) {
            Transaction transaction = session.beginTransaction();
            Courier courier;
            try {
                courier = session.find(Courier.class, id);
                transaction.commit();
            } catch (NoResultException e) {
                courier = null;
                transaction.rollback();
            }
            return courier;
        }
    }
}
