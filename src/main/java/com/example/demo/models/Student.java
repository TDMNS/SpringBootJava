package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String enteredURL;

    protected Student() {}

    public Student(String enteredURL) {
        this.enteredURL = enteredURL;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, enteredURL='%s']",
                id, enteredURL);
    }

    public Long getId() {
        return id;
    }

    public String getEnteredURL() {
        return enteredURL;
    }
}
