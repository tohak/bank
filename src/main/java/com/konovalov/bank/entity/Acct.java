package com.konovalov.bank.entity;


import javax.persistence.*;

@Entity

public class Acct {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "acct")
    private User user;



}
