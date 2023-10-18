package ru.liga.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import ru.liga.entity.Restaurant;

import javax.persistence.NoResultException;


@Component
public class RestaurantDAO {
    private final SessionFactory sessionFactory;

    public RestaurantDAO() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public Restaurant getRestaurantById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Restaurant restaurant;
        try {
            restaurant = session.find(Restaurant.class, id);
            transaction.commit();
        } catch (NoResultException e) {
            restaurant = null;
            transaction.rollback();
        } finally {
            session.close();
        }
        return restaurant;
    }

}
