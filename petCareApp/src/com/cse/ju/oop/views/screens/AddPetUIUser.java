package petCareApp.src.com.cse.ju.oop.views.screens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class AddPetUIUser extends JFrame {
    private JPanel sidebarPanel, mainPanel, headerPanel, contentPanel;
    private JButton logoutButton;
    private JComboBox<String> petTypeComboBox;
    private JTextField petNameField, petSpeciesField, petAgeField;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/petcare_db";
    private static final String DB_USER = "your_username"; // Replace with your DB username
    private static final String DB_PASSWORD = "your_password"; // Replace with your DB password

    private static final Color PRIMARY_COLOR = new Color(65, 105, 225);
    private static final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private static final Color TEXT_COLOR = new Color(44, 62, 80);
    private static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private static final Font NORMAL_FONT = new Font("Segoe UI", Font.PLAIN, 18);
    private static final Font SIDEBAR_FONT = new Font("Segoe UI", Font.BOLD, 20);

    public AddPetUIUser() {
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

    private void createSidebarPanel() {
        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(PRIMARY_COLOR);
        sidebarPanel.setPreferredSize(new Dimension(250, getHeight()));
        sidebarPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

        JLabel logoLabel = new JLabel("PetCare");
        logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebarPanel.add(logoLabel);

        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        createMenuButton("Dashboard", e -> goBack());
        createMenuButton("Add Pet", e -> {/* Current page */});
        createMenuButton("Pet Adopt", e -> openPetAdopt());
        createMenuButton("About Us", e -> showAboutUs());

        sidebarPanel.add(Box.createVerticalGlue());

        // Load and resize the logo
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\HP\\Downloads\\logo.png"); // Load your logo here
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Adjust size as needed
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel logoImageLabel = new JLabel(resizedIcon);
        logoImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebarPanel.add(logoImageLabel);

        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Space between logo and buttons

        logoutButton = createMenuButton("Logout", e -> handleLogout());
        logoutButton.setBackground(new Color(231, 76, 60));
    }

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
        petAgeField = new JTextField(15);

        addFormField(formPanel, gbc, "Pet Type:", petTypeComboBox, 0);
        addFormField(formPanel, gbc, "Pet Name:", petNameField, 1);
        addFormField(formPanel, gbc, "Pet Species:", petSpeciesField, 2);
        addFormField(formPanel, gbc, "Pet Age:", petAgeField, 3);

        return formPanel;
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
            ((JTextField) component).setFont(NORMAL_FONT);
        } else if (component instanceof JComboBox) {
            ((JComboBox<?>) component).setFont(NORMAL_FONT);
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
        return button;
    }

    private void goBack() {
        // Implement your logic for going back to the previous screen
    }

    private void openPetAdopt() {
        // Implement your logic for opening the Pet Adopt screen
    }

    private void showAboutUs() {
        // Implement your logic for showing the About Us screen
    }

    private void handleLogout() {
        // Implement your logout logic here
        System.exit(0); // For demonstration purposes
    }

    private void addPet() {
        String petType = (String) petTypeComboBox.getSelectedItem();
        String petName = petNameField.getText();
        String petSpecies = petSpeciesField.getText();
        String petAge = petAgeField.getText();

        if (petName.isEmpty() || petSpecies.isEmpty() || petAge.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO pets (type, name, species, age) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, petType);
            statement.setString(2, petName);
            statement.setString(3, petSpecies);
            statement.setString(4, petAge);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Pet added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding pet: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        petTypeComboBox.setSelectedIndex(0);
        petNameField.setText("");
        petSpeciesField.setText("");
        petAgeField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddPetUIUser());
    }
}
