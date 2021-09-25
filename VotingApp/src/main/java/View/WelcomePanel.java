package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WelcomePanel extends JPanel {
    public WelcomePanel(JFrame mainFrame) {
        JLabel welcomeLabel = new JLabel("Welcome to the Voting App!");
        welcomeLabel.setSize(100, 100);

        JButton logInButton = new JButton("Log In");
        logInButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel logInPanel = new LogInPanel(mainFrame, WelcomePanel.this);
                mainFrame.add(logInPanel);
                WelcomePanel.super.setVisible(false);
            }
        });

        JButton registerButton = new JButton("Register");
        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel registerPanel = new RegisterPanel(mainFrame, WelcomePanel.this);
                mainFrame.add(registerPanel);
                WelcomePanel.super.setVisible(false);
            }
        });

        super.add(welcomeLabel);
        super.add(logInButton);
        super.add(registerButton);

        try {
            BufferedImage voteBanner = ImageIO.read(new File("src/main/java/Image/VoteBanner.jpg"));
            JLabel voteBannerLabel = new JLabel(new ImageIcon(voteBanner));
            super.add(voteBannerLabel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
