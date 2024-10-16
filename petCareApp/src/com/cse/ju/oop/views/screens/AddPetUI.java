package petCareApp.src.com.cse.ju.oop.views.screens;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AddPetUI extends JFrame{
    private JFrame frame;
    private JComboBox<String> petTypeComboBox;
    private JTextField petNameField;
    private JTextField petSpeciesField;
    private JTextField petAgeField;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/petcare_db";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    public AddPetUI() {
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("PetCare - Add Pet");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLayout(new BorderLayout());

        frame.add(createTopPanel(), BorderLayout.NORTH);
        frame.add(createFormPanel(), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel volunteerLabel = new JLabel("Volunteer", JLabel.LEFT);
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> logout());

        topPanel.add(volunteerLabel, BorderLayout.WEST);
        topPanel.add(logoutButton, BorderLayout.EAST);
        return topPanel;
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[] petTypes = {"Dog", "Cat", "Bird"};
        petTypeComboBox = new JComboBox<>(petTypes);
        petNameField = new JTextField(10);
        petSpeciesField = new JTextField(10);
        petAgeField = new JTextField(10);

        addFormField(formPanel, gbc, "Pet Type:", petTypeComboBox, 0);
        addFormField(formPanel, gbc, "Pet Name:", petNameField, 1);
        addFormField(formPanel, gbc, "Pet Species:", petSpeciesField, 2);
        addFormField(formPanel, gbc, "Pet Age:", petAgeField, 3);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addPet());
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> goBack());

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(addButton, gbc);
        gbc.gridx = 1;
        formPanel.add(backButton, gbc);

        return formPanel;
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, String labelText, JComponent component, int gridy) {
        gbc.gridx = 0;
        gbc.gridy = gridy;
        panel.add(new JLabel(labelText), gbc);
        gbc.gridx = 1;
        panel.add(component, gbc);
    }

    private void addPet() {
        String petType = (String) petTypeComboBox.getSelectedItem();
        String petName = petNameField.getText();
        String petSpecies = petSpeciesField.getText();
        String petAge = petAgeField.getText();

        if (petName.isEmpty() || petSpecies.isEmpty() || petAge.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO pets (type, name, species, age) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, petType);
                pstmt.setString(2, petName);
                pstmt.setString(3, petSpecies);
                pstmt.setInt(4, Integer.parseInt(petAge));
                pstmt.executeUpdate();
            }
            JOptionPane.showMessageDialog(frame, "Pet added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error adding pet to database", "Database Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid age", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        petTypeComboBox.setSelectedIndex(0);
        petNameField.setText("");
        petSpeciesField.setText("");
        petAgeField.setText("");
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to logout?", "Confirm Logout",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            // TODO: Implement actual logout logic
            System.out.println("Logout");
            new LoginScreen().setVisible(true);
            dispose();
        }
    }

    private void goBack() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to go back to the dashboard?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            new UserInterface();
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddPetUI::new);
    }
}
