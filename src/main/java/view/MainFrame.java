package view;

import gui.DashBoardPanel;
import model.Product;
import model.SellBuyDialog;
import service.ProductService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainFrame extends JFrame {

    private final ProductService productService = new ProductService();

    private JTable table;
    private DefaultTableModel model;

    private JTextField tfSearch = new JTextField();

    public MainFrame() {
        setTitle("Raktár Kezelő");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initUI();
        loadProducts();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));

        JToolBar tb = new JToolBar();
        tb.setFloatable(false);

        JButton btnNew  = new JButton("Új termék");
        JButton btnEdit = new JButton("Szerkesztés");
        JButton btnDelete = new JButton("Törlés");
        JButton btnRefresh = new JButton("Frissítés");
        JButton btnSell = new JButton("Eladás");
        JButton btnBuy  = new JButton("Vétel");
        JButton btnSettings = new JButton("Beállítások");
        JButton btnDashboard = new JButton("Dashboard");

        tb.add(btnNew); tb.add(btnEdit); tb.add(btnDelete);
        tb.add(btnRefresh);
        tb.addSeparator();
        tb.add(btnSell); tb.add(btnBuy);
        tb.addSeparator();
        tb.add(btnDashboard);
        tb.add(btnSettings);

        add(tb, BorderLayout.NORTH);

        String[] header = {"ID", "Cikkszám", "Név", "Mennyiség", "Ár (Ft)"};
        model = new DefaultTableModel(header, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout(5, 5));
        bottom.add(new JLabel("Keresés (cikkszám): "), BorderLayout.WEST);
        bottom.add(tfSearch, BorderLayout.CENTER);

        JButton btnSearch = new JButton("Keresés");
        bottom.add(btnSearch, BorderLayout.EAST);

        add(bottom, BorderLayout.SOUTH);

        btnNew.addActionListener(e -> {
            new ProductFromDialog(this, null).setVisible(true);
            loadProducts();
        });

        btnEdit.addActionListener(e -> {
            Product p = getSelectedProduct();
            if (p == null) return;

            new ProductFromDialog(this, p).setVisible(true);
            loadProducts();
        });

        btnDelete.addActionListener(e -> deleteSelectedProduct());
        btnRefresh.addActionListener(e -> loadProducts());

        btnSell.addActionListener(e -> {
            Product p = getSelectedProduct();
            if (p == null) return;

            new SellBuyDialog(this, true).setVisible(true);
            loadProducts();
        });

        btnBuy.addActionListener(e -> {
            Product p = getSelectedProduct();
            if (p == null) return;

            new SellBuyDialog(this, false).setVisible(true);
            loadProducts();
        });

        btnSearch.addActionListener(e -> searchByCikkszam());

        btnSettings.addActionListener(e -> new SettingsDialog(this).setVisible(true));
        btnDashboard.addActionListener(e -> new DashBoardPanel().showDialog(this));
    }
    private void loadProducts() {
        try {
            model.setRowCount(0);

            Product[] products = productService.getAll();

            for (Product p : products) {
                model.addRow(new Object[]{
                        p.getId(),
                        p.getCikkszam(),
                        p.getNev(),
                        p.getMennyiseg(),
                        p.getAr()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Hiba a termékek betöltésekor:\n" + e.getMessage(),
                    "Hiba",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private Product getSelectedProduct() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Nincs termék kijelölve.");
            return null;
        }

        Product p = new Product();

        p.setId((Long) table.getValueAt(row, 0));
        p.setCikkszam((String) table.getValueAt(row, 1));
        p.setNev((String) table.getValueAt(row, 2));
        p.setMennyiseg((Integer) table.getValueAt(row, 3));
        p.setAr((Double) table.getValueAt(row, 4));

        return p;
    }

    private void deleteSelectedProduct() {
        try {
            Product p = getSelectedProduct();
            if (p == null) return;

            int conf = JOptionPane.showConfirmDialog(this,
                    "Biztos törlöd '" + p.getNev() + "' terméket?",
                    "Megerősítés", JOptionPane.YES_NO_OPTION);

            if (conf == JOptionPane.YES_OPTION) {
                productService.delete(p.getId());
                loadProducts();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Hiba törlés közben:\n" + e.getMessage());
        }
    }

    private void searchByCikkszam() {
        try {
            String cikkszam = tfSearch.getText().trim();
            if (cikkszam.isEmpty()) {
                loadProducts();
                return;
            }

            Product p = productService.getByCikkszam(cikkszam);

            model.setRowCount(0);
            model.addRow(new Object[]{
                    p.getId(),
                    p.getCikkszam(),
                    p.getNev(),
                    p.getMennyiseg(),
                    p.getAr()
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Nincs ilyen termék.",
                    "Keresés",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}