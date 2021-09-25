package View;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Voting Application");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(700, 760);
        setLocationRelativeTo(null);
        setBounds(300, 90, 900, 600);
        JPanel welcomePanel = new WelcomePanel(this);
        super.add(welcomePanel);
    }
}
