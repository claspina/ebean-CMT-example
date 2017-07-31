package com.demo.ebean.view;

import com.demo.ebean.CustomView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.wcs.vaadin.cdi.CDIView;
import com.wcs.vaadin.cdi.UIScoped;

import javax.annotation.PostConstruct;

import static net.hdavid.easylayout.L.ve;

@CDIView(PersonView.VIEWNAME)
public class PersonView extends VerticalLayout implements CustomView {
    public static final String VIEWNAME = "person";
    Label label = new Label("PersonView ");

    @PostConstruct
    public void initView() {

        ve(this, label);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }




}
