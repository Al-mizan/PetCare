package petCareApp.src.com.cse.ju.oop.views.screens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserInterface extends JFrame {
    private JPanel sidebarPanel, mainPanel, headerPanel, contentPanel;
    private final Color PRIMARY_COLOR = new Color(65, 105, 225);
    private final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private final Color TEXT_COLOR = new Color(44, 62, 80);
    private final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private final Font NORMAL_FONT = new Font("Segoe UI", Font.PLAIN, 18);
    private final Font SIDEBAR_FONT = new Font("Segoe UI", Font.BOLD, 20);

    public UserInterface() {
        initializeFrame();
        createPanels();
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("PetCare User Dashboard");
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

        JLabel titleLabel = new JLabel("PetCare");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebarPanel.add(titleLabel);

        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        createMenuButton("Dashboard", sidebarPanel, e -> {/* Dashboard action */});
        createMenuButton("Pet Adopt", sidebarPanel, e -> openPetAdopt());
        createMenuButton("Add Pets", sidebarPanel, e -> openAddPet());
        createMenuButton("About Us", sidebarPanel, e -> showAboutUs());

        sidebarPanel.add(Box.createVerticalGlue());

        // Add logo above logout button
        JLabel logoLabel = new JLabel(new ImageIcon(new ImageIcon("/home/almizan/PetCare/petCareApp/src/com/cse/ju/oop/views/screens/logo.png")
                .getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH)));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebarPanel.add(logoLabel);

        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacing before logout

        JButton logoutButton = createMenuButton("Logout", sidebarPanel, e -> handleLogout());
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

        JLabel titleLabel = new JLabel("User Dashboard");
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
        contentPanel.setBorder(new EmptyBorder(30, 10, 30, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 20, 10);
        gbc.weightx = 1;

        String[] cardTitles = {"Pet Adopt", "Pet Add"};
        for (String title : cardTitles) {
            JPanel card = createDashboardCard(title);
            contentPanel.add(card, gbc);
        }
    }

    private JButton createMenuButton(String text, JPanel sidebarPanel, ActionListener actionListener) {
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
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        return button;
    }

    private void openPetAdopt() {
        SwingUtilities.invokeLater(() -> {
            PetAdoptUIUser petAdoptUIUser = new PetAdoptUIUser();
            petAdoptUIUser.setVisible(true);
            dispose();
        });
    }

    private void openAddPet() {
        SwingUtilities.invokeLater(() -> {
            AddPetUIUser addPetUIUser = new AddPetUIUser();
            addPetUIUser.setVisible(true);
            dispose();
        });
    }

    private void showAboutUs() {
        SwingUtilities.invokeLater(() -> {
            AboutUsPageUser aboutUsPageUser = new AboutUsPageUser(); // create new window for userInterface
            aboutUsPageUser.setVisible(true);
            dispose();
        });
    }

    private JPanel createDashboardCard(String title) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 6));

        // Title with larger, bold font
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        card.add(titleLabel, BorderLayout.NORTH);

        // Update the content for each title
        String contentText;
        if (title.equals("Pet Adopt")) {
            contentText = "<html><div style='font-size: 12px; line-height: 1.5;'>" +
                    "Explore our wonderful pets available for adoption!<br>" +
                    "Adopting a pet is a rewarding experience that can change your life.<br>" +
                    "Click the button on left to view available pets and start the adoption process!</div></html>";
        } else if (title.equals("Pet Add")) {
            contentText = "<html><div style='font-size: 12px; line-height: 1.5;'>" +
                    "Want to add a new pet to the system?<br>" +
                    "Please fill out the details of your pet including species, name, and age.<br>" +
                    "Click the button on left side to begin the registration process!</div></html>";
        } else {
            contentText = "No content available.";
        }

        JLabel contentLabel = new JLabel(contentText);
        contentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentLabel.setBorder(new EmptyBorder(30, 1, 30, 1));
        contentLabel.setVerticalAlignment(SwingConstants.TOP);
        card.add(contentLabel, BorderLayout.CENTER);

        return card;
    }

    private void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to logout?", "Confirm Logout",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            // TODO: Implement actual logout logic
            System.out.println("Logout");
            new LoginScreen().setVisible(true);
            this.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new UserInterface();
        });
    }
}
