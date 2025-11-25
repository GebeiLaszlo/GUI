package hu.boltapp.view;

import hu.boltapp.config.AppConfig;

import javax.swing.*;
import java.awt.*;

public class SettingsDialog extends JDialog {

    private JTextField tfHost = new JTextField();
    private JTextField tfPort = new JTextField();

    public SettingsDialog(Frame owner) {
        super(owner, "Beállítások", true);
        setSize(300, 180);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout(10, 10));

        init();
    }

    private void init() {
        JPanel form = new JPanel(new GridLayout(0, 2, 5, 5));

        form.add(new JLabel("Host:"));
        form.add(tfHost);

        form.add(new JLabel("Port:"));
        form.add(tfPort);

        tfHost.setText("localhost");
        tfPort.setText("8080");

        add(form, BorderLayout.CENTER);

        JButton save = new JButton("Mentés");
        save.addActionListener(e -> saveSettings());

        add(save, BorderLayout.SOUTH);
    }

    private void saveSettings() {
        try {
            String host = tfHost.getText().trim();
            int port = Integer.parseInt(tfPort.getText().trim());

            AppConfig.setHost(host);
            AppConfig.setPort(port);

            JOptionPane.showMessageDialog(this,
                    "Beállítások mentve!\nÚj backend: " + host + ":" + port);

            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hibás adat!");
        }
    }
}
