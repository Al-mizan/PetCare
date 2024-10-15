package petCareApp.src.com.cse.ju.oop.views.screens;

import javax.swing.*;
import java.awt.*;

public class AboutUsPage extends JFrame {

    public AboutUsPage() {
        // Set up the frame
        setTitle("About Us - Pet Care Organization");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding for outer layout

        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("About Us", JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        titleLabel.setForeground(new Color(34, 40, 49)); // Dark color for the title
        headerPanel.add(titleLabel, BorderLayout.NORTH);

        // Add a separator line below the title for a cleaner look
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        headerPanel.add(separator, BorderLayout.SOUTH);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Content panel (About Us text)
        JTextArea aboutText = new JTextArea(
                "Pet Care is a community-driven organization dedicated to the well-being and " +
                        "rescue of animals in need. Founded in 2010, we have been at the forefront of " +
                        "animal welfare efforts, providing care, rehabilitation, and adoption services " +
                        "to stray and abandoned animals.\n\n" +
                        "Our mission is to create a safe, loving environment for all animals and to " +
                        "promote awareness about the importance of animal welfare. With a dedicated team " +
                        "of volunteers, veterinarians, and animal lovers, we work tirelessly to rescue " +
                        "animals in distress, provide medical attention, and find them forever homes.\n\n" +
                        "At Pet Care, we believe every animal deserves a second chance, and we are " +
                        "committed to making this a reality." +
                        "We actively engage with the community through educational programs and outreach " +
                        "initiatives to encourage responsible pet ownership. By fostering partnerships " +
                        "with local businesses and organizations, we aim to create a sustainable network " +
                        "of support for both animals and their human caregivers. Together, we envision a " +
                        "future where every animal is treated with dignity and respect, and where compassion " +
                        "leads the way in our communities."
        );
        aboutText.setFont(new Font("Arial", Font.PLAIN, 20));
        aboutText.setWrapStyleWord(true);
        aboutText.setLineWrap(true);
        aboutText.setEditable(false);
        aboutText.setBackground(null); // No background color for professional look
        aboutText.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Add content text area with scroll bar
        JScrollPane scrollPane = new JScrollPane(aboutText);
        scrollPane.setBorder(null); // No border for the scroll pane
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Footer panel (Contact Information)
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Space at the top

        JLabel contactUsLabel = new JLabel("Contact Us");
        contactUsLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        contactUsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emailLabel = new JLabel("Email: petCare@gmail.com");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel phoneLabel = new JLabel("Phone: +880 1705-094855");
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        phoneLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel addressLabel = new JLabel("Address: CSE street, CSE, Jahangirnagar University, Dhaka");
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        addressLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        footerPanel.add(contactUsLabel);
        footerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        footerPanel.add(emailLabel);
        footerPanel.add(phoneLabel);
        footerPanel.add(addressLabel);

        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);
    }

    public static void main(String[] args) {
        // Create and display the About Us page
        SwingUtilities.invokeLater(() -> {
            AboutUsPage aboutUsPage = new AboutUsPage();
            aboutUsPage.setVisible(true);
        });
    }
}
