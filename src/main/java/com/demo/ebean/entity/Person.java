package com.demo.ebean.entity;

import com.demo.ebean.entity.query.QPerson;
import io.ebean.Ebean;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter @Setter
@Entity
public class Person extends BasicEbeanEntity {

    private String name;
    private String lastName;
    private Integer age;

    public static QPerson query() { return new QPerson(Ebean.getDefaultServer()); }
}
