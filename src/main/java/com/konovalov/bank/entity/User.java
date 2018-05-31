package com.konovalov.bank.entity;


import javax.persistence.*;

@Entity
@Table
//@NamedQuery(name="User.find", query = "SELECT u FROM User u WHERE u.name=:n")
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "acct_id")
    private Acct acct;

    public User() {
    }

    public User(String name) {
        this.name = name;

    }

    public User(String name, Acct acct) {
        this.name = name;
        this.acct = acct;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Acct getAcct() {
        return acct;
    }

    public void setAcct(Acct acct) {
        this.acct = acct;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", acct=" + acct +
                '}';
    }
}
