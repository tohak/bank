package com.konovalov.bank.entity;


import javax.persistence.*;

@Entity
@Table
public class Courses {

    @Id
    @GeneratedValue
    private int id;
    private String currency;
    private double by;
    private  double shell;

    public Courses() {
    }

    public Courses(String currency, double by, double shell) {
        this.currency = currency;
        this.by = by;
        this.shell = shell;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBy() {
        return by;
    }

    public void setBy(double by) {
        this.by = by;
    }

    public double getShell() {
        return shell;
    }

    public void setShell(double shell) {
        this.shell = shell;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", currency='" + currency + '\'' +
                ", by=" + by +
                ", shell=" + shell +
                '}';
    }
}
