package com.konovalov.bank;


public class App {
    private static ServiceUtill utill = new ServiceUtill();

    public static void main(String[] args) {
//add user or update balance
        utill.refill();
        utill.refill();
        //tranzaction
        utill.transactionFrom();
//add cource usd and eur;
        utill.addCource("usd", 26.1, 26);
        utill.addCource("eur", 30.1, 30);
        // by cource or shelll
        utill.courceFromTo();

        //max money in ua user
        System.out.println("max money in ua= " + utill.maxMoneyUa());


        utill.close();
        System.out.println("and");

    }
}
