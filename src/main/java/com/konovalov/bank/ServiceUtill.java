package com.konovalov.bank;


import com.konovalov.bank.entity.Acct;
import com.konovalov.bank.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Scanner;

public class ServiceUtill {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA");
    private EntityManager em = emf.createEntityManager();
    private Scanner sc = new Scanner(System.in);

    public ServiceUtill() {
    }

    public void close() {
        emf.close();
        sc.close();
    }

    public void refill() {
        System.out.println(" REFILL:");
        System.out.println(" Input name User");
        String nameUser = sc.nextLine();
        Query query = em.createQuery("SELECT u FROM User u WHERE u.name=:n", User.class);
        query.setParameter("n", nameUser);
        User userTemp = (User) query.getSingleResult();
        if (userTemp == null) {
            addUser(nameUser);
        } else {
            updateAcct(userTemp);
        }

    }

    private void updateAcct(User user) {
        Acct acctTemp = createAcct(user);
        Acct acct = user.getAcct();

        acct.setUa(acct.getUa() + acctTemp.getUa());
        acct.setEur(acct.getEur() + acctTemp.getEur());
        acct.setUsd(acct.getUsd() + acctTemp.getUsd());
        try {
            em.getTransaction().begin();
            em.merge(acct);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            System.out.println("Error  update Acct");
        }
    }

    private Acct createAcct(User user) {
        System.out.println("Input count money 'UA'");
        double uaMoney = sc.nextDouble();
        System.out.println("Input count money 'USD'");
        double usdMoney = sc.nextDouble();
        System.out.println("Input count money'EUR'");
        double eurMoney = sc.nextDouble();
        Acct acct = new Acct(user, uaMoney, usdMoney, eurMoney);
        return acct;
    }

    private void addUser(String nameUser) {
        try {
            em.getTransaction().begin();
            User user = new User(nameUser);
            Acct acct = createAcct(user);

            em.merge(acct);
            em.getTransaction().commit();
            System.out.println("REFILL OK");
        } catch (Exception ex) {
            em.getTransaction().rollback();
            System.out.println("REFILL NO!, Error");
        }
    }


}
