package petCareApp.src.com.cse.ju.oop.views.screens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class AddPetsUIVolunteer extends JFrame {
    private JPanel sidebarPanel, mainPanel, headerPanel, contentPanel;
    private JComboBox<String> petTypeComboBox;
    private JTextField petNameField, petSpeciesField, petAgeYearsField, petAgeMonthsField;
    private int volunteerId;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/petCare_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "mysql@1234";

    private static final Color PRIMARY_COLOR = new Color(65, 105, 225);
    private static final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private static final Color TEXT_COLOR = new Color(44, 62, 80);
    private static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private static final Font NORMAL_FONT = new Font("Segoe UI", Font.PLAIN, 18);
    private static final Font SIDEBAR_FONT = new Font("Segoe UI", Font.BOLD, 20);
    private int Id;

    public AddPetsUIVolunteer() {
        initializeFrame();
        createPanels();
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("PetCare - Add Pet");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR);
    }

    private void createPanels() {
        createSidebarPanel();
        createMainPanel();
        add(sidebarPanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
    }
///////////////////////////////////////////////////////////////////////
private void createSidebarPanel() {
    sidebarPanel = new JPanel();
    sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
    sidebarPanel.setBackground(PRIMARY_COLOR);
    sidebarPanel.setPreferredSize(new Dimension(250, getHeight()));
    sidebarPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

    // PetCare Title
    JLabel logoLabel = new JLabel("PetCare");
    logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
    logoLabel.setForeground(Color.WHITE);
    logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    sidebarPanel.add(logoLabel);

    sidebarPanel.add(Box.createRigidArea(new Dimension(0, 50)));

    // Add menu buttons
    createMenuButton("Dashboard", e -> goBack());
    createMenuButton("Add Pet", e -> {/* Current page */});
    createMenuButton("Show Pets", e -> openShowPets());
    createMenuButton("About Us", e -> showAboutUs());

    sidebarPanel.add(Box.createVerticalGlue());

    // Add the logo above the logout button
    ImageIcon logoImage = new ImageIcon("/home/almizan/PetCare/petCareApp/src/com/cse/ju/oop/views/screens/logo.png"); // Ensure the path is correct
    Image scaledLogo = logoImage.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH); // Adjust the size as needed
    JLabel logoImageLabel = new JLabel(new ImageIcon(scaledLogo));
    logoImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    sidebarPanel.add(logoImageLabel);

    // Logout Button
    JButton logoutButton = createMenuButton("Logout", e -> handleLogout());
    logoutButton.setBackground(new Color(231, 76, 60));
}

    /////////////////////////////////////////////////////////////////
    private void createMainPanel() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);

        createHeaderPanel();
        createContentPanel();

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
    }

    private void createHeaderPanel() {
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel titleLabel = new JLabel("Add New Pet");
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(TEXT_COLOR);
        headerPanel.add(titleLabel, BorderLayout.WEST);

        JLabel dateLabel = new JLabel("Today: " + java.time.LocalDate.now().toString());
        dateLabel.setFont(NORMAL_FONT);
        dateLabel.setForeground(TEXT_COLOR);
        headerPanel.add(dateLabel, BorderLayout.EAST);
    }

    private void createContentPanel() {
        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(BACKGROUND_COLOR);
        contentPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1;

        JPanel formPanel = createFormPanel();
        contentPanel.add(formPanel, gbc);

        JPanel buttonPanel = createButtonPanel();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        contentPanel.add(buttonPanel, gbc);
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
                new EmptyBorder(20, 20, 20, 20)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        String[] petTypes = {"Dog", "Cat", "Bird", "Other"};
        petTypeComboBox = new JComboBox<>(petTypes);
        petNameField = new JTextField(15);
        petSpeciesField = new JTextField(15);
        petAgeYearsField = new JTextField(5);
        petAgeMonthsField = new JTextField(5);

        addFormField(formPanel, gbc, "Pet Type:", petTypeComboBox, 0);
        addFormField(formPanel, gbc, "Pet Name:", petNameField, 1);
        addFormField(formPanel, gbc, "Pet Species:", petSpeciesField, 2);
        addAgeFields(formPanel, gbc, 3);

        return formPanel;
    }

    private void addAgeFields(JPanel panel, GridBagConstraints gbc, int gridy) {
        JLabel ageLabel = new JLabel("Pet Age:");
        ageLabel.setFont(NORMAL_FONT);
        ageLabel.setForeground(TEXT_COLOR);

        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(ageLabel, gbc);

        JPanel agePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        agePanel.setOpaque(false);

        petAgeYearsField.setFont(NORMAL_FONT);
        petAgeMonthsField.setFont(NORMAL_FONT);

        agePanel.add(createLabeledField(petAgeYearsField, "Years"));
        agePanel.add(createLabeledField(petAgeMonthsField, "Months"));

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(agePanel, gbc);
    }

    private JPanel createLabeledField(JTextField field, String labelText) {
        JPanel panel = new JPanel(new BorderLayout(5, 0));
        panel.setOpaque(false);
        field.setColumns(5);
        panel.add(field, BorderLayout.CENTER);
        JLabel label = new JLabel(labelText);
        label.setFont(NORMAL_FONT.deriveFont(Font.PLAIN, 14));
        label.setForeground(TEXT_COLOR);
        panel.add(label, BorderLayout.EAST);
        return panel;
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, String labelText, JComponent component, int gridy) {
        JLabel label = new JLabel(labelText);
        label.setFont(NORMAL_FONT);
        label.setForeground(TEXT_COLOR);

        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(component, gbc);

        if (component instanceof JTextField) {
            component.setFont(NORMAL_FONT);
        } else if (component instanceof JComboBox) {
            component.setFont(NORMAL_FONT);
        }
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);

        JButton addButton = createStyledButton("Add Pet", e -> addPet());
        JButton clearButton = createStyledButton("Clear", e -> clearFields());

        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);

        return buttonPanel;
    }

    private JButton createStyledButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(NORMAL_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(SECONDARY_COLOR);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(actionListener);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(PRIMARY_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(SECONDARY_COLOR);
            }
        });

        return button;
    }

    private JButton createMenuButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(SIDEBAR_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(PRIMARY_COLOR);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

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

        button.addActionListener(actionListener);
        sidebarPanel.add(button);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        return button;
    }

    private boolean validateInput() {
        if (petNameField.getText().trim().isEmpty() || petSpeciesField.getText().trim().isEmpty() ||
                (petAgeYearsField.getText().trim().isEmpty() && petAgeMonthsField.getText().trim().isEmpty())) {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            int years = petAgeYearsField.getText().trim().isEmpty() ? 0 : Integer.parseInt(petAgeYearsField.getText().trim());
            int months = petAgeMonthsField.getText().trim().isEmpty() ? 0 : Integer.parseInt(petAgeMonthsField.getText().trim());

            if (years < 0 || years > 100 || months < 0 || months > 11) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid age (0-100 years, 0-11 months)", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void addPet() {
        if (!validateInput()) {
            return;
        }

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            conn.setAutoCommit(false); // Start transaction

            // Insert new pet
            String sqlInsertPet = "INSERT INTO pets (petname, age_years, age_months, species, pettype) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmtInsertPet = conn.prepareStatement(sqlInsertPet, Statement.RETURN_GENERATED_KEYS)) {
                pstmtInsertPet.setString(1, petNameField.getText().trim());
                pstmtInsertPet.setInt(2, Integer.parseInt(petAgeYearsField.getText().trim()));
                pstmtInsertPet.setInt(3, Integer.parseInt(petAgeMonthsField.getText().trim()));
                pstmtInsertPet.setString(4, petSpeciesField.getText().trim());
                pstmtInsertPet.setString(5, (String) petTypeComboBox.getSelectedItem());
                pstmtInsertPet.executeUpdate();

                int loginID = LoginScreen.ID;
                if(loginID != -1) {
                    Id = loginID;
                }

                String sqlUpdateVolunteer = "UPDATE volunteers SET pets_rescued = pets_rescued + 1 WHERE id = ?";
                try (PreparedStatement pstmtUpdateVolunteer = conn.prepareStatement(sqlUpdateVolunteer)) {
                    pstmtUpdateVolunteer.setInt(1, Id);
                    pstmtUpdateVolunteer.executeUpdate();
                }
            }

            conn.commit(); // Commit transaction
            JOptionPane.showMessageDialog(this, "Pet added successfully and volunteer's rescued pet count updated", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (SQLException ex) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback transaction on error
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding pet to database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Reset auto-commit to true
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void clearFields() {
        petTypeComboBox.setSelectedIndex(0);
        petNameField.setText("");
        petSpeciesField.setText("");
        petAgeYearsField.setText("");
        petAgeMonthsField.setText("");
    }

    private void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to logout?", "Confirm Logout",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            new LoginScreen().setVisible(true);
            dispose();
        }
    }

    private void goBack() {
        new VolunteerInterface().setVisible(true);
        dispose();
    }

    private void openShowPets() {
        new ShowPetsUIVolunteer().setVisible(true);
        dispose();
    }

    private void showAboutUs() {
        new AboutUsPageVolunteer().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new AddPetsUIVolunteer();
        });
    }
}
