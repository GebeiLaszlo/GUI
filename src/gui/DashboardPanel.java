package gui;

import model.Product;
import model.WarehouseStats;
import service.MockProductService;
import service.MockWarehouseStatsService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.List;

public class DashboardPanel extends JPanel {

    private final MockProductService productService = new MockProductService();
    private final MockWarehouseStatsService statsService = new MockWarehouseStatsService();

    private List<Product> products;
    private WarehouseStats stats;

    private JLabel budgetLabel;
    private JLabel revenueLabel;
    private JLabel expensesLabel;
    private JLabel profitLabel;

    private JLabel capacityLabel;
    private JLabel usedSpaceLabel;
    private JLabel freeSpaceLabel;
    private JProgressBar usageBar;

    private JLabel productCountLabel;
    private JLabel totalStockLabel;
    private JLabel inventoryValueLabel;

    private JTable productTable;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> rowSorter;

    public DashboardPanel() {
        setLayout(new BorderLayout(16, 16));
        setBorder(new EmptyBorder(16, 16, 16, 16));
        setBackground(new Color(235, 238, 243));
        initUi();
        loadMockData();
    }

    private void initUi() {
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        add(createFooterPanel(), BorderLayout.SOUTH);
    }

    private JComponent createHeaderPanel() {
        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BorderLayout(8, 8));

