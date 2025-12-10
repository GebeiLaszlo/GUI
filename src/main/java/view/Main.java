package view;

import gui.DashBoardPanel;
import javax.swing.SwingUtilities;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();

            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            mainFrame.pack();
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setVisible(true);
        });

    }


}
