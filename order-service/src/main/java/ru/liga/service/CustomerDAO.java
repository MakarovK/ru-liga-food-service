package ru.liga.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.liga.entity.Customer;

import javax.persistence.NoResultException;
@Component
public class CustomerDAO {
    private final SessionFactory sessionFactory;

    public CustomerDAO() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public Customer getCustomerById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer;
        try {
            customer = session.find(Customer.class, id); // Поиск по ID
            transaction.commit();
        } catch (NoResultException e) {
            customer = null;
            transaction.rollback();
        } finally {
            session.close();
        }

        return customer;
    }
}
