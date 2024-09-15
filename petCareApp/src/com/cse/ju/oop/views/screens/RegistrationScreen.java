package petCareApp.src.com.cse.ju.oop.views.screens;

import javax.swing.*;
import java.awt.*;

public class RegistrationScreen extends JFrame {
    private JTextField firstNameField, lastNameField, userNameField, passwordField, phoneField, emailField;
    private JComboBox<String> genderComboBox;
    private JButton signUpButton, backButton;

    public RegistrationScreen() {
        setTitle("Customer Sign Up");
        setSize(600, 400);  // Adjusted size to match the screenshot
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top panel for back button and spacing
        JPanel topPanel = new JPanel(new BorderLayout());
        backButton = new JButton("BACK");
        topPanel.add(backButton, BorderLayout.WEST);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Form panel for the input fields
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // First name
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("First Name"), gbc);
        firstNameField = new JTextField();
        firstNameField.setText("                                        ");
        gbc.gridx = 1;
        formPanel.add(firstNameField, gbc);

        // Last name
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Last Name"), gbc);
        lastNameField = new JTextField();
        gbc.gridx = 1;
        formPanel.add(lastNameField, gbc);

        // Gender
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Gender"), gbc);
        genderComboBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        gbc.gridx = 1;
        formPanel.add(genderComboBox, gbc);

        // Username
        gbc.gridx = 2;
        gbc.gridy = 0;
        formPanel.add(new JLabel("User Name"), gbc);
        userNameField = new JTextField();
        gbc.gridx = 3;
        formPanel.add(userNameField, gbc);

        // Password
        gbc.gridx = 2;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Password"), gbc);
        passwordField = new JPasswordField();
        gbc.gridx = 3;
        formPanel.add(passwordField, gbc);

        // Phone
        gbc.gridx = 2;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Phone"), gbc);
        phoneField = new JTextField();
        phoneField.setText("                                        ");
        gbc.gridx = 3;
        formPanel.add(phoneField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Email"), gbc);
        emailField = new JTextField();
        gbc.gridx = 1;
        gbc.gridwidth = 3;  // Span across both columns
        formPanel.add(emailField, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Bottom panel for the signup button
        JPanel bottomPanel = new JPanel(new BorderLayout());

        signUpButton = new JButton("SIGNUP");
        signUpButton.setBackground(new Color(65, 105, 225));
        signUpButton.setForeground(Color.WHITE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(signUpButton);
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);

        // "SIGN UP TO PETS CARE" label
        JLabel petsCareLabel = new JLabel("SIGN UP TO PETS CARE", SwingConstants.CENTER);
        petsCareLabel.setForeground(Color.LIGHT_GRAY);
        petsCareLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Padding for label
        bottomPanel.add(petsCareLabel, BorderLayout.SOUTH);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegistrationScreen::new);
    }
}
