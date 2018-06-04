package com.konovalov.bank;


public class App {
   private  static ServiceUtill utill = new ServiceUtill();
    public static void main(String[] args) {

      //  utill.refill();
      //  utill.refill();
        utill.testTransaction();
      //  utill.transactionFrom();
        utill.close();
        System.out.println("and");

    }
}
