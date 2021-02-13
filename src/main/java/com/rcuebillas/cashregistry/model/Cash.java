package com.rcuebillas.cashregistry.model;

import javax.persistence.*;

@Entity
@Table(name = "cash")
public class Cash {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int value;

    public Cash() {
    }

    public Cash(int value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Cash{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}