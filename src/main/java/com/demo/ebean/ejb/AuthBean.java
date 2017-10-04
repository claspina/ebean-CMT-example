package com.demo.ebean.ejb;

import com.demo.ebean.entity.User;

import javax.ejb.Stateless;

@Stateless
public class AuthBean {

    public User authenticate(String username, String pass) {
        User user = User.query().username.eq(username).password.eq(pass).findUnique();

        return user;
    }
}
