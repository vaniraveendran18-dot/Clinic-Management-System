package controller;

import java.awt.*;
import javax.swing.*;

/**
 * Task 6 (Controller Layer): handles moving between screens. All views
 * call navigateTo(...) instead of managing their own CardLayout logic,
 * keeping navigation in one place.
 */
public class NavigationController {

    private final CardLayout cardLayout;
    private final JPanel container;

    public NavigationController(CardLayout cardLayout, JPanel container) {
        this.cardLayout = cardLayout;
        this.container = container;
    }

    public void navigateTo(String screenName) {
        cardLayout.show(container, screenName);
    }
}
