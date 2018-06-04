package com.konovalov.bank;


public class App {
   private  static ServiceUtill utill = new ServiceUtill();
    public static void main(String[] args) {

//        utill.refill();
//        utill.refill();
//        utill.transactionFrom();

        utill.addCource("usd", 26.1, 26);
        utill.addCource("eur", 30.1, 30);




        utill.close();
        System.out.println("and");

    }
}
