package com.khramtsov.io;

import javax.swing.*;

public class Messenger extends JOptionPane {
    private final JFrame frame;

    public Messenger(JFrame frame) {
        this.frame = frame;
        translateUI();
    }

    public void showInfo(Object message, String title) {
        showMessageDialog(frame, message, title, INFORMATION_MESSAGE);
    }

    public void showError(Object message, String title) {
        showMessageDialog(frame, message, title, ERROR_MESSAGE);
    }

    public void showWarning(Object message, String title) {
        showMessageDialog(frame, message, title, WARNING_MESSAGE);
    }

    public String showInput(Object message, String title) {
        return showInputDialog(frame, message, title, PLAIN_MESSAGE);
    }

    public int showQuestion(Object message, String title) {
        return showConfirmDialog(frame, message,
            title, YES_NO_OPTION, QUESTION_MESSAGE);
    }

    private void translateUI() {
        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");
        UIManager.put("OptionPane.okButtonText", "ОК");
        UIManager.put("OptionPane.cancelButtonText", "Отмена");
        updateUI();
    }
}