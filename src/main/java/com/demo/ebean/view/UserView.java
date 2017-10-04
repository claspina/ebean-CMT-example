package com.demo.ebean.view;

import com.demo.ebean.CustomView;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.*;
import com.wcs.vaadin.cdi.CDIView;

import javax.annotation.PostConstruct;

import static net.hdavid.easylayout.L.ve;

@CDIView(UserView.VIEWNAME)
public class UserView extends VerticalLayout implements CustomView {
    public static final String VIEWNAME = "user";

    Label label = new Label("UserView ");

    @PostConstruct
    public void initView() {

        WrappedSession session = VaadinSession.getCurrent().getSession();
        label.setValue(session.toString());

        ve(this, label);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }




}
