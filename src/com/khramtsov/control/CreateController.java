package com.khramtsov.control;

import com.khramtsov.model.enums.MonarchyType;
import com.khramtsov.model.enums.RepublicType;
import com.khramtsov.model.states.*;
import com.khramtsov.view.CreateDialog;

import javax.swing.*;
import java.awt.event.*;

public class CreateController {
    private CreateDialog dialog;
    private State state;
    private JTextField txtName;
    private JTextField txtCapital;
    private JTextField txtArea;
    private JTextField txtPop;
    private JComboBox<String> cmbMonType;
    private JComboBox<String> cmbRepType;

    public void execute(CreateDialog dialog) {
        this.dialog = dialog;

        txtName = dialog.getTxtName();
        txtCapital = dialog.getTxtCapital();
        txtArea = dialog.getTxtArea();
        txtPop = dialog.getTxtPop();
        cmbMonType = dialog.getCmbMonType();
        cmbRepType = dialog.getCmbRepType();

        addListeners();
        dialog.setVisible(Boolean.TRUE);
    }

    private void addListeners() {
        dialog.getBtnOK().addActionListener(e -> btnOK_onClick());
        dialog.getBtnCancel().addActionListener(e -> btnCancel_onClick());

        dialog.getTxtName().addKeyListener(txtFields_onInput());
        dialog.getTxtCapital().addKeyListener(txtFields_onInput());
        dialog.getTxtArea().addKeyListener(intFields_onInput());
        dialog.getTxtPop().addKeyListener(intFields_onInput());
        dialog.getCmbType().addItemListener(e -> cmbType_onSelect());
        dialog.getCmbMonType().addItemListener(e -> cmBoxes_onSelect());
        dialog.getCmbRepType().addItemListener(e -> cmBoxes_onSelect());

        dialog.addWindowListener(dialog_onClosing());
        dialog.addWindowListener(dialog_onOpening());
    }

    public State getState() { return state; }

    private void btnOK_onClick() {
        String name = txtName.getText();
        String capital = txtCapital.getText();
        int area = Integer.parseInt(txtArea.getText());
        int pop = Integer.parseInt(txtPop.getText());
        int type = dialog.getCmbType().getSelectedIndex();

        int index = type == 1 ? cmbRepType.getSelectedIndex()
            : cmbMonType.getSelectedIndex();

        createState(name, capital, area, pop, type, index);
        btnCancel_onClick();
    }

    private void btnCancel_onClick() { dialog.dispose(); }

    private KeyAdapter txtFields_onInput() {
        return new KeyAdapter() {
            @Override public void keyTyped(KeyEvent e) {
                String text = ((JTextField) e.getSource()).getText();

                int index = text.length() - 1;
                boolean isSplitter = e.getKeyChar() == '-' || e.getKeyChar() == ' ';

                if (text.length() >= 30 || isSplitter && (text.length() == 0
                    || text.charAt(index) == '-' || text.charAt(index) == ' ')
                    || text.length() == 0 && Character.isLowerCase(e.getKeyChar())
                    || (e.getKeyChar() < 'А' || e.getKeyChar() > 'я')
                    && e.getKeyChar() != 'Ё' && e.getKeyChar() != 'ё' && !isSplitter)
                e.consume();

                setEnableBtnOK();
            }
        };
    }

    private KeyAdapter intFields_onInput() {
        return new KeyAdapter() {
            @Override public void keyTyped(KeyEvent e) {
                String text = ((JTextField) e.getSource()).getText();

                if (text.length() >= 9 || text.length() == 0 && e.getKeyChar() == '0'
                    || !Character.isDigit(e.getKeyChar())) e.consume();

                setEnableBtnOK();
            }
        };
    }

    private void cmbType_onSelect() {
        boolean isRepublic = dialog.getCmbType().getSelectedIndex() == 1;

        cmbRepType.setVisible(isRepublic);
        cmbMonType.setVisible(!isRepublic);
        dialog.getLbRepType().setVisible(isRepublic);
        dialog.getLbMonType().setVisible(!isRepublic);

        setEnableBtnOK();
    }

    private void cmBoxes_onSelect() { setEnableBtnOK(); }

    private WindowAdapter dialog_onOpening() {
        return new WindowAdapter() {
            @Override public void windowOpened(WindowEvent e) {
                cmbType_onSelect();
                dialog.getBtnOK().setEnabled(Boolean.FALSE);
            }
        };
    }

    private WindowAdapter dialog_onClosing() {
        return new WindowAdapter() {
            @Override public void windowClosing(WindowEvent windowEvent) {
                 btnCancel_onClick();
            }
        };
    }

    private void setEnableBtnOK() {
        dialog.getBtnOK().setEnabled(!txtName.getText().isEmpty()
            && !txtCapital.getText().isEmpty()
            && !txtArea.getText().isEmpty()
            && !txtPop.getText().isEmpty());
    }

    private void createState(String name, String capital,
        int area, int pop, int type, int index) {

        MonarchyType monType = MonarchyType.convert(index + 1);
        RepublicType repType = RepublicType.convert(index + 1);

        switch (type) {
            case 0: state = new Monarchy(name, capital, area, pop, monType); break;
            case 1: state = new Republic(name, capital, area, pop, repType); break;
            case 2: state = new Kingdom(name, capital, area, pop, monType); break;
        }
    }
}