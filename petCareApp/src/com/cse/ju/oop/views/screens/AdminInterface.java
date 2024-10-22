package petCareApp.src.com.cse.ju.oop.views.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminInterface extends JFrame {
    private JPanel leftPanel, rightPanel, topPanel;
    private JButton logoutButton;
    private final Color PRIMARY_COLOR = new Color(65, 105, 225);
    private final Color SECONDARY_COLOR = new Color(100, 149, 237);
    private final Font HEADER_FONT = new Font("Arial", Font.BOLD, 26);
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
        setLocationRelativeTo(null);
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
        gbc.fill = GridBagConstraints.BOTH; ////////
        gbc.weightx = 0.25; // 1/3 of the total width
        gbc.weighty = 0.9;
        add(leftPanel, gbc);

        rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.75; // 2/3 of the total width
        gbc.weighty = 0.9;
        add(rightPanel, gbc);
    }
////////////////////////////////
private void createTopPanel() {
    JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Use FlowLayout to manage components
    titlePanel.setOpaque(false); // Make the panel transparent
//
    // Load the logo image
    ImageIcon logoIcon = new ImageIcon("C://Users//HP//Downloads//logo.png"); // Update the path as needed
    JLabel logoLabel = new JLabel(logoIcon);

    // Optionally, you can scale the image to a desired size
    Image logoImage = logoIcon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH); // Scale to 50x50 pixels
    logoLabel.setIcon(new ImageIcon(logoImage));

    titlePanel.add(logoLabel); // Add logo to the panel
    titlePanel.add(Box.createHorizontalStrut(0)); // Add space between logo and title
    //

    titlePanel.add(Box.createHorizontalStrut(0)); // Adjust the width as needed
    JLabel titleLabel = new JLabel("Admin");
    titleLabel.setFont(HEADER_FONT);
    titleLabel.setForeground(Color.WHITE);
    titlePanel.add(titleLabel);

    topPanel.add(titlePanel, BorderLayout.WEST);

    logoutButton = createStyledButton("Logout");
    logoutButton.setBackground(new Color(255, 69, 0)); // Red background
    logoutButton.setForeground(Color.WHITE);          // White text
    logoutButton.setFont(HEADER_FONT);
    logoutButton.setFocusPainted(false);
    logoutButton.setBorder(BorderFactory.createLineBorder(Color.RED, 2)); // Red border

    // Add hover effect
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

    logoutButton.addActionListener(e -> handleLogout());

    JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    logoutPanel.setOpaque(false);
    logoutPanel.add(logoutButton);
    topPanel.add(logoutPanel, BorderLayout.EAST);
}

    ////////////////////
    private void createLeftPanel() {
        leftPanel.add(Box.createRigidArea(new Dimension(5, 20)));
        addMenuButton("Manage Pets", leftPanel, e -> openPetManagement());
        addMenuButton("Manage Volunteers", leftPanel, e -> openVolunteerManagement());
        addMenuButton("Manage Users", leftPanel, e -> openUserManagement());
        addMenuButton("About Us", leftPanel, e -> showAboutUs());

        leftPanel.add(Box.createVerticalGlue());
    }

    private void createRightPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        JLabel welcomeLabel = new JLabel("Welcome, Admin!");
        welcomeLabel.setFont(HEADER_FONT);
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
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void addMenuButton(String text, JPanel panel, ActionListener listener) {
        JButton button = createStyledButton(text);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(10000, 10000));
        button.addActionListener(listener);
        panel.add(button);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
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

    private JPanel createDashboardCard(String title) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(SECONDARY_COLOR, 2));

        JLabel titleLabel = new JLabel(title,SwingConstants.CENTER); ///////////
        titleLabel.setFont(NORMAL_FONT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        card.add(titleLabel, BorderLayout.NORTH);

        JLabel countLabel = new JLabel(String.valueOf((int)(Math.random() * 100)));
        countLabel.setFont(HEADER_FONT);
        countLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(countLabel, BorderLayout.CENTER);

        return card;
    }

    private void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to logout?", "Confirm Logout",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // TODO: Implement actual logout logic
            System.out.println("Logout");
            this.dispose();
            new LoginScreen().setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminInterface::new);
    }
}