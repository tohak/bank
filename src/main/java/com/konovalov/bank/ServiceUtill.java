package com.konovalov.bank;


import com.konovalov.bank.entity.Acct;
import com.konovalov.bank.entity.Courses;
import com.konovalov.bank.entity.Transaction;
import com.konovalov.bank.entity.User;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ServiceUtill {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA");
    private EntityManager em = emf.createEntityManager();
    private Scanner sc = new Scanner(System.in);
    private Scanner scTho = new Scanner(System.in);

    public ServiceUtill() {
    }

    public void close() {
        emf.close();
        sc.close();
        scTho.close();
    }

    public void refill() {
        System.out.println(" REFILL:");
        System.out.println(" Input name User");
        String nameUser = scTho.nextLine();
        try {
            Query query = em.createQuery("SELECT u FROM User u WHERE u.name=:n", User.class);
            query.setParameter("n", nameUser);
            User userTemp = (User) query.getSingleResult();
            updateAcct(userTemp);
        } catch (NoResultException ex) {
            addUser(nameUser);
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
        if (uaMoney < 0) {
            uaMoney = 0;
            System.out.println("you entered a negative number. the number became zero");
        }
        System.out.println("Input count money 'USD'");
        double usdMoney = sc.nextDouble();
        if (usdMoney < 0) {
            usdMoney = 0;
            System.out.println("you entered a negative number. the number became zero");
        }
        System.out.println("Input count money'EUR'");
        double eurMoney = sc.nextDouble();
        if (eurMoney < 0) {
            eurMoney = 0;
            System.out.println("you entered a negative number. the number became zero");
        }
        Acct acct = new Acct(user, uaMoney, usdMoney, eurMoney);
        return acct;
    }

    private void addUser(String nameUser) {
        try {
            em.getTransaction().begin();
            User user = new User(nameUser);
            Acct acct = createAcct(user);
            user.setAcct(acct);
            em.merge(acct);
            em.getTransaction().commit();
            System.out.println("REFILL OK");
        } catch (Exception ex) {
            em.getTransaction().rollback();
            System.out.println("REFILL NO!, Error");
            System.out.println(ex.getMessage());
        }
    }

    public void testTransaction() {
        String from = "test";
        String to = "test1";
        double count = 10;
        Date date= new Date();
        Transaction transaction = new Transaction(from, to, count,date);
        em.getTransaction().begin();
        try {
            em.persist(transaction);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            System.out.println("Error transaction");
            System.out.println(ex.getMessage());
        }

    }
    public void transactionFrom() {
        try {
            em.getTransaction().begin();
            Transaction transaction = createTransaction();
            User userFrom = checkUser(transaction.getFrom());
            User userTo = checkUser(transaction.getTo());
            if (userFrom != null || userTo != null) {
                if (userFrom.getAcct().getUa() - transaction.getCount() >= 0) {
                    userFrom.getAcct().setUa(userFrom.getAcct().getUa() - transaction.getCount());
                    userTo.getAcct().setUa(userTo.getAcct().getUa() + transaction.getCount());
                    em.merge(userFrom);
                    em.merge(userTo);
                    em.persist(transaction);
                    System.out.println("Transacton OK");
                } else {
                    System.out.println("Transaction NO! Error, no count many");
                }
            }
            em.getTransaction().commit();

        } catch (Exception ex) {
            em.getTransaction().rollback();
            System.out.println("Transaction No!, Error");
            ex.printStackTrace();
        }
    }

    private User checkUser(String userName) {
        User user;
        try {
            Query query = em.createQuery("SELECT u FROM User u WHERE u.name=:n", User.class);
            query.setParameter("n", userName);
            user = (User) query.getSingleResult();
            System.out.println("checkUser OK");
        } catch (NoResultException ex) {
            System.out.println("Error, is not user");
            user = null;
        }

        return user;
    }

    private Transaction createTransaction() {
        System.out.println("Input from name ");
        String from = scTho.nextLine();
        System.out.println("Input to name");
        String to = scTho.nextLine();
        System.out.println("Input count ua");
        Double count = sc.nextDouble();
        Date date= new Date();
        Transaction transaction = new Transaction(from, to, count, date);
        return transaction;
    }
    public  void addCource(String nameCurrency, double by, double shell){
        Courses courses=new Courses(nameCurrency,by,shell);
        try{
            em.getTransaction().begin();
            em.persist(courses);
            em.getTransaction().commit();
            System.out.println("AddCoirce YES");
        }catch (Exception e){
            em.getTransaction().rollback();
            System.out.println("Error, is not addCource");
        }
    }
    public void courceFromTo(){
        User user=getUser();
        if (checkFromTo()){
            Courses to=getCource();
        }else{
            Courses from=getCource();
        }


       

    }
    private boolean checkFromTo(){
        int check= 0;
        System.out.println("Input '1' if buy currency, '2' if shell ");
        check=sc.nextInt();
        if (check < 2) {
            return true;
        }else return false;
    }
    private User getUser(){
        System.out.println("Input name user");
        String nameUser= sc.nextLine();
        User user=checkUser(nameUser);
        return user;
    }
    private Courses getCource(){
        System.out.println("Input  cource ");
        String from=scTho.nextLine();
        Courses courses;
        try{
            Query query= em.createQuery("SELECT c FROM Courses c WHERE c.currency=:n", Courses.class);
            query.setParameter("n",from);
             courses=(Courses) query.getSingleResult();

        } catch (NoResultException ex) {
            System.out.println("Error, is not cources");
             courses= null;
        }

        return courses;
    }



}
