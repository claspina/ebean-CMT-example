package com.demo.ebean.view;

import com.demo.ebean.CustomView;
import com.demo.ebean.MyControlAccess;
import com.demo.ebean.ejb.AuthBean;
import com.demo.ebean.entity.User;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import com.wcs.vaadin.cdi.CDIView;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import static net.hdavid.easylayout.L.*;

//@CDIView
public class LoginView extends VerticalLayout implements CustomView {

    @Inject
    MyControlAccess controlAccess;
    @Inject
    AuthBean authBean;

    Label label = new Label("");
    TextField username = new TextField("Username", "");
    PasswordField password = new PasswordField("Username", "");

    Button login = new Button("LoginView");

    @PostConstruct
    public void initView() {

        login.addClickListener(cl -> {
            User cuser = authBean.authenticate(username.getValue(), password.getValue());
            if(cuser== null ) {
                username.setValue("");
                password.setValue("");
                Notification.show("Credenciales invalidas", Notification.Type.WARNING_MESSAGE);
                username.focus();
            } else {
                controlAccess.setCurrentUser(cuser);
                Page.getCurrent().reload();
            }
        });

        ve(this, label, username, password, login);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }




}
