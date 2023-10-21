package ru.liga.DAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import ru.liga.entity.Order;

import javax.persistence.NoResultException;
import java.util.List;

@Component
public class OrderDAO {
    private final SessionFactory sessionFactory;

    public OrderDAO() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public Order getOrderListById(Long id) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Order order;
            try {
                order = session.find(Order.class, id);
                transaction.commit();
            } catch (NoResultException e) {
                order = null;
                transaction.rollback();
            }
            return order;
        }
    }

    public List<Order> getAllOrderList() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Order> orders;
            try {
                orders = session.createQuery("from Order ").list();
                transaction.commit();
            } catch (NoResultException e) {
                orders = null;
                transaction.rollback();
            }
            return orders;
        }
    }

    public List<Order> getOrderByCourierId(Long courier_id) {
        try (Session session = sessionFactory.openSession();) {
            Transaction transaction = session.beginTransaction();
            List<Order> orders;
            try {
                Query query = session.createQuery("from Order where courier.id = :courierId");
                query.setParameter("courierId", courier_id);
                orders = query.list();
                transaction.commit();
            } catch (NoResultException e) {
                orders = null;
            }
            return orders;
        }
    }

    public void createOrder(Order order) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(order);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new RuntimeException("Error" + e.getMessage());
            }
        }
    }

    public void deleteOrderById(Long id) {


        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Order order;
            try {
                order = session.find(Order.class, id);
                session.delete(order);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new RuntimeException("Error" + e.getMessage());
            }
        }

    }
}
