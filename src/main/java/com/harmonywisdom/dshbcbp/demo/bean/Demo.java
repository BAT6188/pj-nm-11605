package com.harmonywisdom.dshbcbp.demo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by hotleave on 14-9-16.
 */
@Entity
@Table(name = "T_DEMO")
public class Demo implements Serializable {
    @Id
    @Column(length = 64)
    private String id;

    @Column(unique = true, nullable = false, length = 128)
    private String name;

    @Column
    private Integer age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
