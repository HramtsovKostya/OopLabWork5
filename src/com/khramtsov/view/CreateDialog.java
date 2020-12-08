package com.khramtsov.view;

import javax.swing.*;
import java.awt.*;

public class CreateDialog extends JDialog {
    private JPanel pnlContent;

    private JTextField txtName;
    private JTextField txtCapital;
    private JTextField txtArea;
    private JTextField txtPop;

    private JLabel lbMonType;
    private JLabel lbRepType;

    private JComboBox<String> cmbType;
    private JComboBox<String> cmbMonType;
    private JComboBox<String> cmbRepType;

    private JButton btnOK;
    private JButton btnCancel;

    public CreateDialog(String title) {
        initialize();

        setTitle(title);
        setBounds(830, 400, 340, 380);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setContentPane(pnlContent);
        setMinimumSize(getSize());
        getRootPane().setDefaultButton(btnOK);
        setModal(Boolean.TRUE);
    }

    private void initialize() {
        pnlContent = new JPanel(new BorderLayout());
        JPanel pnlInput = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        JLabel lbName = new JLabel("Введите название государства: ");
        JLabel lbCapital = new JLabel("Введите название столицы: ");
        JLabel lbArea = new JLabel("Введите площадь государства: ");
        JLabel lbPopulation = new JLabel("Введите численность населения: ");
        JLabel lbType = new JLabel("Выберните тип государства: ");

        lbMonType = new JLabel("Выберните тип монархии: ");
        lbRepType = new JLabel("Выберните тип республики: ");

        txtName = getTxtField();
        txtCapital = getTxtField();
        txtArea = getTxtField();
        txtPop = getTxtField();

        cmbType = new JComboBox<>(new String[] {
            "Монархия", "Республика", "Королевство" });
        cmbMonType = new JComboBox<>(new String[] {
            "Абсолютная", "Конституционная", "Дуалистическая" });
        cmbRepType = new JComboBox<>(new String[] {
            "Президентская", "Парламентская", "Смешанная" });

        Dimension size = new Dimension(220, 20);

        cmbType.setPreferredSize(size);
        cmbMonType.setPreferredSize(size);
        cmbRepType.setPreferredSize(size);

        btnOK = new JButton("ОК");
        btnCancel = new JButton("Отмена");

        pnlInput.add(lbName);
        pnlInput.add(txtName);
        pnlInput.add(lbCapital);
        pnlInput.add(txtCapital);
        pnlInput.add(lbArea);
        pnlInput.add(txtArea);
        pnlInput.add(lbPopulation);
        pnlInput.add(txtPop);
        pnlInput.add(lbType);
        pnlInput.add(cmbType);
        pnlInput.add(lbMonType);
        pnlInput.add(cmbMonType);
        pnlInput.add(lbRepType);
        pnlInput.add(cmbRepType);

        pnlButtons.add(btnOK);
        pnlButtons.add(btnCancel);

        pnlContent.add(pnlInput, BorderLayout.CENTER);
        pnlContent.add(pnlButtons, BorderLayout.SOUTH);
    }

    public JTextField getTxtName() { return txtName; }
    public JTextField getTxtCapital() { return txtCapital; }
    public JTextField getTxtArea() { return txtArea; }
    public JTextField getTxtPop() { return txtPop; }
    public JLabel getLbMonType() { return lbMonType; }
    public JLabel getLbRepType() { return lbRepType; }
    public JComboBox<String> getCmbType() { return cmbType; }
    public JComboBox<String> getCmbMonType() { return cmbMonType; }
    public JComboBox<String> getCmbRepType() { return cmbRepType; }
    public JButton getBtnOK() { return btnOK; }
    public JButton getBtnCancel() { return btnCancel; }

    private JTextField getTxtField() {
        return new JTextField("", 20);
    }
}