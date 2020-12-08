package com.khramtsov.control;

import com.khramtsov.io.*;
import com.khramtsov.model.Union;
import com.khramtsov.model.states.*;
import com.khramtsov.serialize.*;
import com.khramtsov.view.*;
import com.khramtsov.view.menu.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.*;
import java.util.Arrays;

public class MainController {
    private boolean isSaved = Boolean.TRUE;

    private MainFrame frame;
    private Union union;
    private Messenger messenger;
    private SaveOpenDialog soDialog;
    private MainMenu mainMenu;
    private MainPopMenu popupMenu;
    private JTable tblStates;

    public void execute(MainFrame frame, Union union) {
        this.frame = frame;
        this.union = union;

        messenger = new Messenger(frame);
        soDialog = new SaveOpenDialog(frame);
        mainMenu = frame.getMainMenu();
        popupMenu = frame.getPopupMenu();
        tblStates = frame.getTblStates();

        addListeners();
        frame.setVisible(Boolean.TRUE);
    }

    private void addListeners() {
        mainMenu.getMiSave().addActionListener(e -> miSave_onClick());
        mainMenu.getMiOpen().addActionListener(e -> miOpen_onClick());
        mainMenu.getMiExit().addActionListener(e -> miExit_onClick());
        mainMenu.getMiCreate().addActionListener(e -> miCreate_onClick());
        mainMenu.getMiEdit().addActionListener(e -> miEdit_onClick());
        mainMenu.getMiInfo().addActionListener(e -> miInfo_onClick());
        mainMenu.getMiRemove().addActionListener(e -> miRemove_onClick());
        mainMenu.getMiClear().addActionListener(e -> miClear_onClick());
        mainMenu.getMiFindNum().addActionListener(e -> miFindNum_onClick());
        mainMenu.getMiFindName().addActionListener(e -> miFindName_onClick());

        popupMenu.getMiCreate().addActionListener(e -> miCreate_onClick());
        popupMenu.getMiEdit().addActionListener(e -> miEdit_onClick());
        popupMenu.getMiRemove().addActionListener(e -> miRemove_onClick());

        tblStates.addFocusListener(tblStates_onFocus());
        tblStates.addMouseListener(tblStates_onMouseClick());
        frame.addWindowListener(frame_onClosing());
    }

    private void miSave_onClick() {
        if (soDialog.save() == SaveOpenDialog.APPROVE_OPTION) {
            String path = soDialog.getPath();

            switch (soDialog.getFilter()) {
                case SaveOpenDialog.JSON_FILTER:
                    JsonSerializer.serialize(union, path);
                    break;
                case SaveOpenDialog.XML_FILTER:
                    XmlSerializer.serialize(union, path);
                    break;
                case SaveOpenDialog.BINARY_FILTER:
                    BinarySerializer.serialize(union, path);
                    break;
            }

            isSaved = Boolean.TRUE; messenger.showInfo(
                "Данные успешно сохранены!", "Сохранение файла");
        }
    }

    private void miOpen_onClick() {
        if (soDialog.open() == SaveOpenDialog.APPROVE_OPTION) {
            String path = soDialog.getPath();

            switch (soDialog.getFilter()) {
                case SaveOpenDialog.JSON_FILTER:
                    union = (Union) JsonSerializer.deserialize(union, path);
                    break;
                case SaveOpenDialog.XML_FILTER:
                    union = (Union) XmlSerializer.deserialize(union, path);
                    break;
                case SaveOpenDialog.BINARY_FILTER:
                    union = (Union) BinarySerializer.deserialize(path);
                    break;
            }

            updateTable(); messenger.showInfo(
                "Данные успешно загружены!", "Открытие файла");
        }
    }

    private void miCreate_onClick() {
        createOrEditState("Создание государства", Boolean.FALSE);
    }

    private void miEdit_onClick() {
        createOrEditState("Изменение государства", Boolean.TRUE);
    }

    private void miInfo_onClick() {
        State state = getSelectedState();
        String info = "\nПлотность населения = "
            + state.getDensity() + " чел./кв. км."
            + "\nГлава государства = " + state.getHead()
            + "\nНаличие парламента = "
            + (state.hasParliament() ? "Есть" : "Нет");

        messenger.showInfo(state + info, "Доп. информация");
    }

    private void miRemove_onClick() {
        union.remove(getSelectedState()); updateTable();
        if (union.isNotEmpty()) isSaved = Boolean.FALSE;
    }

    private void miClear_onClick() { union.clear(); updateTable(); }

