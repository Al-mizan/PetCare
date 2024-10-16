package petCareApp.src.com.cse.ju.oop.views.screens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserManagementUI extends JFrame {
    private JPanel mainPanel, topPanel, contentPanel, bottomPanel;
    private JTable userTable;
    private JButton editButton, saveButton, backButton, logoutButton;
    private final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private final Color TEXT_COLOR = new Color(44, 62, 80);
    private final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private final Font NORMAL_FONT = new Font("Segoe UI", Font.PLAIN, 16);
    private final Font TABLE_HEADER_FONT = new Font("Segoe UI", Font.BOLD, 14);

    public UserManagementUI() {
        initializeFrame();
        createPanels();
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("PetCare - User Management");
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

        JLabel titleLabel = new JLabel("Users Management");
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

//        JLabel userLabel = new JLabel("User Management");
//        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
//        userLabel.setForeground(TEXT_COLOR);
//        userLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

        String[] columnNames = {"ID", "Name", "Phone Number", "Number of PetAdopted"};
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

        userTable = new JTable(tableModel); /////
        userTable.setFont(NORMAL_FONT);
        userTable.setRowHeight(30);
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userTable.setShowVerticalLines(false);
        userTable.setGridColor(new Color(189, 195, 199));

        JTableHeader header = userTable.getTableHeader();
        header.setFont(TABLE_HEADER_FONT);
        header.setBackground(PRIMARY_COLOR);
        header.setForeground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder());
        header.setReorderingAllowed(false);
        header.setResizingAllowed(false);

        // Center table content in each cell
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Apply the renderer to each column
        for (int i = 0; i < userTable.getColumnCount(); i++) {
            userTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Create a scroll pane for the table
        JScrollPane tableScrollPane = new JScrollPane(userTable);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder());
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tableScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Set preferred size for the scroll pane to control its height
        tableScrollPane.setPreferredSize(new Dimension(900, 400));

        // Add components to the content panel
//        contentPanel.add(userLabel, BorderLayout.NORTH);
        contentPanel.add(tableScrollPane, BorderLayout.CENTER);
    }

    private void createBottomPanel() {
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        bottomPanel.setBackground(BACKGROUND_COLOR);

        editButton = createStyledButton("Edit");
        backButton = createStyledButton("Back");

        editButton.addActionListener(e -> editUser());
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

    private void editUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow != -1) {
            String petName = (String) userTable.getValueAt(selectedRow, 1);
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
            new UserManagementUI();
        });
    }
}