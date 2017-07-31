package com.demo.ebean.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.wcs.vaadin.cdi.CDIView;
import com.wcs.vaadin.cdi.UIScoped;

@CDIView(AccessDeniedView.NAME)
public class AccessDeniedView extends VerticalLayout implements View {

    public static final String NAME = "access-denied";

    public AccessDeniedView() {
        setMargin(true);
        Label lbl = new Label("You don't have access to this view.");
        lbl.addStyleName(ValoTheme.LABEL_FAILURE);
        lbl.setSizeUndefined();
        addComponent(lbl);
    }

}

