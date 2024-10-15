package petCareApp.src.com.cse.ju.oop.views.screens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PetManagementUI extends JFrame {
    private JPanel mainPanel, topPanel, contentPanel, bottomPanel;
    private JTable petTable;
    private JButton addButton, editButton, deleteButton, backButton;
    private final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private final Color TEXT_COLOR = new Color(44, 62, 80);
    private final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private final Font NORMAL_FONT = new Font("Segoe UI", Font.PLAIN, 16);
    private final Font TABLE_HEADER_FONT = new Font("Segoe UI", Font.BOLD, 14);

    public PetManagementUI() {
        initializeFrame();
        createPanels();
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("PetCare - Manage Pets");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR);
    }

    private void createPanels() {
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        createTopPanel();
        createContentPanel();
        createBottomPanel();

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void createTopPanel() {
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(189, 195, 199)),
                new EmptyBorder(15, 20, 15, 20)
        ));

        JLabel titleLabel = new JLabel("Manage Pets");
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(TEXT_COLOR);

        JLabel adminLabel = new JLabel("Admin Dashboard");
        adminLabel.setFont(NORMAL_FONT);
        adminLabel.setForeground(SECONDARY_COLOR);

        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(adminLabel, BorderLayout.EAST);
    }

    private void createContentPanel() {
        contentPanel = new JPanel(new BorderLayout(0, 20));
        contentPanel.setBackground(BACKGROUND_COLOR);

        String[] columnNames = {"Pet ID", "Pet Name", "Age", "Species", "Pet Type", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        petTable = new JTable(tableModel);
        petTable.setFont(NORMAL_FONT);
        petTable.setRowHeight(30);
        petTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        petTable.setShowVerticalLines(false);
        petTable.setGridColor(new Color(189, 195, 199));

        JTableHeader header = petTable.getTableHeader();
        header.setFont(TABLE_HEADER_FONT);
        header.setBackground(PRIMARY_COLOR);
        header.setForeground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder());

        // Add some sample rows (replace with actual data as needed)
        tableModel.addRow(new Object[]{"1", "Buddy", "2", "Dog", "Golden Retriever", "Available"});
        tableModel.addRow(new Object[]{"2", "Milo", "1", "Cat", "Persian", "Adopted"});
        tableModel.addRow(new Object[]{"3", "Rex", "3", "Dog", "German Shepherd", "In Treatment"});
        tableModel.addRow(new Object[]{"1", "Buddy", "2", "Dog", "Golden Retriever", "Available"});
        tableModel.addRow(new Object[]{"2", "Milo", "1", "Cat", "Persian", "Adopted"});
        tableModel.addRow(new Object[]{"3", "Rex", "3", "Dog", "German Shepherd", "In Treatment"});
        tableModel.addRow(new Object[]{"1", "Buddy", "2", "Dog", "Golden Retriever", "Available"});
        tableModel.addRow(new Object[]{"2", "Milo", "1", "Cat", "Persian", "Adopted"});
        tableModel.addRow(new Object[]{"3", "Rex", "3", "Dog", "German Shepherd", "In Treatment"});
        tableModel.addRow(new Object[]{"1", "Buddy", "2", "Dog", "Golden Retriever", "Available"});
        tableModel.addRow(new Object[]{"2", "Milo", "1", "Cat", "Persian", "Adopted"});
        tableModel.addRow(new Object[]{"3", "Rex", "3", "Dog", "German Shepherd", "In Treatment"});
        tableModel.addRow(new Object[]{"1", "Buddy", "2", "Dog", "Golden Retriever", "Available"});
        tableModel.addRow(new Object[]{"2", "Milo", "1", "Cat", "Persian", "Adopted"});
        tableModel.addRow(new Object[]{"3", "Rex", "3", "Dog", "German Shepherd", "In Treatment"});
        tableModel.addRow(new Object[]{"1", "Buddy", "2", "Dog", "Golden Retriever", "Available"});
        tableModel.addRow(new Object[]{"2", "Milo", "1", "Cat", "Persian", "Adopted"});
        tableModel.addRow(new Object[]{"3", "Rex", "3", "Dog", "German Shepherd", "In Treatment"});
        tableModel.addRow(new Object[]{"1", "Buddy", "2", "Dog", "Golden Retriever", "Available"});
        tableModel.addRow(new Object[]{"2", "Milo", "1", "Cat", "Persian", "Adopted"});
        tableModel.addRow(new Object[]{"3", "Rex", "3", "Dog", "German Shepherd", "In Treatment"});
        tableModel.addRow(new Object[]{"1", "Buddy", "2", "Dog", "Golden Retriever", "Available"});
        tableModel.addRow(new Object[]{"2", "Milo", "1", "Cat", "Persian", "Adopted"});
        tableModel.addRow(new Object[]{"3", "Rex", "3", "Dog", "German Shepherd", "In Treatment"});

        JScrollPane tableScrollPane = new JScrollPane(petTable);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder());

        contentPanel.add(tableScrollPane, BorderLayout.CENTER);
    }

    private void createBottomPanel() {
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        bottomPanel.setBackground(BACKGROUND_COLOR);

        addButton = createStyledButton("Add Pet");
        editButton = createStyledButton("Edit Pet");
        deleteButton = createStyledButton("Delete Pet");
        backButton = createStyledButton("Back to Dashboard");

        addButton.addActionListener(e -> addPet());
        editButton.addActionListener(e -> editPet());
        deleteButton.addActionListener(e -> deletePet());
        backButton.addActionListener(e -> backToDashboard());

        bottomPanel.add(addButton);
        bottomPanel.add(editButton);
        bottomPanel.add(deleteButton);
        bottomPanel.add(backButton);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(NORMAL_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(PRIMARY_COLOR);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(180, 40));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(SECONDARY_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(PRIMARY_COLOR);
            }
        });

        return button;
    }

    private void addPet() {
        JOptionPane.showMessageDialog(this, "Add Pet functionality will be implemented here.", "Add Pet", JOptionPane.INFORMATION_MESSAGE);
    }

    private void editPet() {
        int selectedRow = petTable.getSelectedRow();
        if (selectedRow != -1) {
            String petName = (String) petTable.getValueAt(selectedRow, 1);
            JOptionPane.showMessageDialog(this, "Edit Pet functionality for " + petName + " will be implemented here.", "Edit Pet", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a pet to edit.", "No Pet Selected", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deletePet() {
        int selectedRow = petTable.getSelectedRow();
        if (selectedRow != -1) {
            String petName = (String) petTable.getValueAt(selectedRow, 1);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete " + petName + "?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                ((DefaultTableModel) petTable.getModel()).removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, petName + " has been deleted.", "Pet Deleted", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a pet to delete.", "No Pet Selected", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void backToDashboard() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to go back to the dashboard?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            new AdminInterface();
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new PetManagementUI();
        });
    }
}