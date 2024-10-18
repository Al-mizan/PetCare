package petCareApp.src.com.cse.ju.oop.views.screens;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RegistrationScreen extends JFrame {
    private JTextField firstNameField, lastNameField, userNameField, phoneField, emailField;
    private JComboBox<String> genderComboBox, roleComboBox;
    private JButton signUpButton, backButton;
    private JPasswordField passwordField;

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/petCare_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "mysql@1234";
    public static int ID = -1;

    public RegistrationScreen() {
        setTitle("Pet Care - Sign Up");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        mainPanel.setBackground(new Color(240, 240, 240));

        // Top panel for back button and title
        JPanel topPanel = new JPanel(new BorderLayout(20, 20));
        topPanel.setOpaque(false);
        backButton = createStyledButton("← Back", new Color(100, 100, 100));
        topPanel.add(backButton, BorderLayout.WEST);

        JLabel titleLabel = new JLabel("Sign Up to Pet Care", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(new Color(65, 105, 225));
        topPanel.add(titleLabel, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, -100, 5, 50);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        addFormField(formPanel, gbc, "                             First Name", firstNameField = createStyledTextField());
        addFormField(formPanel, gbc, "                             Surname", lastNameField = createStyledTextField());
        addFormField(formPanel, gbc, "                             Gender", genderComboBox = createStyledComboBox(new String[]{"Male", "Female", "Other"}));
        addFormField(formPanel, gbc, "                             User ID", userNameField = createStyledTextField());
        addFormField(formPanel, gbc, "                             Phone", phoneField = createStyledTextField());
        addFormField(formPanel, gbc, "                             Email", emailField = createStyledTextField());
        addFormField(formPanel, gbc, "                             Password", passwordField = createStyledPasswordField());
        addFormField(formPanel, gbc, "                             Sign up as", roleComboBox = createStyledComboBox(new String[]{"User", "Admin", "Volunteer"}));

        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Bottom panel for the signup button and small logo
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        bottomPanel.setOpaque(false);

        // Add a small logo above the sign-up button
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\HP\\Downloads\\logo.png"); // Replace with actual path
        Image scaledLogo = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);  // Scale the image to a smaller size
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        bottomPanel.add(logoLabel);  // Add the small logo above the button

        signUpButton = createStyledButton("SIGN UP", new Color(65, 105, 225));
        signUpButton.setForeground(Color.WHITE);
        bottomPanel.add(signUpButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);

        signUpButton.addActionListener(e -> handleRegistration());
        backButton.addActionListener(e -> openSignInWindow());
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Arial", Font.PLAIN, 18));
        field.setPreferredSize(new Dimension(250, 35));
        return field;
    }

    private JPasswordField createStyledPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setFont(new Font("Arial", Font.PLAIN, 18));
        field.setPreferredSize(new Dimension(250, 35));
        return field;
    }

    private JComboBox<String> createStyledComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 18));
        comboBox.setPreferredSize(new Dimension(250, 35));
        return comboBox;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(150, 40));
        return button;
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, String labelText, JComponent field) {
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(label, gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);
    }

<<<<<<< HEAD
    private void handleLogin() {
        String selectedRole = (String) roleComboBox.getSelectedItem();

        if ("Admin".equals(selectedRole)) {
            try {
                openAdminInterfaceWindow();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error opening AdminInterface: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if ("Volunteer".equals(selectedRole)) {
            try {
                openVolunteerInterfaceWindow();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error opening VolunteerInterface: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if ("User".equals(selectedRole)) {
            try {
                openUserInterfaceWindow();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error opening UserInterface: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Login functionality not implemented for " + selectedRole + " role.", "Login Error", JOptionPane.ERROR_MESSAGE);
=======
    private void handleRegistration() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String gender = (String) genderComboBox.getSelectedItem();
        String userName = userNameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String selectedRole = (String) roleComboBox.getSelectedItem();

        // Validate input fields
        if (firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty() || password.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Registration Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Insert data into the appropriate table based on the selected role
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            assert selectedRole != null;
            String tableName = getTableNameForRole(selectedRole);
            String sql = "INSERT INTO " + tableName + " (username, firstname, surname, gender, phone, email, password) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, userName);
                pstmt.setString(2, firstName);
                pstmt.setString(3, lastName);
                pstmt.setString(4, gender);
                pstmt.setString(5, phone);
                pstmt.setString(6, email);
                pstmt.setString(7, password);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    LoginScreen.authenticateUserRegistration(userName, password, selectedRole);
                    openAppropriateInterface(selectedRole);
                } else {
                    JOptionPane.showMessageDialog(this, "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    ///////////

    private String getTableNameForRole(String role) {
        switch (role.toLowerCase()) {
            case "admin":
                return "admins";
            case "volunteer":
                return "volunteers";
            case "user":
                return "users";
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }
    }

    private void openAppropriateInterface(String role) {
        switch (role.toLowerCase()) {
            case "admin":
                openAdminInterfaceWindow();
                break;
            case "volunteer":
                openVolunteerInterfaceWindow();
                break;
            case "user":
                openUserInterfaceWindow();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid role selected.", "Error", JOptionPane.ERROR_MESSAGE);
>>>>>>> 86e0245188d2863aaf900ffd976a5e65dccb7bf8
        }
    }

    private void openAdminInterfaceWindow() {
        SwingUtilities.invokeLater(() -> {
            try {
                AdminInterface adminInterface = new AdminInterface();
                adminInterface.setVisible(true);
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
<<<<<<< HEAD
                UserInterface userInterface = new UserInterface();
=======
                System.out.println("Attempting to open AdminInterface");
                UserInterface userInterface = new UserInterface(); ///////////////////////////////////
>>>>>>> 86e0245188d2863aaf900ffd976a5e65dccb7bf8
                userInterface.setVisible(true);
                this.dispose();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error in openUserInterfaceWindow: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void openVolunteerInterfaceWindow() {
        SwingUtilities.invokeLater(() -> {
            try {
                VolunteerInterface volunteerInterface = new VolunteerInterface();
                volunteerInterface.setVisible(true);
                this.dispose();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error in openVolunteerInterfaceWindow: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void openSignInWindow() {
        new LoginScreen();
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegistrationScreen::new);
    }
}
