package petCareApp.src.com.cse.ju.oop.views.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginScreen extends JFrame {
    private JTextField userText;
    private JPasswordField passText;
    private JComboBox<String> roleComboBox;
    private JPanel leftPanel, rightPanel;
    private JLabel logoLabel, welcomeLabel;
    private JButton loginButton, signUpButton;

    public LoginScreen() {
        setTitle("Login");
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

        // Left panel components
        try {
            ImageIcon logoIcon = new ImageIcon("C:\\Users\\HP\\Downloads\\logo.png"); // Update with the correct logo path
            Image img = logoIcon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
            logoLabel = new JLabel(new ImageIcon(img));
            gbc.gridx = 0;
            gbc.gridy = 0;
            leftPanel.add(logoLabel, gbc);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load the logo.");
        }

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
        loginButton.addActionListener(e -> handleLogin());
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

    private void handleLogin() {
        String username = userText.getText();
        String password = new String(passText.getPassword());
        String selectedRole = (String) roleComboBox.getSelectedItem();

        // Here you would typically validate the username and password
        // For this example, we'll just check if the Admin role is selected
        if ("Admin".equals(selectedRole)) {
            openAdminInterfaceWindow();
        }
        else if ("Volunteer".equals(selectedRole)) {
            openVolunteerInterfaceWindow();
        }
        else if ("User".equals(selectedRole)) {
            openUserInterfaceWindow();
        }
        else {
            JOptionPane.showMessageDialog(this, "Login functionality not implemented for " + selectedRole + " role.", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openAdminInterfaceWindow() {
        SwingUtilities.invokeLater(() -> {
            AdminInterface adminInterface = new AdminInterface();
            adminInterface.setVisible(true);
            this.dispose();
        });
    }

    private void openUserInterfaceWindow() {
        SwingUtilities.invokeLater(() -> {
            UserInterface userInterface = new UserInterface();
            userInterface.setVisible(true);
            this.dispose();
        });
    }

    private void openVolunteerInterfaceWindow() {
        SwingUtilities.invokeLater(() -> {
            VolunteerInterface volunteerInterface = new VolunteerInterface();
            volunteerInterface.setVisible(true);
            this.dispose();
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
