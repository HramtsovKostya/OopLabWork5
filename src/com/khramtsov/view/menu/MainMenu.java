package com.khramtsov.view.menu;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JMenuBar {
    private final JMenuItem miSave;
    private final JMenuItem miOpen;
    private final JMenuItem miExit;
    private final JMenuItem miCreate;
    private final JMenuItem miEdit;
    private final JMenuItem miInfo;
    private final JMenuItem miRemove;
    private final JMenuItem miClear;
    private final JMenuItem miFindNum;
    private final JMenuItem miFindName;

    public MainMenu() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 3, 3));

        JMenu mFile = new JMenu("Файл");
        JMenu mEdit = new JMenu("Редактор");
        JMenu mSearch = new JMenu("Поиск");

        miOpen = new JMenuItem("Открыть файл");
        miSave = new JMenuItem("Сохранить как...");
        miExit = new JMenuItem("Выйти из программы");
        miCreate = new JMenuItem("Создать новое государство");
        miEdit = new JMenuItem("Изменить данные о государстве");
        miInfo = new JMenuItem("Получить доп. информацию");
        miRemove = new JMenuItem("Удалить текущее государство");
        miClear = new JMenuItem("Очистить список государств");
        miFindNum = new JMenuItem("Найти по номеру");
        miFindName = new JMenuItem("Найти по названию");

        mFile.add(miOpen);
        mFile.add(miSave);
        mFile.add(getHSeparator());
        mFile.add(miExit);

        mEdit.add(miCreate);
        mEdit.add(getHSeparator());
        mEdit.add(miEdit);
        mEdit.add(miInfo);
        mEdit.add(getHSeparator());
        mEdit.add(miRemove);
        mEdit.add(miClear);

        mSearch.add(miFindNum);
        mSearch.add(miFindName);

        this.add(mFile);
        this.add(mEdit);
        this.add(mSearch);
    }

    public JMenuItem getMiOpen() { return miOpen; }
    public JMenuItem getMiSave() { return miSave; }
    public JMenuItem getMiExit() { return miExit; }
    public JMenuItem getMiCreate() { return miCreate; }
    public JMenuItem getMiEdit() { return miEdit; }
    public JMenuItem getMiInfo() { return miInfo; }
    public JMenuItem getMiRemove() { return miRemove; }
    public JMenuItem getMiClear() { return miClear; }
    public JMenuItem getMiFindNum() { return miFindNum; }
    public JMenuItem getMiFindName() { return miFindName; }

    private JSeparator getHSeparator() {
        return new JSeparator(SwingConstants.HORIZONTAL);
    }
}