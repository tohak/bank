package com.konovalov.bank.entity;


import javax.persistence.*;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Column (nullable = false)
    private String name;

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name="acct_id")
    private Acct acct;

    public User() {
    }
}
