package petCareApp.src.com.cse.ju.oop.views.screens;

import javax.swing.*;
import java.awt.*;

public class AboutUsPageAdmin extends JFrame {
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 800;
    private static final int PADDING = 20;
    private static final String TITLE = "About Us - Pet Care Organization";
    private static final String FONT_FAMILY = "Arial";
    private static final Color TITLE_COLOR = new Color(34, 40, 49);

    public AboutUsPageAdmin() {
        initializeFrame();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
    }

    private void initializeFrame() {
        setTitle(TITLE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(PADDING, PADDING));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));

        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        mainPanel.add(createContentPanel(), BorderLayout.CENTER);
        mainPanel.add(createFooterPanel(), BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());

        // Create a sub-panel for the back button and title
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(createBackButton(), BorderLayout.WEST);

        JLabel titleLabel = new JLabel("About Us", JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        titleLabel.setForeground(TITLE_COLOR);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        headerPanel.add(topPanel, BorderLayout.NORTH);
        headerPanel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.SOUTH);
        return headerPanel;
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font(FONT_FAMILY, Font.PLAIN, 14));
        backButton.addActionListener(e -> {
            new AdminInterface();
            dispose();
        });
        return backButton;
    }

    private JScrollPane createContentPanel() {
        JTextArea aboutText = new JTextArea(getAboutUsText());
        aboutText.setFont(new Font(FONT_FAMILY, Font.PLAIN, 20));
        aboutText.setWrapStyleWord(true);
        aboutText.setLineWrap(true);
        aboutText.setEditable(false);
        aboutText.setBackground(null);
        aboutText.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JScrollPane scrollPane = new JScrollPane(aboutText);
        scrollPane.setBorder(null);
        return scrollPane;
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Padding around components

        // Add the logo to the left
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;  // Align logo to the top-left
        JLabel logoLabel = createLogoLabel();
        footerPanel.add(logoLabel, gbc);

        // Add "Contact Us" and details, aligned with the logo
        gbc.gridx = 1; // Place the text components to the right of the logo
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;  // Align the text to the left
        JPanel contactInfoPanel = createContactInfoPanel();
        footerPanel.add(contactInfoPanel, gbc);

        return footerPanel;
    }

    private JPanel createContactInfoPanel() {
        JPanel contactInfoPanel = new JPanel();
        contactInfoPanel.setLayout(new BoxLayout(contactInfoPanel, BoxLayout.Y_AXIS));

        contactInfoPanel.add(createStyledLabel("Contact Us", Font.BOLD, 24));
        contactInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contactInfoPanel.add(createStyledLabel("Email: petCare@gmail.com", Font.PLAIN, 18));
        contactInfoPanel.add(createStyledLabel("Phone: +880 1705-094855", Font.PLAIN, 18));
        contactInfoPanel.add(createStyledLabel("Address: CSE street, CSE, Jahangirnagar Universi", Font.PLAIN, 18));

        return contactInfoPanel;
    }

    private JLabel createLogoLabel() {
        // Load the original logo image
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\HP\\Downloads\\logo.png");

        // Resize the image to a smaller size (e.g., 100x100 pixels)
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledLogoIcon = new ImageIcon(scaledImage);

        // Create a JLabel with the scaled icon
        JLabel logoLabel = new JLabel(scaledLogoIcon);
        return logoLabel;
    }

    private JLabel createStyledLabel(String text, int style, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(FONT_FAMILY, style, size));
        return label;
    }

    private String getAboutUsText() {
        return "Pet Care is a community-driven organization dedicated to the well-being and " +
                "rescue of animals in need. Founded in 2024, we have been at the forefront of " +
                "animal welfare efforts, providing care, rehabilitation, and adoption services " +
                "to stray and abandoned animals.\n\n" +
                "Our mission is to create a safe, loving environment for all animals and to " +
                "promote awareness about the importance of animal welfare. With a dedicated team " +
                "of volunteers, veterinarians, and animal lovers, we work tirelessly to rescue " +
                "animals in distress, provide medical attention, and find them forever homes.\n\n" +
                "At Pet Care, we believe every animal deserves a second chance, and we are " +
                "committed to making this a reality. " +
                "We actively engage with the community through educational programs and outreach " +
                "initiatives to encourage responsible pet ownership. By fostering partnerships " +
                "with local businesses and organizations, we aim to create a sustainable network " +
                "of support for both animals and their human caregivers. Together, we envision a " +
                "future where every animal is treated with dignity and respect, and where compassion " +
                "leads the way in our communities.";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AboutUsPageAdmin aboutUsPageAdmin = new AboutUsPageAdmin();
            aboutUsPageAdmin.setVisible(true);
        });
    }
}
