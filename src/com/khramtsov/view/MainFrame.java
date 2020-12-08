package com.khramtsov.view;

import com.khramtsov.view.menu.MainMenu;
import com.khramtsov.view.menu.MainPopMenu;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel pnlContent;
    private MainMenu mainMenu;
    private MainPopMenu popupMenu;
    private JTable tblStates;
    private JLabel lbCount;

    private String[] tableHeader;

    public MainFrame(String title) {
        initialize();

        setTitle(title);
        setBounds(700, 350, 600, 400);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setContentPane(pnlContent);
        setMinimumSize(getSize());
    }

    public MainMenu getMainMenu() { return mainMenu; }
    public MainPopMenu getPopupMenu() { return popupMenu; }
    public JTable getTblStates() { return tblStates; }
    public JLabel getLbCount() { return lbCount; }
    public String[] getTableHeader() { return tableHeader; }

    private void initialize() {
        pnlContent = new JPanel(new BorderLayout());
        mainMenu = new MainMenu();
        popupMenu = new MainPopMenu();

        createNewTable();
        lbCount = new JLabel();

        JPanel pnlFooter = new JPanel(
            new FlowLayout(FlowLayout.RIGHT, 25, 5));
        JScrollPane pnlTable = new JScrollPane(tblStates);

        pnlFooter.add(lbCount);
        pnlContent.add(mainMenu, BorderLayout.NORTH);
        pnlContent.add(pnlTable, BorderLayout.CENTER);
        pnlContent.add(pnlFooter, BorderLayout.SOUTH);
    }

    private void createNewTable() {
        tableHeader = new String[] {
            "Тип государства", "Название",
            "Столица", "Площадь, кв. км.",
            "Население, чел." };

        String[][] tableData = new String[0][];
        tblStates = new JTable(tableData, tableHeader);

        tblStates.getTableHeader()
            .setReorderingAllowed(Boolean.FALSE);
        tblStates.getTableHeader()
            .setResizingAllowed(Boolean.FALSE);
        tblStates.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION);
    }
}