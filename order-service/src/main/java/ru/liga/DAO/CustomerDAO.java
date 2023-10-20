package ru.liga.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import ru.liga.entity.Customer;

import javax.persistence.NoResultException;
import java.util.List;

@Component
public class CustomerDAO {
    private final SessionFactory sessionFactory;

    public CustomerDAO() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public Customer getCustomerById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Customer customer;
            try {
                customer = session.find(Customer.class, id);
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

    public List<Customer> getCustomers() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Customer> customers;
            try {
                customers = session.createQuery("from Customer", Customer.class).list();
                transaction.commit();
            } catch (NoResultException e) {
                customers = null;
                transaction.rollback();
            } finally {
                session.close();
            }
            return customers;
        }
    }
}
