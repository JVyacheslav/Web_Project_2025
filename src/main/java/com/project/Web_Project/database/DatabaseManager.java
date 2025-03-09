package com.project.Web_Project.database;


import com.project.Web_Project.utils.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;


@Component
public class DatabaseManager {
    private SessionFactory sessionFactory = setUpSessionFactory();
    private SessionFactory setUpSessionFactory(){
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(User.class);
        StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(standardServiceRegistryBuilder.build());
    }
    public boolean saveUser(User user){
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public User selectUser(User user){
        Session session = sessionFactory.openSession();
        User realUser = session.get(User.class, user.getEmail());
        return realUser;
    }
}
