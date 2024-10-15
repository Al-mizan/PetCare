package petCareApp.src.com.cse.ju.oop.views.screens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VolunteerManagementUI extends JFrame {
    private JPanel mainPanel, topPanel, contentPanel, bottomPanel;
    private JTable volunteerTable;
    private JButton editButton, saveButton, backButton, logoutButton;
    private final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private final Color TEXT_COLOR = new Color(44, 62, 80);
    private final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private final Font NORMAL_FONT = new Font("Segoe UI", Font.PLAIN, 16);
    private final Font TABLE_HEADER_FONT = new Font("Segoe UI", Font.BOLD, 14);

    public VolunteerManagementUI() {
        initializeFrame();
        createPanels();
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("PetCare - Volunteer Management");
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

        JLabel titleLabel = new JLabel("Admin");
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(TEXT_COLOR);

//        logoutButton = createStyledButton("Logout");
//        logoutButton.setPreferredSize(new Dimension(100, 30));

        topPanel.add(titleLabel, BorderLayout.CENTER);
//        topPanel.add(logoutButton, BorderLayout.EAST);
    }

    private void createContentPanel() {
        contentPanel = new JPanel(new BorderLayout(0, 20));
        contentPanel.setBackground(BACKGROUND_COLOR);

        JLabel volunteerLabel = new JLabel("Volunteer Management");
        volunteerLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        volunteerLabel.setForeground(TEXT_COLOR);
        volunteerLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

        String[] columnNames = {"ID", "Name", "Phone Number", "Number of Rescues"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Add sample data
        tableModel.addRow(new Object[]{1, "Alice Smith", "123-456-7890", 5});
        tableModel.addRow(new Object[]{2, "Bob Johnson", "987-654-3210", 3});
        tableModel.addRow(new Object[]{3, "Charlie Davis", "555-123-4567", 8});
        tableModel.addRow(new Object[]{4, "Diana Brown", "444-567-8901", 4});
        tableModel.addRow(new Object[]{5, "Evan Lewis", "333-789-1234", 2});
        tableModel.addRow(new Object[]{1, "Alice Smith", "123-456-7890", 5});
        tableModel.addRow(new Object[]{2, "Bob Johnson", "987-654-3210", 3});
        tableModel.addRow(new Object[]{3, "Charlie Davis", "555-123-4567", 8});
        tableModel.addRow(new Object[]{4, "Diana Brown", "444-567-8901", 4});
        tableModel.addRow(new Object[]{5, "Evan Lewis", "333-789-1234", 2});
        tableModel.addRow(new Object[]{1, "Alice Smith", "123-456-7890", 5});
        tableModel.addRow(new Object[]{2, "Bob Johnson", "987-654-3210", 3});
        tableModel.addRow(new Object[]{3, "Charlie Davis", "555-123-4567", 8});
        tableModel.addRow(new Object[]{4, "Diana Brown", "444-567-8901", 4});
        tableModel.addRow(new Object[]{5, "Evan Lewis", "333-789-1234", 2});
        tableModel.addRow(new Object[]{1, "Alice Smith", "123-456-7890", 5});
        tableModel.addRow(new Object[]{2, "Bob Johnson", "987-654-3210", 3});
        tableModel.addRow(new Object[]{3, "Charlie Davis", "555-123-4567", 8});
        tableModel.addRow(new Object[]{4, "Diana Brown", "444-567-8901", 4});
        tableModel.addRow(new Object[]{5, "Evan Lewis", "333-789-1234", 2});
        tableModel.addRow(new Object[]{1, "Alice Smith", "123-456-7890", 5});
        tableModel.addRow(new Object[]{2, "Bob Johnson", "987-654-3210", 3});
        tableModel.addRow(new Object[]{3, "Charlie Davis", "555-123-4567", 8});
        tableModel.addRow(new Object[]{4, "Diana Brown", "444-567-8901", 4});
        tableModel.addRow(new Object[]{5, "Evan Lewis", "333-789-1234", 2});

        volunteerTable = new JTable(tableModel); /////
        volunteerTable.setFont(NORMAL_FONT);
        volunteerTable.setRowHeight(30);
        volunteerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        volunteerTable.setShowVerticalLines(false);
        volunteerTable.setGridColor(new Color(189, 195, 199));

        JTableHeader header = volunteerTable.getTableHeader();
        header.setFont(TABLE_HEADER_FONT);
        header.setBackground(PRIMARY_COLOR);
        header.setForeground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder());

        // Center table content in each cell
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Apply the renderer to each column
        for (int i = 0; i < volunteerTable.getColumnCount(); i++) {
            volunteerTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane tableScrollPane = new JScrollPane(volunteerTable);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder());

        contentPanel.add(volunteerLabel, BorderLayout.NORTH);
        contentPanel.add(tableScrollPane, BorderLayout.CENTER);
    }

    private void createBottomPanel() {
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        bottomPanel.setBackground(BACKGROUND_COLOR);

        editButton = createStyledButton("Edit");
        backButton = createStyledButton("Back");

        editButton.addActionListener(e -> editVolunteer());
        backButton.addActionListener(e -> backToDashboard());

        bottomPanel.add(editButton);
//        bottomPanel.add(saveButton);
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
        button.setPreferredSize(new Dimension(120, 40));

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

    private void editVolunteer() {
        int selectedRow = volunteerTable.getSelectedRow();
        if (selectedRow != -1) {
            String petName = (String) volunteerTable.getValueAt(selectedRow, 1);
            JOptionPane.showMessageDialog(this, "Edit Pet functionality for " + petName + " will be implemented here.", "Edit Pet", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a pet to edit.", "No Pet Selected", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void backToDashboard() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to go back to the dashboard?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            new AdminInterface();
            dispose();
        }
    }

    // TODO: Implement action listeners for buttons

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new VolunteerManagementUI();
        });
    }
}