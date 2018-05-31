package com.konovalov.bank.entity;


import javax.persistence.*;

@Entity
@Table
@NamedQuery(name="Acct.find", query = "SELECT a FROM Acct  a WHERE User.id=:uid")
public class Acct {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "acct", cascade = CascadeType.ALL)
    private User user;

    @Column
    private double ua;

    @Column
    private double usd;

    @Column
    private double eur;

    public Acct() {
    }

    public Acct(User user) {
        this.user = user;
    }

    public Acct( double ua, double usd, double eur) {
        this.ua = ua;
        this.usd = usd;
        this.eur = eur;
    }

    public Acct(User user, double ua, double usd, double eur) {
        this.user = user;
        this.ua = ua;
        this.usd = usd;
        this.eur = eur;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getUa() {
        return ua;
    }

    public void setUa(double ua) {
        this.ua = ua;
    }

    public double getUsd() {
        return usd;
    }

    public void setUsd(double usd) {
        this.usd = usd;
    }

    public double getEur() {
        return eur;
    }

    public void setEur(double eur) {
        this.eur = eur;
    }

    @Override
    public String toString() {
        return "Acct{" +
                "id=" + id +
                ", user=" + user +
                ", ua=" + ua +
                ", usd=" + usd +
                ", eur=" + eur +
                '}';
    }
}
