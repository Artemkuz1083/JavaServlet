package com.example.servlet;


import com.example.servlet.DataBase.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Requests_sql {


    public static void addUser(User user){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
        }
    }

    public static User getUser(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(User.class, username);
        }
    }

    public static boolean validateUser(String username, String password){
        User user = getUser(username);
        return user != null && user.getPassword().equals(password);
    }

}
