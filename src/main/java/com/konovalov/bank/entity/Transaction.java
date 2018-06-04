package com.konovalov.bank.entity;


import javax.persistence.*;



@Entity
@Table
public class Transaction {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = true)
    private String from;
    @Column(nullable = true)
    private String to;
    @Column(nullable = true)
    private int count;

    public Transaction() {
    }

    public Transaction(String from, String to, int count) {
        this.from = from;
        this.to = to;
        this.count = count;
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
                '}';
    }
}
