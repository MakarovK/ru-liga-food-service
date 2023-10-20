package ru.liga.DAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import ru.liga.entity.OrderList;

import javax.persistence.NoResultException;
import java.util.List;

@Component
public class OrderListDAO {
    private final SessionFactory sessionFactory;

    public OrderListDAO() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public OrderList getOrderListById(Long id) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            OrderList orderList;
            try {
                orderList = session.find(OrderList.class, id);
                transaction.commit();
            } catch (NoResultException e) {
                orderList = null;
                transaction.rollback();
            }
            return orderList;
        }
    }

    public List<OrderList> getAllOrderList() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<OrderList> orderLists;
            try {
                orderLists = session.createQuery("from OrderList ").list();
                transaction.commit();
            } catch (NoResultException e) {
                orderLists = null;
                transaction.rollback();
            }
            return orderLists;
        }
    }

    public List<OrderList> getOrderListByCourierId(Long courier_id) {
        try (Session session = sessionFactory.openSession();) {
            Transaction transaction = session.beginTransaction();
            List<OrderList> orderLists;
            try {
                Query query = session.createQuery("from OrderList where courier.id = :courierId");
                query.setParameter("courierId", courier_id);
                orderLists = query.list();
                transaction.commit();
            } catch (NoResultException e) {
                orderLists = null;
            }
            return orderLists;
        }
    }

    public void createOrder(OrderList orderList) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(orderList);
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
            OrderList orderList;
            try {
                orderList = session.find(OrderList.class, id);
                session.delete(orderList);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new RuntimeException("Error" + e.getMessage());
            }
        }

    }
}
