package com.khramtsov;

import com.khramtsov.control.MainController;
import com.khramtsov.model.Union;
import com.khramtsov.view.MainFrame;
import javax.swing.SwingUtilities;

public class Program {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainController controller = new MainController();
            MainFrame frame = new MainFrame("Список государств");
            controller.execute(frame, new Union());
        });
    }
}