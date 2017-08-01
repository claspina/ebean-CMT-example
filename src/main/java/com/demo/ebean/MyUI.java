package com.demo.ebean;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.demo.ebean.view.LoginView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.*;
import com.vaadin.ui.UI;
import com.wcs.vaadin.cdi.CDIUI;
import com.wcs.vaadin.cdi.server.VaadinCDIServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@CDIUI("")
@Viewport("user-scalable=no,initial-scale=1.0")
@Title("Titulo app")
public class MyUI extends UI {

    @Inject
    MyControlAccess cu;
    @Inject
    LoginView loginView;
    @Inject
    Instance<MainView> mainViewInstance;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        if (!cu.isUserSignedIn()) {
            setContent(loginView);
        } else {
//            mainView.init();
            MainView mainView = mainViewInstance.get();
            setContent(mainView);
            UI.getCurrent().addDetachListener(dl -> {
                mainViewInstance.destroy(mainView);
            });
        }

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
//    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinCDIServlet {
//        @Override
//        protected void servletInitialized() throws ServletException {
//            super.servletInitialized();
//            getService().setSystemMessagesProvider((SystemMessagesProvider) systemMessagesInfo -> {
//                CustomizedSystemMessages messages = new CustomizedSystemMessages();
//                // Don't show any messages, redirect immediately to the session expired URL
//                messages.setSessionExpiredNotificationEnabled(false);
//                // Don't show any message, reload the page instead
//                messages.setCommunicationErrorNotificationEnabled(true);
//                return messages;
//            });
//        }
    }
}
