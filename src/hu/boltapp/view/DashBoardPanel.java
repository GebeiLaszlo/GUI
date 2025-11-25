package hu.boltapp.view;

import hu.boltapp.model.StoreStats;
import hu.boltapp.service.ApiClient;

import javax.swing.*;
import java.awt.*;

public class DashBoardPanel {

    public void showDialog(Frame owner) {
        JDialog dlg = new JDialog(owner, "Bolt statisztikák", true);
        dlg.setSize(400, 300);
        dlg.setLocationRelativeTo(owner);
        dlg.setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));

        JLabel lblProfit = new JLabel();
        JLabel lblKiadas = new JLabel();
        JLabel lblHely = new JLabel();

        panel.add(lblProfit);
        panel.add(lblKiadas);
        panel.add(lblHely);

        dlg.add(panel, BorderLayout.CENTER);

        try {
            StoreStats stats = ApiClient.get("/stats", StoreStats.class);
            lblProfit.setText("Profit: " + stats.getProfit() + " Ft");
            lblKiadas.setText("Kiadás: " + stats.getKiadas() + " Ft");
            lblHely.setText("Szabad hely: " + stats.getSzabadHely() + " db");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(dlg,
                    "Nem sikerült betölteni a statisztikákat:\n" + e.getMessage());
        }

        JButton ok = new JButton("OK");
        ok.addActionListener(e -> dlg.dispose());

        dlg.add(ok, BorderLayout.SOUTH);

        dlg.setVisible(true);
    }
}
