package com.khramtsov.view.menu;

import javax.swing.*;

public class MainPopMenu extends JPopupMenu {
    private final JMenuItem miCreate;
    private final JMenuItem miEdit;
    private final JMenuItem miRemove;

    public MainPopMenu() {
        miCreate = new JMenuItem("Создать");
        miEdit = new JMenuItem("Изменить");
        miRemove = new JMenuItem("Удалить");

        this.add(miCreate);
        this.add(miEdit);
        this.add(miRemove);
    }

    public JMenuItem getMiCreate() { return miCreate; }
    public JMenuItem getMiEdit() { return miEdit; }
    public JMenuItem getMiRemove() { return miRemove; }
}