        JLabel title = new JLabel("Bolt Dashboard");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 26f));

        JPanel titleWrapper = new JPanel(new BorderLayout());
        titleWrapper.setOpaque(false);
        titleWrapper.add(title, BorderLayout.WEST);
        header.add(titleWrapper, BorderLayout.NORTH);

        JPanel cards = new JPanel(new GridLayout(1, 3, 12, 0));
        cards.setOpaque(false);

        cards.add(createMoneyCard());
        cards.add(createCapacityCard());
        cards.add(createInventoryCard());

        header.add(cards, BorderLayout.CENTER);
        return header;
    }

    private JComponent createMoneyCard() {
        RoundedPanel panel = new RoundedPanel(16, new Color(250, 250, 250));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new TitledBorder("Pénzügyek"));

        budgetLabel = new JLabel("Keret: - Ft");
        revenueLabel = new JLabel("Bevétel: - Ft");
        expensesLabel = new JLabel("Kiadás: - Ft");
        profitLabel = new JLabel("Profit: - Ft");

        panel.add(Box.createVerticalStrut(4));
        panel.add(budgetLabel);
        panel.add(revenueLabel);
        panel.add(expensesLabel);

        panel.add(Box.createVerticalStrut(6));
        profitLabel.setFont(profitLabel.getFont().deriveFont(Font.BOLD));
        profitLabel.setForeground(new Color(0, 128, 0));
        panel.add(profitLabel);

        return panel;
    }

    private JComponent createCapacityCard() {
        RoundedPanel panel = new RoundedPanel(16, new Color(250, 250, 250));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new TitledBorder("Raktárhely"));

        capacityLabel = new JLabel("Kapacitás: - db");
        usedSpaceLabel = new JLabel("Használt: - db");
        freeSpaceLabel = new JLabel("Szabad: - db   (Bolt hely)");

        usageBar = new JProgressBar(0, 100);
        usageBar.setStringPainted(true);

        panel.add(Box.createVerticalStrut(4));
        panel.add(capacityLabel);
        panel.add(usedSpaceLabel);
        panel.add(freeSpaceLabel);
        panel.add(Box.createVerticalStrut(8));
        panel.add(usageBar);

        return panel;
    }

    private JComponent createInventoryCard() {
        RoundedPanel panel = new RoundedPanel(16, new Color(250, 250, 250));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new TitledBorder("Készlet összegzés"));

        productCountLabel = new JLabel("Termékek száma: -");
        totalStockLabel = new JLabel("Összes darab: -");
        inventoryValueLabel = new JLabel("Készlet értéke: - Ft");

        panel.add(Box.createVerticalStrut(4));
        panel.add(productCountLabel);
        panel.add(totalStockLabel);
        panel.add(Box.createVerticalStrut(6));
        inventoryValueLabel.setFont(inventoryValueLabel.getFont().deriveFont(Font.BOLD));
        panel.add(inventoryValueLabel);

        return panel;
    }

    private JComponent createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setOpaque(false);

        JLabel searchLabel = new JLabel("Keresés:");
        JTextField searchField = new JTextField(20);

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        panel.add(searchPanel, BorderLayout.NORTH);

        String[] columns = {
                "Termék",
                "Kategória",
                "Készlet",
                "Eladási ár (Ft)",
                "Összérték (Ft)"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return switch (columnIndex) {
                    case 2 -> Integer.class;
                    case 3, 4 -> Double.class;
                    default -> String.class;
                };
            }
        };

        productTable = new JTable(tableModel);
        productTable.setFillsViewportHeight(true);
        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productTable.setRowHeight(24);
        productTable.setShowGrid(true);
        productTable.setGridColor(new Color(220, 220, 220));

        JTableHeader header = productTable.getTableHeader();
        header.setFont(header.getFont().deriveFont(Font.BOLD));
        header.setReorderingAllowed(false);

        rowSorter = new TableRowSorter<>(tableModel);
        productTable.setRowSorter(rowSorter);

        productTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            private final DecimalFormat numberFormat = new DecimalFormat("#,##0");

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                DefaultTableCellRenderer cell = (DefaultTableCellRenderer)
                        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (value instanceof Number && (column == 3 || column == 4)) {
                    cell.setText(numberFormat.format(((Number) value).doubleValue()));
                }

                if (!isSelected) {
                    cell.setForeground(Color.BLACK);
                    cell.setBackground(Color.WHITE);

                    int modelRow = table.convertRowIndexToModel(row);
                    if (products != null && modelRow >= 0 && modelRow < products.size()) {
                        Product p = products.get(modelRow);
                        if (p.stock < 100) {
                            cell.setBackground(new Color(255, 235, 238));
                        }
                    }
                }

                return cell;
            }
        });

        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                    showSelectedProductDetails();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.getViewport().setBackground(Color.WHITE);
        panel.add(scrollPane, BorderLayout.CENTER);

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            private void updateFilter() {
                String text = searchField.getText();
                if (text == null || text.isBlank()) {
                    rowSorter.setRowFilter(null);
                } else {
                    String pattern = "(?i)" + text;
                    rowSorter.setRowFilter(RowFilter.regexFilter(pattern, 0, 1));
                }
            }

            @Override public void insertUpdate(DocumentEvent e) { updateFilter(); }
            @Override public void removeUpdate(DocumentEvent e) { updateFilter(); }
            @Override public void changedUpdate(DocumentEvent e) { updateFilter(); }
        });

        return panel;
    }

    private JPanel createFooterPanel() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setOpaque(false);

        JLabel info = new JLabel("Válassz ki egy terméket a készlet módosításához.");
        footer.add(info, BorderLayout.WEST);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.setOpaque(false);

        JButton minusBtn = new JButton("-100");
        JButton plusBtn = new JButton("+100");
        JButton detailsBtn = new JButton("Kijelölt termék részletei");

        minusBtn.addActionListener(e -> changeSelectedProductQuantity(-100));
        plusBtn.addActionListener(e -> changeSelectedProductQuantity(+100));
        detailsBtn.addActionListener(e -> showSelectedProductDetails());

        buttons.add(minusBtn);
        buttons.add(plusBtn);
        buttons.add(detailsBtn);

        footer.add(buttons, BorderLayout.EAST);
        return footer;
    }

    private void loadMockData() {
        stats = statsService.getStats();
        products = productService.getProducts();

        refreshTableFromProducts();
        recalcStatsFromProducts();
        updateStatsUi();
    }

    private void refreshTableFromProducts() {
        tableModel.setRowCount(0);
        if (products == null) return;

        for (Product p : products) {
            double totalValue = p.stock * p.sellingPrice;
            tableModel.addRow(new Object[]{
                    p.name,
                    p.category,
                    (Object) p.stock,
                    (Object) p.sellingPrice,
                    (Object) totalValue
            });
        }
    }

    private void recalcStatsFromProducts() {
        if (stats == null || products == null) return;

        int totalStock = 0;
        double totalValue = 0;

        for (Product p : products) {
            totalStock += p.stock;
            totalValue += p.stock * p.sellingPrice;
        }

        stats.currentStock = totalStock;
        stats.freeSpace = Math.max(0, stats.capacity - stats.currentStock);

        productCountLabel.setText("Termékek száma: " + products.size());
        totalStockLabel.setText("Összes darab: " + stats.currentStock);
        inventoryValueLabel.setText("Készlet értéke: " + String.format("%,.0f Ft", totalValue));
    }

    private void updateStatsUi() {
        if (stats == null) return;

        budgetLabel.setText("Keret: " + String.format("%,.0f Ft", stats.budget));
        revenueLabel.setText("Bevétel: " + String.format("%,.0f Ft", stats.revenue));
        expensesLabel.setText("Kiadás: " + String.format("%,.0f Ft", stats.expenses));
        profitLabel.setText("Profit: " + String.format("%,.0f Ft", stats.profit));

        capacityLabel.setText("Kapacitás: " + stats.capacity + " db");
        usedSpaceLabel.setText("Használt: " + stats.currentStock + " db");
        freeSpaceLabel.setText("Szabad: " + stats.freeSpace + " db   (Bolt hely)");

        int percent = (stats.capacity == 0)
                ? 0
                : (int) Math.round(stats.currentStock * 100.0 / stats.capacity);
        usageBar.setValue(percent);
        usageBar.setString(percent + " % kihasználtság");

        if (percent < 60) {
            usageBar.setForeground(new Color(46, 204, 113));
        } else if (percent < 90) {
            usageBar.setForeground(new Color(243, 156, 18));
        } else {
            usageBar.setForeground(new Color(231, 76, 60));
        }
    }

    private void changeSelectedProductQuantity(int delta) {
        int viewRow = productTable.getSelectedRow();
        if (viewRow < 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Először válassz ki egy terméket a táblázatban!",
                    "Nincs kiválasztva termék",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int modelRow = productTable.convertRowIndexToModel(viewRow);
        Product p = products.get(modelRow);

        int newStock = p.stock + delta;
        if (newStock < 0) newStock = 0;
        p.stock = newStock;

        tableModel.setValueAt(p.stock, modelRow, 2);
        double newTotal = p.stock * p.sellingPrice;
        tableModel.setValueAt(newTotal, modelRow, 4);

        recalcStatsFromProducts();
        updateStatsUi();
    }

    private void showSelectedProductDetails() {
        int viewRow = productTable.getSelectedRow();
        if (viewRow < 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nincs kijelölt termék.",
                    "Információ",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        int modelRow = productTable.convertRowIndexToModel(viewRow);
        Product p = products.get(modelRow);

        StringBuilder sb = new StringBuilder();
        sb.append("Név: ").append(p.name).append("\n");
        sb.append("Kategória: ").append(p.category).append("\n");
        sb.append("Készlet: ").append(p.stock).append(" db\n");
        sb.append("Eladási ár: ").append(String.format("%,.0f Ft", p.sellingPrice)).append("\n");
        if (p.description != null && !p.description.isBlank()) {
            sb.append("\nLeírás:\n").append(p.description);
        }

        JOptionPane.showMessageDialog(
                this,
                sb.toString(),
                "Termék részletei",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private static class RoundedPanel extends JPanel {
        private final int cornerRadius;
        private final Color backgroundColor;

        public RoundedPanel(int cornerRadius, Color bg) {
            this.cornerRadius = cornerRadius;
            this.backgroundColor = bg;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(),
                    cornerRadius, cornerRadius);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}
