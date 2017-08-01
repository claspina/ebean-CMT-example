package com.demo.ebean;

import com.demo.ebean.view.PersonView;
import com.demo.ebean.view.UserView;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.wcs.vaadin.cdi.CDINavigator;
import com.wcs.vaadin.cdi.UIScoped;
import org.vaadin.teemusa.sidemenu.SideMenu;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.lang.reflect.Field;


@UIScoped
public class MainView extends SideMenu {

    @Inject
    CDINavigator navigator;

    @Inject
    MyControlAccess cu;

    @PostConstruct
    public void init() {
        navigator.init(UI.getCurrent(), this);

        setMenuCaption("My App caption", VaadinIcons.AIRPLANE);
        setCaption("another caption?");

        setUserIcon(VaadinIcons.MALE);
        setUserName("Guest");
        setUserMenuVisible(true);
        addUserMenuItem("Show/Hide user menu", VaadinIcons.USER, () -> setUserMenuVisible(!isUserMenuVisible()) );
        addUserMenuItem("Settings", VaadinIcons.WRENCH, () -> {
            Notification.show("Showing settings", Notification.Type.TRAY_NOTIFICATION);
        });
        addUserMenuItem("Sign out", () -> {
            Notification.show("Logging out..", Notification.Type.TRAY_NOTIFICATION);
        });

        UI.getCurrent().setNavigator(navigator);

        setMenuCaption("MyUI Menu");
        addMenuItem("My Menu Entry", () -> {
            Notification.show("Here is my custom action for this menu item.");
        });


        addNavigation("Users", UserView.VIEWNAME);
        addDivision("Division");
        addNavigation("PersonView", PersonView.VIEWNAME);
        setResponsive(true);

        Notification.show("CurrentUser is: "+ cu.isUserSignedIn());

        navigator.navigateTo(UserView.VIEWNAME);
    }

    private void addDivision(String name) {
        try {
            Field fieldMenuItemLayout = getClass().getDeclaredField("menuItemsLayout");
            fieldMenuItemLayout.setAccessible(true);
            CssLayout menuItemLayout = (CssLayout) fieldMenuItemLayout.get(this);
            menuItemLayout.addComponent(new Label("&nbsp; &nbsp;"+ FontAwesome.BARS.getHtml()+"&nbsp; "+name, ContentMode.HTML));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void logout() {
        VaadinSession.getCurrent().close();
        Page.getCurrent().reload();
        getSession().close();
    }
}
