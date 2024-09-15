package petCareApp.src.com.cse.ju.oop.views.screens;
//package com.cse.ju.oop.views.screens;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class LoginScreen extends JFrame {
    private JTextField userText;
    private JPasswordField passText;
    private JComboBox<String> roleComboBox;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel logoText;
    private JLabel loginLabel;
    private JButton loginButton;
    private JButton clearButton;
    private JLabel signUpLabel;
    private double initialWidth;
    private double initialHeight;

    public LoginScreen() {
        setTitle("Pet Care");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        initialWidth = getWidth();
        initialHeight = getHeight();
        setLayout(new GridBagLayout());

        createPanels();
        createLoginComponents();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                rescaleComponents();
            }
        });

        setVisible(true);
    }

    private void createPanels() {
        GridBagConstraints gbc = new GridBagConstraints();

        leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(new Color(230, 230, 255));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(leftPanel, gbc);

        rightPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        add(rightPanel, gbc);
    }

    private void createLoginComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Logo
        String logoPath = "C:/Users/HP/Downloads/pet CARE.png";
        ImageIcon originalIcon = new ImageIcon(logoPath);
        Image scaledImage = originalIcon.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(scaledIcon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        leftPanel.add(logoLabel, gbc);

        // Logo text
        logoText = new JLabel("PetsCare", JLabel.CENTER);
        logoText.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridy = 1;
        leftPanel.add(logoText, gbc);

        // Login components
        gbc.anchor = GridBagConstraints.WEST;

        loginLabel = new JLabel("LOGIN");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        rightPanel.add(loginLabel, gbc);

        JLabel userLabel = new JLabel("USERNAME:");
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        rightPanel.add(userLabel, gbc);

        userText = new JTextField(15);
        gbc.gridx = 1;
        rightPanel.add(userText, gbc);

        JLabel passLabel = new JLabel("PASSWORD:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        rightPanel.add(passLabel, gbc);

        passText = new JPasswordField(15);
        gbc.gridx = 1;
        rightPanel.add(passText, gbc);

        JLabel roleLabel = new JLabel("LOGIN AS:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        rightPanel.add(roleLabel, gbc);

        String[] roles = {"Admin", "User"};
        roleComboBox = new JComboBox<>(roles);
        gbc.gridx = 1;
        rightPanel.add(roleComboBox, gbc);

        loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 4;
        rightPanel.add(loginButton, gbc);

        clearButton = new JButton("Clear");
        gbc.gridx = 1;
        rightPanel.add(clearButton, gbc);

        signUpLabel = new JLabel("SIGN UP");
        signUpLabel.setForeground(Color.BLUE);
        signUpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        rightPanel.add(signUpLabel, gbc);

        clearButton.addActionListener(e -> {
            userText.setText("");
            passText.setText("");
        });
    }

    private void rescaleComponents() {
        double widthRatio = getWidth() / initialWidth;
        double heightRatio = getHeight() / initialHeight;
        double scaleFactor = Math.min(widthRatio, heightRatio);

        Font logoFont = logoText.getFont().deriveFont((float) (18 * scaleFactor));
        logoText.setFont(logoFont);

        Font loginFont = loginLabel.getFont().deriveFont((float) (18 * scaleFactor));
        loginLabel.setFont(loginFont);

        rescaleComponentFont(userText, scaleFactor);
        rescaleComponentFont(passText, scaleFactor);
        rescaleComponentFont(roleComboBox, scaleFactor);
        rescaleComponentFont(loginButton, scaleFactor);
        rescaleComponentFont(clearButton, scaleFactor);
        rescaleComponentFont(signUpLabel, scaleFactor);

        Component[] components = rightPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JLabel) {
                rescaleComponentFont(comp, scaleFactor);
            }
        }

        revalidate();
        repaint();
    }

    private void rescaleComponentFont(Component component, double scaleFactor) {
        Font currentFont = component.getFont();
        Font newFont = currentFont.deriveFont((float) (currentFont.getSize() * scaleFactor));
        component.setFont(newFont);
    }
}