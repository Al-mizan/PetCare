package petCareApp.src.com.cse.ju.oop.views.screens;

import javax.swing.*;
import java.awt.*;

public class RegistrationScreen extends JFrame {
    private JTextField firstNameField, lastNameField, userNameField, passwordField, phoneField, emailField;
    private JComboBox<String> genderComboBox, roleComboBox;
    private JButton signUpButton, backButton;

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

        signUpButton.addActionListener(e -> handleLogin());
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
                UserInterface userInterface = new UserInterface();
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
