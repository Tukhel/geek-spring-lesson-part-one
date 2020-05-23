package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();
        User user1 = new User("alex");
        User user2 = new User("ivan");
        User user3 = new User("petr");

        em.getTransaction().begin();
        em.persist(user1);
        em.persist(user2);
        em.persist(user3);
        em.getTransaction().commit();

        User user4 = em.find(User.class, 1L);
        System.out.println(user4);

        em.getTransaction().begin();
        em.remove(user4);
        em.getTransaction().commit();

        List<User> users = em.createQuery("from User u where u.name = :name", User.class)
                .setParameter("name", "ivan")
                .getResultList();
        users.forEach(System.out::println);

        User user5 = users.get(0);

        Product product1 = new Product("T-shirt", new BigDecimal(20.0));
        Product product2 = new Product("shorts", new BigDecimal(39.99));


        em.getTransaction().begin();
        em.persist(product1);
        em.persist(product2);
        em.getTransaction().commit();

        em.refresh(user5);

        System.out.println(user5);

        em.close();
    }
}
