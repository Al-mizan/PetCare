package petCareApp.src.com.cse.ju.oop.views.screens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class VolunteerManagementUI extends JFrame {
    private JPanel topPanel;
    private JPanel contentPanel;
    private JPanel bottomPanel;
    private JTable volunteerTable;
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
        loadVolunteerData();
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
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
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

        // Load the logo image (Make sure the path is correct)
        ImageIcon logoIcon = new ImageIcon("/home/almizan/PetCare/petCareApp/src/com/cse/ju/oop/views/screens/logo.png");
        Image logoImage = logoIcon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);  // Resize logo to appropriate size
        JPanel titlePanel = getjPanel(logoImage);

        // Admin dashboard label on the right
        JLabel adminLabel = new JLabel("Admin Dashboard");
        adminLabel.setFont(NORMAL_FONT);
        adminLabel.setForeground(SECONDARY_COLOR);

        // Add the title panel (logo + title) and admin label to the top panel
        topPanel.add(titlePanel, BorderLayout.WEST);
        topPanel.add(adminLabel, BorderLayout.EAST);
    }

    private JPanel getjPanel(Image logoImage) {
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setHorizontalAlignment(SwingConstants.LEFT);

        // Title label for "Volunteers Management"
        JLabel titleLabel = new JLabel("                               Volunteers Management");
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(TEXT_COLOR);

        // Create a panel to hold both the logo and the title
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        titlePanel.setBackground(Color.WHITE);
        titlePanel.add(logoLabel);
        titlePanel.add(titleLabel);
        return titlePanel;
    }

    private void createContentPanel() {
        contentPanel = new JPanel(new BorderLayout(0, 20));
        contentPanel.setBackground(BACKGROUND_COLOR);

        String[] columnNames = {"ID", "Username", "Name", "Gender", "Phone", "Email", "PetsRescued"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        volunteerTable = new JTable(tableModel);
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

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < volunteerTable.getColumnCount(); i++) {
            volunteerTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane tableScrollPane = new JScrollPane(volunteerTable);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder());

        contentPanel.add(tableScrollPane, BorderLayout.CENTER);
    }

    private void createBottomPanel() {
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        bottomPanel.setBackground(BACKGROUND_COLOR);

        JButton deleteButton = createStyledButton("Delete");
        JButton backButton = createStyledButton("Back");

        deleteButton.addActionListener(e -> deleteVolunteer());
        backButton.addActionListener(e -> backToDashboard());

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

    private void loadVolunteerData() {
        DefaultTableModel model = (DefaultTableModel) volunteerTable.getModel();
        model.setRowCount(0); // Clear existing data

        String url = "jdbc:mysql://localhost:3306/petCare_db";
        String user = "root";
        String password = "mysql@1234";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, username, firstname, gender, phone, email, pets_rescued FROM volunteers")) {

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("firstname"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getInt("pets_rescued")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading volunteer data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteVolunteer() {
        int selectedRow = volunteerTable.getSelectedRow();
        if (selectedRow != -1) {
            int volunteerId = (int) volunteerTable.getValueAt(selectedRow, 0);
            String volunteerName = (String) volunteerTable.getValueAt(selectedRow, 2);

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete volunteer " + volunteerName + " (ID: " + volunteerId + ")?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (deleteVolunteerFromDatabase(volunteerId)) {
                    ((DefaultTableModel) volunteerTable.getModel()).removeRow(selectedRow);
                    JOptionPane.showMessageDialog(this, "Volunteer deleted successfully.", "Deletion Successful", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete volunteer from the database.", "Deletion Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a volunteer to delete.", "No Volunteer Selected", JOptionPane.WARNING_MESSAGE);
        }
    }

    private boolean deleteVolunteerFromDatabase(int volunteerId) {
        String url = "jdbc:mysql://localhost:3306/petCare_db";
        String user = "root";
        String password = "mysql@1234";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM volunteers WHERE id = ?")) {

            pstmt.setInt(1, volunteerId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void backToDashboard() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to go back to the dashboard?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            new AdminInterface();
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VolunteerManagementUI::new);
    }
}
