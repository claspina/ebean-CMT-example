package com.demo.ebean;

import com.demo.ebean.entity.User;
import com.vaadin.server.VaadinService;
import com.wcs.vaadin.cdi.VaadinSessionScoped;
import com.wcs.vaadin.cdi.access.AccessControl;

import javax.enterprise.inject.Alternative;

@VaadinSessionScoped
@Alternative
public class MyControlAccess extends AccessControl {

    private volatile User currentUser;

    public void setCurrentUser(User user) {
        if(currentUser != null)
            throw new RuntimeException("UserView already logged in!");
        currentUser = user;
        VaadinService.reinitializeSession(VaadinService.getCurrentRequest());
    }

    @Override
    public boolean isUserSignedIn() {
        return currentUser != null;
    }

    @Override
    public boolean isUserInRole(String role) {
        return role.equals("admin");
    }

    @Override
    public String getPrincipalName() {
        return currentUser.getUsername();
    }
}
