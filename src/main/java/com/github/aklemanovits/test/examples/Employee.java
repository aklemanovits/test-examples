package com.github.aklemanovits.test.examples;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author aklemanovits on 2018. 04. 01.
 */
@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true, nullable = false)
    private String name;

    public Employee(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Employee(String name) {
        this(null, name);
    }

    public Employee() {
        this(null, null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
