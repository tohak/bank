package com.konovalov.bank.entity;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table
public class Transaction {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "from_user")
    private String from;
    @Column(name = "to_user")
    private String to;
    private double count;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Transaction() {
    }

    public Transaction(String from, String to, double count, Date date) {
        this.from = from;
        this.to = to;
        this.count = count;
        this.date=date;

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public void setCount(int count) {
        this.count = count;
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
