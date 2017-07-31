package com.demo.ebean.entity;

import com.demo.ebean.entity.query.QPerson;
import com.demo.ebean.entity.query.QUser;
import io.ebean.Ebean;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter @Setter
@Entity
@Table(name = "suser")
public class User extends BasicEbeanEntity {

    private String username;
    private String password;

    public static QUser query() { return new QUser(Ebean.getDefaultServer()); }
}
