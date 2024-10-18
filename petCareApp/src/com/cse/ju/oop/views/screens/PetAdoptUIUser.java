package petCareApp.src.com.cse.ju.oop.views.screens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.border.TitledBorder;
import java.util.ArrayList;
import java.util.List;

public class PetAdoptUIUser extends JFrame {
    private JPanel leftPanel, rightPanel, filterPanel, tablePanel, buttonPanel;
    private JTable petTable;
    private DefaultTableModel tableModel;
    private JButton backButton, adoptButton;

    private final Color PRIMARY_COLOR = new Color(65, 105, 225);
    private final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private final Color TEXT_COLOR = new Color(44, 62, 80);
    private final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private final Font NORMAL_FONT = new Font("Segoe UI", Font.PLAIN, 18);
    private final Font SIDEBAR_FONT = new Font("Segoe UI", Font.BOLD, 20);

    private static final String DB_URL = "jdbc:mysql://localhost:3306/petCare_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "mysql@1234";

    private ButtonGroup petTypeGroup;
    private ButtonGroup ageGroup;
    private int Id;

    public PetAdoptUIUser() {
        initializeFrame();
        createPanels();
        loadPetsFromDatabase("All", "All");
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("PetCare - Adoption");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR);
    }

    private void createPanels() {
        createLeftPanel();
        createRightPanel();
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }

    private void createLeftPanel() {
        leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(PRIMARY_COLOR);
        leftPanel.setPreferredSize(new Dimension(250, getHeight()));
        leftPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel logoLabel = new JLabel("PetCare");
        logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(logoLabel, BorderLayout.NORTH);

        createFilterPanel();
        leftPanel.add(filterPanel, BorderLayout.CENTER);
    }

    private void createFilterPanel() {
        filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBackground(PRIMARY_COLOR);

        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE),
                "Filters"
        );
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setTitleColor(Color.WHITE);
        titledBorder.setTitleFont(SIDEBAR_FONT);

        filterPanel.setBorder(titledBorder);

        petTypeGroup = new ButtonGroup();
        ageGroup = new ButtonGroup();

        addFilterSection(filterPanel, "Pet Type", new String[]{"All", "Cat", "Dog", "Bird"}, petTypeGroup);
        addFilterSection(filterPanel, "Age", new String[]{"All", "0-11 months", "1-3 years", "4 years and above"}, ageGroup);
    }

    private void createRightPanel() {
        rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(BACKGROUND_COLOR);
        rightPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        createTablePanel();
        createButtonPanel();

        rightPanel.add(new JLabel("Pet Adoption", SwingConstants.CENTER), BorderLayout.NORTH);
        rightPanel.add(tablePanel, BorderLayout.CENTER);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void createTablePanel() {
        tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(BACKGROUND_COLOR);

        createPetTable();
        JScrollPane scrollPane = new JScrollPane(petTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 2));

        tablePanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void createButtonPanel() {
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(BACKGROUND_COLOR);

        adoptButton = createStyledButton("Adopt");
        backButton = createStyledButton("Back");

        adoptButton.addActionListener(e -> handleAdopt());
        backButton.addActionListener(e -> handleBack());

        buttonPanel.add(adoptButton);
        buttonPanel.add(backButton);
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

    private void handleAdopt() {
        int selectedRow = petTable.getSelectedRow();
        if (selectedRow != -1) {
            int petId = (int) petTable.getValueAt(selectedRow, 0);
            String petName = (String) petTable.getValueAt(selectedRow, 1);

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to adopt " + petName + " (ID: " + petId + ")?",
                    "Confirm Adoption", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (removePetFromDatabase(petId)) {
                    tableModel.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(this,
                            petName + " has been successfully adopted! We will get you soon, stay tuned!",
                            "Adoption Successful", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "There was an error processing the adoption. Please try again.",
                            "Adoption Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please select a pet to adopt.",
                    "No Pet Selected", JOptionPane.WARNING_MESSAGE);
        }
    }

    private boolean removePetFromDatabase(int petId) {
        String sql = "DELETE FROM pets WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, petId);
            int affectedRows = pstmt.executeUpdate();

            int loginID = LoginScreen.ID;
            if(loginID != -1) {
                Id = loginID;
            }

            String sqlUpdateUser = "UPDATE users SET pets_adopted = pets_adopted + 1 WHERE id = ?";
            try (PreparedStatement pstmtUpdateUser = conn.prepareStatement(sqlUpdateUser)) {
                pstmtUpdateUser.setInt(1, Id);
                pstmtUpdateUser.executeUpdate();
            }

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void addFilterSection(JPanel panel, String title, String[] options, ButtonGroup group) {
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(NORMAL_FONT);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 0)));

        for (String option : options) {
            JRadioButton radioButton = new JRadioButton(option);
            radioButton.setFont(NORMAL_FONT);
            radioButton.setForeground(Color.WHITE);
            radioButton.setBackground(PRIMARY_COLOR);
            radioButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            group.add(radioButton);
            panel.add(radioButton);
            panel.add(Box.createRigidArea(new Dimension(0, 0)));

            radioButton.addActionListener(e -> applyFilters());
        }
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Set "All" as default selected
        group.getElements().nextElement().setSelected(true);
    }

    private void applyFilters() {
        String petTypeFilter = getSelectedButtonText(petTypeGroup);
        String ageFilter = getSelectedButtonText(ageGroup);
        loadPetsFromDatabase(petTypeFilter, ageFilter);
    }

    private String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (java.util.Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    private void createPetTable() {
        String[] columnNames = {"Pet ID", "Pet Name", "Age (Years)", "Age (Months)", "Species", "Pet Type"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0 || column == 2) {
                    return Integer.class;
                }
                return String.class;
            }
        };
        petTable = new JTable(tableModel);
        petTable.setFont(NORMAL_FONT);
        petTable.getTableHeader().setFont(SIDEBAR_FONT);
        petTable.setRowHeight(30);
        petTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < petTable.getColumnCount(); i++) {
            petTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void loadPetsFromDatabase(String petTypeFilter, String ageFilter) {
        tableModel.setRowCount(0);  // Clear existing data
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            StringBuilder query = new StringBuilder("SELECT * FROM pets");
            List<String> conditions = new ArrayList<>();

            if (!"All".equals(petTypeFilter)) {
                conditions.add("pettype = ?");
            }

            if (!"All".equals(ageFilter)) {
                switch (ageFilter) {
                    case "0-11 months":
                        conditions.add("(age_years = 0 AND age_months BETWEEN 0 AND 11)");
                        break;
                    case "1-3 years":
                        conditions.add("((age_years BETWEEN 1 AND 2) OR (age_years = 3 AND age_months <= 11))");
                        break;
                    case "4 years and above":
                        conditions.add("(age_years >= 4)");
                        break;
                }
            }

            if (!conditions.isEmpty()) {
                query.append(" WHERE ").append(String.join(" AND ", conditions));
            }

            try (PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
                if (!"All".equals(petTypeFilter)) {
                    pstmt.setString(1, petTypeFilter);
                }

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("petname");
                        int ageYears = rs.getInt("age_years");
                        int ageMonths = rs.getInt("age_months");
                        String species = rs.getString("species");
                        String type = rs.getString("pettype");
                        tableModel.addRow(new Object[]{id, name, ageYears, ageMonths, species, type});
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading pets from database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleBack() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to go back to the dashboard?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            new UserInterface();
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
            new PetAdoptUIUser();
        });
    }
}
