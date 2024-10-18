package petCareApp.src.com.cse.ju.oop.views.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginScreen extends JFrame {
    private JTextField userText;
    private JPasswordField passText;
    private JComboBox<String> roleComboBox;
    private JPanel leftPanel, rightPanel;
    private JLabel logoLabel, welcomeLabel;
    private JButton loginButton, signUpButton;

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/petCare_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "mysql@1234";
    public static int ID = -1;

    public LoginScreen() {
        setTitle("Pet Care Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        createPanels();
        createLoginComponents();

        setVisible(true);
    }

    private void createPanels() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;

        leftPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                int w = getWidth(), h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, new Color(65, 105, 225), w, h, new Color(100, 149, 237));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
                g2d.dispose();
            }
        };
        gbc.gridx = 0;
        gbc.weightx = 0.25; // 25% of the total width
        add(leftPanel, gbc);

        rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.WHITE);
        gbc.gridx = 1;
        gbc.weightx = 0.75; // 75% of the total width
        add(rightPanel, gbc);
    }

    private void createLoginComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel brandLabel = new JLabel("PetCare");
        brandLabel.setFont(new Font("Arial", Font.BOLD, 40));
        brandLabel.setForeground(Color.WHITE);
        gbc.gridy = 1;
        leftPanel.add(brandLabel, gbc);

        JLabel sloganLabel = new JLabel("Care for your furry friends");
        sloganLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        sloganLabel.setForeground(Color.WHITE);
        gbc.gridy = 2;
        leftPanel.add(sloganLabel, gbc);

        // Right panel components
        welcomeLabel = new JLabel("Welcome Back!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 36));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        rightPanel.add(welcomeLabel, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;

        userText = createStyledTextField("Username");
        gbc.gridy = 1;
        rightPanel.add(userText, gbc);

        passText = createStyledPasswordField("Password");
        gbc.gridy = 2;
        rightPanel.add(passText, gbc);

        String[] roles = {"User", "Admin", "Volunteer"};
        roleComboBox = new JComboBox<>(roles);
        styleComboBox(roleComboBox);
        gbc.gridy = 3;
        rightPanel.add(roleComboBox, gbc);

        loginButton = createStyledButton("Login", new Color(65, 105, 225));
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rightPanel.add(loginButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel label = new JLabel("                             Or,");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        rightPanel.add(label, gbc);
        gbc.gridx = 1;

        signUpButton = createStyledButton("Create Account", new Color(80, 112, 211));
        gbc.gridx = 0;
        gbc.gridy = 6;
        rightPanel.add(signUpButton, gbc);

        // Event listeners
        loginButton.addActionListener(e -> {
            try {
                handleLogin();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        signUpButton.addActionListener(e -> openSignUpWindow());
    }

    private JTextField createStyledTextField(String placeholder) {
        JTextField textField = new JTextField(20);
        textField.setPreferredSize(new Dimension(300, 50));
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        addPlaceholderStyle(textField, placeholder);
        return textField;
    }

    private JPasswordField createStyledPasswordField(String placeholder) {
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(300, 50));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        addPlaceholderStyle(passwordField, placeholder);
        return passwordField;
    }

    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setPreferredSize(new Dimension(325, 50));
        comboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(300, 60));
        button.setFont(new Font("Arial", Font.BOLD, 18)); // Increased font size
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void addPlaceholderStyle(JTextField textField, String placeholder) {
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }

    public static void authenticateUserRegistration(String username, String password, String role) throws ClassNotFoundException {
        String tableName = role.toLowerCase() + "s"; // Assumes tables are named: admins, users, volunteers
        String query = "SELECT * FROM " + tableName + " WHERE username = ? AND password = ?";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt1 = conn.prepareStatement(query)) {
            System.out.println("Successfully go into mysql");
            pstmt1.setString(1, username);
            pstmt1.setString(2, password); // Note: In a real application, you should use hashed passwords

            try (ResultSet rs = pstmt1.executeQuery()) {

                if (rs.next()) {
                    ID = rs.getInt("id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleLogin() throws ClassNotFoundException {
        String username = userText.getText();
        String password = new String(passText.getPassword());
        String selectedRole = (String) roleComboBox.getSelectedItem();

        // Validate input fields
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Login Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (authenticateUser(username, password, selectedRole)) {
            switch (selectedRole) {
                case "Admin":
                    try {
                        openAdminInterfaceWindow();
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error opening AdminInterface: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "User":
                    try {
                        openUserInterfaceWindow();
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error opening AdminInterface: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "Volunteer":
                    try {
                        openVolunteerInterfaceWindow();
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error opening AdminInterface: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Invalid role selected.", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password for the selected role.", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean authenticateUser(String username, String password, String role) throws ClassNotFoundException {
        String tableName = role.toLowerCase() + "s"; // Assumes tables are named: admins, users, volunteers
        String query = "SELECT * FROM " + tableName + " WHERE username = ? AND password = ?";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            System.out.println("Successfully go into mysql");
            pstmt.setString(1, username);
            pstmt.setString(2, password); // Note: In a real application, you should use hashed passwords

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    ID = rs.getInt("id");
                    return true;
                }
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void openAdminInterfaceWindow() {
        SwingUtilities.invokeLater(() -> {
            try {
                System.out.println("Attempting to open AdminInterface");
                AdminInterface adminInterface = new AdminInterface();
                adminInterface.setVisible(true);
                System.out.println("AdminInterface opened successfully");
                this.dispose();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error in openAdminInterfaceWindow: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    private void openUserInterfaceWindow() {
        SwingUtilities.invokeLater(() -> {
            try {
                System.out.println("Attempting to open AdminInterface");
                UserInterface userInterface = new UserInterface();
                userInterface.setVisible(true);
                System.out.println("UserInterface opened successfully");
                this.dispose();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error in open UserInterface Window: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    private void openVolunteerInterfaceWindow() {
        SwingUtilities.invokeLater(() -> {
            try {
                System.out.println("Attempting to open VolunteerInterface");
                VolunteerInterface volunteerInterface = new VolunteerInterface();
                volunteerInterface.setVisible(true);
                System.out.println("VolunteerInterface opened successfully");
                this.dispose();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error in open VolunteerInterface Window: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void openSignUpWindow() {
        new RegistrationScreen();
        dispose();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(LoginScreen::new);
    }
}