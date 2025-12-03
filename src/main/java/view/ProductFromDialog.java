package view;

import model.Product;
import service.ProductService;

import javax.swing.*;
import java.awt.*;

public class ProductFromDialog extends JDialog {

    private final ProductService service = new ProductService();
    private final Product editing; // null = új termék, != null = szerkesztés

    private JTextField tfCikkszam = new JTextField();
    private JTextField tfNev = new JTextField();
    private JTextField tfMennyiseg = new JTextField();
    private JTextField tfAr = new JTextField();

    public ProductFromDialog(Frame owner, Product editing) {
        super(owner, editing == null ? "Új termék" : "Termék szerkesztése", true);
        this.editing = editing;

        setSize(400, 250);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout(10, 10));

        initForm();
        fillFormIfEditing();
    }

    private void initForm() {
        JPanel form = new JPanel(new GridLayout(0, 2, 5, 5));

        form.add(new JLabel("Cikkszám:"));
        form.add(tfCikkszam);

        form.add(new JLabel("Név:"));
        form.add(tfNev);

        form.add(new JLabel("Mennyiség:"));
        form.add(tfMennyiseg);

        form.add(new JLabel("Ár:"));
        form.add(tfAr);

        add(form, BorderLayout.CENTER);

        JButton ok = new JButton("Mentés");
        ok.addActionListener(e -> save());

        add(ok, BorderLayout.SOUTH);
    }

    private void fillFormIfEditing() {
        if (editing != null) {
            tfCikkszam.setText(editing.getCikkszam());
            tfNev.setText(editing.getNev());
            tfMennyiseg.setText(String.valueOf(editing.getMennyiseg()));
            tfAr.setText(String.valueOf(editing.getAr()));
        }
    }

    private void save() {
        try {
            Product p = new Product();
            p.setCikkszam(tfCikkszam.getText().trim());
            p.setNev(tfNev.getText().trim());
            p.setMennyiseg(Integer.parseInt(tfMennyiseg.getText().trim()));
            p.setAr(Double.parseDouble(tfAr.getText().trim()));

            if (editing == null) {
                service.add(p);
                JOptionPane.showMessageDialog(this, "Új termék felvéve!");
            } else {
                p.setId(editing.getId());
                service.update(p);
                JOptionPane.showMessageDialog(this, "Termék frissítve!");
            }

            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Hibás adat vagy backend hiba:\n" + e.getMessage());
        }
    }
}
