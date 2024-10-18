package petCareApp.src.com.cse.ju.oop.views.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminInterface extends JFrame {
    private JPanel leftPanel, rightPanel, topPanel;
    private JButton logoutButton;
    private final Color PRIMARY_COLOR = new Color(65, 105, 225);
    private final Color SECONDARY_COLOR = new Color(100, 149, 237);
    private final Font HEADER_FONT = new Font("Arial", Font.BOLD, 28); // Larger header for a cleaner look
    private final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 18);

    public AdminInterface() {
        initializeFrame();
        createPanels();
        createTopPanel();
        createLeftPanel();
        createRightPanel();
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("PetCare Admin Dashboard");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private void createPanels() {
        GridBagConstraints gbc = new GridBagConstraints();

        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(PRIMARY_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        add(topPanel, gbc);

        leftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                int w = getWidth(), h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, PRIMARY_COLOR, w, h, SECONDARY_COLOR);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
                g2d.dispose();
            }
        };
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.25;
        gbc.weighty = 0.9;
        add(leftPanel, gbc);

        rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.75;
        gbc.weighty = 0.9;
        add(rightPanel, gbc);
    }

    private void createTopPanel() {
        JLabel titleLabel = new JLabel("Admin");
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 0));
        topPanel.add(titleLabel, BorderLayout.WEST);

        logoutButton = createStyledButton("Logout");
        logoutButton.setBackground(new Color(255, 69, 0)); // Red background
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFont(HEADER_FONT);
        logoutButton.setFocusPainted(false);
        logoutButton.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

        logoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutButton.setBackground(new Color(223, 31, 65)); // Darker red on hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutButton.setBackground(new Color(255, 69, 0)); // Original color
            }
        });

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.setOpaque(false);
        logoutPanel.add(logoutButton);
        topPanel.add(logoutPanel, BorderLayout.EAST);
    }

    private void createLeftPanel() {
        leftPanel.add(Box.createRigidArea(new Dimension(5, 20)));

        addMenuButton("Manage Pets", leftPanel, e -> openPetManagement());
        addMenuButton("Manage Volunteers", leftPanel, e -> openVolunteerManagement());
        addMenuButton("Manage Users", leftPanel, e -> openUserManagement());
        addMenuButton("About Us", leftPanel, e -> showAboutUs());

        leftPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Add space before logo

        // Add the logo below "About Us"
        try {
            // Load and resize the logo image
            ImageIcon logoIcon = new ImageIcon("C:\\Users\\HP\\Downloads\\logo.png"); // Replace with the actual logo path
            Image scaledLogo = logoIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));

            // Align the logo to the center
            logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            leftPanel.add(logoLabel);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load the logo.");
        }

        leftPanel.add(Box.createVerticalGlue());
    }

    private void createRightPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel welcomeLabel = new JLabel("Welcome, Admin!");
        welcomeLabel.setFont(HEADER_FONT);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Padding for better spacing
        rightPanel.add(welcomeLabel, gbc);

        String[] categories = {"Pets", "Volunteers", "Users", "Supplies"};
        for (String category : categories) {
            JPanel card = createDashboardCard(category);
            rightPanel.add(card, gbc);
        }
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(NORMAL_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(PRIMARY_COLOR);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Thin white border for a polished look
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(SECONDARY_COLOR);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(PRIMARY_COLOR);
            }
        });

        return button;
    }

    private void addMenuButton(String text, JPanel panel, ActionListener listener) {
        JButton button = createStyledButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Align buttons to the center
        button.setMaximumSize(new Dimension(220, 60)); // Increased size for a larger button
        button.addActionListener(listener);
        panel.add(button);
        panel.add(Box.createRigidArea(new Dimension(20, 20))); // Spacing between buttons
    }

    private JPanel createDashboardCard(String title) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(SECONDARY_COLOR, 2));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(NORMAL_FONT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        card.add(titleLabel, BorderLayout.NORTH);

        JLabel countLabel = new JLabel(String.valueOf((int) (Math.random() * 100)), SwingConstants.CENTER);
        countLabel.setFont(HEADER_FONT);
        card.add(countLabel, BorderLayout.CENTER);

        return card;
    }

    private void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to logout?", "Confirm Logout",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
            new LoginScreen().setVisible(true);
        }
    }

    private void openPetManagement() {
        SwingUtilities.invokeLater(() -> {
            PetManagementUI petManagementUI = new PetManagementUI();
            petManagementUI.setVisible(true);
            dispose();
        });
    }

    private void openVolunteerManagement() {
        SwingUtilities.invokeLater(() -> {
            VolunteerManagementUI volunteerManagementUI = new VolunteerManagementUI();
            volunteerManagementUI.setVisible(true);
            dispose();
        });
    }

    private void openUserManagement() {
        SwingUtilities.invokeLater(() -> {
            UserManagementUI userManagementUI = new UserManagementUI();
            userManagementUI.setVisible(true);
            dispose();
        });
    }

    private void showAboutUs() {
        SwingUtilities.invokeLater(() -> {
            AboutUsPageAdmin aboutUsPageAdmin = new AboutUsPageAdmin();
            aboutUsPageAdmin.setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminInterface::new);
    }
}
