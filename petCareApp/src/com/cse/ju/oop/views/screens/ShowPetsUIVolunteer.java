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

public class ShowPetsUIVolunteer extends JFrame {
    private JPanel topPanel;
    private JPanel contentPanel;
    private JPanel bottomPanel;
    private JTable petTable;
    private final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private final Color TEXT_COLOR = new Color(44, 62, 80);
    private final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private final Font NORMAL_FONT = new Font("Segoe UI", Font.PLAIN, 16);
    private final Font TABLE_HEADER_FONT = new Font("Segoe UI", Font.BOLD, 14);
    private DefaultTableModel tableModel;

    public ShowPetsUIVolunteer() {
        initializeFrame();
        createPanels();
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("PetCare - Show Pets");
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
///
private void createTopPanel() {
    topPanel = new JPanel(new BorderLayout());
    topPanel.setBackground(Color.WHITE);
    topPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(189, 195, 199)),
            new EmptyBorder(15, 20, 15, 20)
    ));

    // Load and scale the logo
    ImageIcon originalLogo = new ImageIcon("/home/almizan/PetCare/petCareApp/src/com/cse/ju/oop/views/screens/logo.png"); // Replace with the correct path to your logo
    Image logoImage = originalLogo.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH); // Adjust width and height as needed
    ImageIcon scaledLogo = new ImageIcon(logoImage);

    JLabel logoLabel = new JLabel(scaledLogo);

    // Create a panel for the title and center it
    JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
    titlePanel.setBackground(Color.WHITE); // Ensure title panel background is white

    JLabel titleLabel = new JLabel("Show Pets");
    titleLabel.setFont(HEADER_FONT);
    titleLabel.setForeground(TEXT_COLOR);
    titlePanel.add(titleLabel); // Add the title label to the title panel

    // Add the logo, titlePanel, and volunteerLabel to the topPanel
    topPanel.add(logoLabel, BorderLayout.WEST); // Add logo to the left
    topPanel.add(titlePanel, BorderLayout.CENTER); // Add the titlePanel to center
    JLabel volunteerLabel = new JLabel("Volunteer Dashboard");
    volunteerLabel.setFont(NORMAL_FONT);
    volunteerLabel.setForeground(SECONDARY_COLOR);
    topPanel.add(volunteerLabel, BorderLayout.EAST); // Add volunteer label to the right
}


    /////
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/petCare_db";
        String user = "root";
        String password = "mysql@1234";
        return DriverManager.getConnection(url, user, password);
    }

    private void createContentPanel() {
        contentPanel = new JPanel(new BorderLayout(0, 20));
        contentPanel.setBackground(BACKGROUND_COLOR);

        String[] columnNames = {"Pet ID", "Pet Name", "Age (Years)", "Age (Months)", "Species", "Pet Type"};
        tableModel = new DefaultTableModel(columnNames, 0) {
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

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < petTable.getColumnCount(); i++) {
            petTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane tableScrollPane = new JScrollPane(petTable);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder());

        contentPanel.add(tableScrollPane, BorderLayout.CENTER);
        fetchPetsData();
    }

    private void fetchPetsData() {
        DefaultTableModel model = (DefaultTableModel) petTable.getModel();
        model.setRowCount(0); // Clear existing data

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM pets")) {

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("petname"),
                        rs.getInt("age_years"),
                        rs.getInt("age_months"),
                        rs.getString("species"),
                        rs.getString("pettype")
                };
                model.addRow(row);
            }
            petTable.setModel(tableModel);
            petTable.repaint();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching pets data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createBottomPanel() {
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        bottomPanel.setBackground(BACKGROUND_COLOR);

        JButton backButton = createStyledButton("Back to Dashboard");

        backButton.addActionListener(e -> backToDashboard());

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

    private void backToDashboard() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to go back to the dashboard?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            new VolunteerInterface();
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
            new ShowPetsUIVolunteer();
        });
    }
}
