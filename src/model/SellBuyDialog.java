package model;
import service.TransactionService;

import javax.swing.*;
import java.awt.*;

public class SellBuyDialog extends JDialog {

    private final boolean isSell;
    private final TransactionService service = new TransactionService();

    private JTextField tfCikkszam = new JTextField();
    private JTextField tfMennyiseg = new JTextField("1");

    public SellBuyDialog(Frame owner, boolean isSell) {
        super(owner, isSell ? "Eladás" : "Vétel", true);
        this.isSell = isSell;

        initUI();
        pack();
        setLocationRelativeTo(owner);
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(0, 2, 5, 5));

        form.add(new JLabel("Cikkszám:"));
        form.add(tfCikkszam);

        form.add(new JLabel("Mennyiség:"));
        form.add(tfMennyiseg);

        add(form, BorderLayout.CENTER);

        JButton ok = new JButton(isSell ? "Elad" : "Vesz");
        JButton cancel = new JButton("Mégse");

        JPanel buttons = new JPanel();
        buttons.add(ok);
        buttons.add(cancel);

        add(buttons, BorderLayout.SOUTH);

        ok.addActionListener(e -> submit());
        cancel.addActionListener(e -> dispose());
    }

    private void submit() {
        try {
            String cikkszam = tfCikkszam.getText().trim();
            int menny = Integer.parseInt(tfMennyiseg.getText().trim());

            if (isSell) service.sell(cikkszam, menny);
            else        service.buy(cikkszam, menny);

            JOptionPane.showMessageDialog(this,
                    (isSell ? "Eladás sikeres!" : "Vétel sikeres!")
            );
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Hiba: " + ex.getMessage(),
                    "Hiba",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

public class SellBuyDialog {
}