    private void miFindNum_onClick() {
       try {
            int number = Integer.parseInt(messenger.showInput(
                "Введите номер государства: ",
                "Поиск государства по номеру"));

            if (number > 0 && number <= union.size()) {
                messenger.showInfo(union.get(number - 1),
                    "Искомое государство");
            } else {
                messenger.showWarning(
                    "Государство с таким номером не найдено!",
                    "Предупреждение об ошибке!");
            }
        } catch (NumberFormatException e) {
           messenger.showError(
                "Вводите только положительные числа!",
                "Ошибка ввода данных!");
        }
    }

    private void miFindName_onClick() {
        String name = messenger.showInput(
            "Введите название государства: ",
            "Поиск государства по имени");
        State state = union.find(name);

        if (state != null) {
            messenger.showInfo(state, "Искомое государство");
        } else {
            messenger.showWarning(
                "Государство с таким названием не найдено!",
                "Предупреждение об ошибке!");
        }
    }

    private void miExit_onClick() {
        int option = messenger.showQuestion(
            "Вы действительно хотите выйти?",
            "Подтверждение выхода");

        if (option == Messenger.OK_OPTION) {
            if (!isSaved) {
                option = messenger.showQuestion(new String [] {
                    "Данные были изменены. ", "Сохранить последние измения?" },
                    "Подтверждение сохранения");
                if (option == Messenger.OK_OPTION) miSave_onClick();
            } frame.dispose();
        }
    }

    private FocusAdapter tblStates_onFocus() {
        return new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                setCountOfStates();
                setEnableMenuItems();
            }
        };
    }

    private MouseAdapter tblStates_onMouseClick() {
        return new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popupMenu.show(tblStates, e.getX(), e.getY());
                }
            }
        };
    }

    private WindowAdapter frame_onClosing() {
        return new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) { miExit_onClick(); }
        };
    }

    private void updateTable() {
        String [][] data = new String[union.size()][];

        for (int row = 0; row < union.size(); row++) {
            State state = union.get(row);
            String stateType = state instanceof Monarchy
                ? (state instanceof Kingdom
                ? "Королевство" : "Монархия") : "Республика";

            data[row] = new String[] {
                stateType, state.getName(), state.getCapital(),
                String.valueOf(state.getArea()),
                String.valueOf(state.getPopulation())
            };
        }
        tblStates.setModel(getTableModel(data));
        setCountOfStates();
    }

    private void setEnableMenuItems() {
        for (JMenuItem mi : Arrays.asList(
            mainMenu.getMiSave(), mainMenu.getMiEdit(),
            mainMenu.getMiRemove(), mainMenu.getMiClear(),
            mainMenu.getMiInfo(), mainMenu.getMiFindName(),
            mainMenu.getMiFindNum())) mi.setEnabled(union.isNotEmpty());
    }

    private State getSelectedState() {
        int selectedRow = tblStates.getSelectedRow();
        return selectedRow != -1 ? union.get(selectedRow) : union.get(0);
    }

    private void createOrEditState(String title, boolean isEdit) {
        CreateController controller = new CreateController();
        CreateDialog dialog = new CreateDialog(title);

        if (isEdit) fillFieldsOfDialog(dialog);
        controller.execute(dialog);
        State state = controller.getState();

        if (state != null) {
            if (isEdit) union.remove(getSelectedState());
            if (!union.contains(state)) {
                union.add(state); updateTable();
                isSaved = Boolean.FALSE;
            } else {
                messenger.showWarning(
                    "Государство с таким названием уже существует!",
                    "Предупреждение об ошибке!");
            }
        }
    }

    private void fillFieldsOfDialog(CreateDialog dialog) {
        State state = getSelectedState(); String type = "Республика";
        dialog.getTxtName().setText(state.getName());
        dialog.getTxtCapital().setText(state.getCapital());
        dialog.getTxtArea().setText(String.valueOf(state.getArea()));
        dialog.getTxtPop().setText(String.valueOf(state.getPopulation()));

        if (state instanceof Monarchy) {
            type = state instanceof Kingdom ? "Королевство" : "Монархия";
            String monType = ((Monarchy) state).getType().toString();
            dialog.getCmbMonType().getModel().setSelectedItem(monType);
        } else {
            String repType = ((Republic) state).getType().toString();
            dialog.getCmbRepType().getModel().setSelectedItem(repType);
        } dialog.getCmbType().setSelectedItem(type);
    }

    private TableModel getTableModel(String[][] data) {
        return new DefaultTableModel(data, frame.getTableHeader()) {
            @Override public boolean isCellEditable(int row, int column) {
                return Boolean.FALSE;
            }
        };
    }

    private void setCountOfStates() {
        frame.getLbCount().setText("Количество государств: " + union.size());
    }
}