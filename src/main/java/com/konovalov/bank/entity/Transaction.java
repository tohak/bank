package com.konovalov.bank.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Transaction {
    @Id
    @GeneratedValue
    private  Long id;

    @Column(nullable = false)
    private String from;

    @Column(nullable = false)
    private String to;

    @Column(nullable = false)
    private double count;

    @Column(nullable = false)
    private Date date;

    public Transaction() {
    }

    public Transaction(String from, String to, double count, Date date) {
        this.from = from;
        this.to = to;
        this.count = count;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", count=" + count +
                ", date=" + date +
                '}';
    }
}
