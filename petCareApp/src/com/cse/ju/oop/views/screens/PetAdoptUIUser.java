package petCareApp.src.com.cse.ju.oop.views.screens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.border.TitledBorder;

public class PetAdoptUIUser extends JFrame {
    private JPanel sidebarPanel, mainPanel, headerPanel, contentPanel;
    private JTable petTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> sortByComboBox;
    private JButton backButton;

    private final Color PRIMARY_COLOR = new Color(65, 105, 225);
    private final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private final Color TEXT_COLOR = new Color(44, 62, 80);
    private final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private final Font NORMAL_FONT = new Font("Segoe UI", Font.PLAIN, 18);
    private final Font SIDEBAR_FONT = new Font("Segoe UI", Font.BOLD, 20);

    public PetAdoptUIUser() {
        initializeFrame();
        createPanels();
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("PetCare - Adoption");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        logoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebarPanel.add(logoLabel);

        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        createFilterPanel();

        sidebarPanel.add(Box.createVerticalGlue());

        backButton = createMenuButton("Back", sidebarPanel, e -> handleBack());
        backButton.setBackground(new Color(231, 76, 60));
    }

    private void createFilterPanel() {
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBackground(PRIMARY_COLOR);

        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE),
                "Filters"
        );
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setTitleColor(Color.WHITE);
        titledBorder.setTitleFont(SIDEBAR_FONT);

        filterPanel.setBorder(titledBorder);

        addFilterSection(filterPanel, "Pet Type", new String[]{"Cat", "Dog", "Bird"});
        addFilterSection(filterPanel, "Age", new String[]{"0-9 months", "1-3 years", "4 years and above"});

        sidebarPanel.add(filterPanel);
    }

    private void addFilterSection(JPanel panel, String title, String[] options) {
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(NORMAL_FONT);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 0)));

        ButtonGroup group = new ButtonGroup();
        for (String option : options) {
            JRadioButton radioButton = new JRadioButton(option);
            radioButton.setFont(NORMAL_FONT);
            radioButton.setForeground(Color.WHITE);
            radioButton.setBackground(PRIMARY_COLOR);
            radioButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            group.add(radioButton);
            panel.add(radioButton);
            panel.add(Box.createRigidArea(new Dimension(0, 0)));
        }
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
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

        JLabel titleLabel = new JLabel("Pet Adoption");
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(TEXT_COLOR);
        headerPanel.add(titleLabel, BorderLayout.WEST);

        JLabel dateLabel = new JLabel("Today: " + java.time.LocalDate.now().toString());
        dateLabel.setFont(NORMAL_FONT);
        dateLabel.setForeground(TEXT_COLOR);
        headerPanel.add(dateLabel, BorderLayout.EAST);

    }

    private void createContentPanel() {
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(BACKGROUND_COLOR);
        contentPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        createPetTable();
        JScrollPane scrollPane = new JScrollPane(petTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 2));

        contentPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void createPetTable() {
        String[] columnNames = {"Pet ID", "Pet Name", "Age", "Species", "Pet Type"};
        tableModel = new DefaultTableModel(columnNames, 0);
        petTable = new JTable(tableModel);
        petTable.setFont(NORMAL_FONT);
        petTable.getTableHeader().setFont(SIDEBAR_FONT);
        petTable.setRowHeight(30);
        petTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        petTable.setAutoCreateRowSorter(true);

        // Sample data - in a real application, this would come from a database
        addPetToTable("1", "Bella", "2", "Bengal Dog", "Dog");
        addPetToTable("2", "Milo", "1", "Pussy Cat", "Cat");
        addPetToTable("3", "Charlie", "3", "American Dog", "Dog");
        addPetToTable("4", "Max", "5", "Russian Cat", "Cat");
    }

    private void addPetToTable(String id, String name, String age, String species, String type) {
        tableModel.addRow(new Object[]{id, name, age, species, type});
    }

    private JButton createMenuButton(String text, JPanel panel, ActionListener actionListener) {
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
        panel.add(button);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        return button;
    }

//    private void handleSort() {
//        String selectedOption = (String) sortByComboBox.getSelectedItem();
//        System.out.println("Sorting by: " + selectedOption);
//        // TODO: Implement actual sorting logic
//    }

    private void handleBack() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to go back to the dashboard?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            new UserInterface();
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
            new PetAdoptUIUser();
        });
    }
}