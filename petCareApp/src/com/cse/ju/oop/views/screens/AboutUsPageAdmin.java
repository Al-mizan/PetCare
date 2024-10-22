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
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding around components

        // Add the logo to the left
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon("C://Users//HP//Downloads//logo.png"); // Replace with your logo path
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Adjust size as necessary
        logoLabel.setIcon(new ImageIcon(scaledImage));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3; // Span the height of the contact info
        gbc.anchor = GridBagConstraints.WEST; // Align to the left
        footerPanel.add(logoLabel, gbc);

        // Add the "Contact Us" label and contact details to the right of the logo
        gbc.gridheight = 1; // Reset grid height for contact info
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST; // Align the contact info with the top of the logo
        footerPanel.add(createStyledLabel("Contact Us", Font.BOLD, 24), gbc);

        gbc.gridy = 1;
        footerPanel.add(createStyledLabel("Email: petCare@gmail.com", Font.PLAIN, 18), gbc);

        gbc.gridy = 2;
        footerPanel.add(createStyledLabel("Phone: +880 1705-094855", Font.PLAIN, 18), gbc);

        gbc.gridy = 3;
        footerPanel.add(createStyledLabel("Address: CSE street, CSE, Jahangirnagar University", Font.PLAIN, 18), gbc);

        return footerPanel;
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
