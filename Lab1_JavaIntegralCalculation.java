package lab1_javaintegralcalculation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Lab1_JavaIntegralCalculation extends JFrame {

    private JTextField jTextFieldLowerLimit;
    private JTextField jTextFieldUpperLimit;
    private JTextField jTextFieldStep;
    private JTable jTable1;
    private DefaultTableModel tableModel;
    private JButton jButtonAdd;
    private JButton jButtonDelete;
    private JButton jButtonCalculate;

    public Lab1_JavaIntegralCalculation() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Integral Calculator - sin(x)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        dataPanel.setBorder(BorderFactory.createTitledBorder("Data"));

        dataPanel.add(new JLabel("Lower limit:"));
        jTextFieldLowerLimit = new JTextField(8);
        dataPanel.add(jTextFieldLowerLimit);

        dataPanel.add(new JLabel("Upper limit:"));
        jTextFieldUpperLimit = new JTextField(8);
        dataPanel.add(jTextFieldUpperLimit);

        dataPanel.add(new JLabel("Step:"));
        jTextFieldStep = new JTextField(8);
        dataPanel.add(jTextFieldStep);

        JPanel actionsPanel = new JPanel(new BorderLayout(5, 10));
        actionsPanel.setBorder(BorderFactory.createTitledBorder("Actions"));

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        jButtonAdd = new JButton("Add Item");
        jButtonDelete = new JButton("Delete Item");
        jButtonCalculate = new JButton("Calculate");

        buttonRow.add(jButtonAdd);
        buttonRow.add(jButtonDelete);
        buttonRow.add(jButtonCalculate);

        String[] columnNames = {"Lower limit", "Upper limit", "Step", "Result"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 3;
            }
        };
        jTable1 = new JTable(tableModel);
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable1.setRowHeight(24);
        JScrollPane scrollPane = new JScrollPane(jTable1);
        scrollPane.setPreferredSize(new Dimension(550, 200));

        actionsPanel.add(buttonRow, BorderLayout.NORTH);
        actionsPanel.add(scrollPane, BorderLayout.CENTER);

        jButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lower = jTextFieldLowerLimit.getText().trim();
                String upper = jTextFieldUpperLimit.getText().trim();
                String step  = jTextFieldStep.getText().trim();

                if (lower.isEmpty() || upper.isEmpty() || step.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                    return;
                }
                try {
                    double a = Double.parseDouble(lower);
                    double b = Double.parseDouble(upper);
                    double h = Double.parseDouble(step);
                    if (h <= 0) throw new NumberFormatException();
                    tableModel.addRow(new Object[]{a, b, h, ""});
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Enter valid numbers. Step must be > 0.");
                }
            }
        });

        jButtonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = jTable1.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete.");
                    return;
                }
                tableModel.removeRow(selectedRow);
            }
        });

        jButtonCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = jTable1.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row to calculate.");
                    return;
                }

                try {
                    double a = Double.parseDouble(tableModel.getValueAt(selectedRow, 0).toString());
                    double b = Double.parseDouble(tableModel.getValueAt(selectedRow, 1).toString());
                    double h = Double.parseDouble(tableModel.getValueAt(selectedRow, 2).toString());

                    double result = 0.0;
                    int n = (int) Math.round((b - a) / h);
                    for (int i = 0; i < n; i++) {
                        double x0 = a + i * h;
                        double x1 = a + (i + 1) * h;
                        result += (Math.sin(x0) + Math.sin(x1)) / 2.0 * h;
                    }

                    result = Math.round(result * 1_000_000.0) / 1_000_000.0;
                    tableModel.setValueAt(result, selectedRow, 3);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error during calculation: " + ex.getMessage());
                }
            }
        });

        add(dataPanel, BorderLayout.NORTH);
        add(actionsPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Lab1_JavaIntegralCalculation();
            }
        });
    }
}