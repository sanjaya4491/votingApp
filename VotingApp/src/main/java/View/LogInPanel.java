package View;

import Model.ApplicationUser;
import Model.Auditor;
import Model.Voter;
import Model.VotingData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class LogInPanel extends JPanel {
    private final JLabel email;
    private final JTextField temail;
    private final JLabel password;
    private final JPasswordField tpassword;

    public LogInPanel(JFrame mainFrame, JPanel previousPanel) {
        JLabel login = new JLabel("Please Login to Vote!");
        login.setFont(new Font("Arial", Font.BOLD, 30));
        login.setSize(400, 40);
        login.setLocation(240, 50);
        mainFrame.add(login);

        email = new JLabel("Email ");
        email.setFont(new Font("Arial", Font.PLAIN, 20));
        email.setSize(100, 20);
        email.setLocation(250, 100);
        mainFrame.add(email);

        temail = new JTextField();
        temail.setFont(new Font("Arial", Font.PLAIN, 15));
        temail.setSize(190, 20);
        temail.setLocation(350, 100);
        mainFrame.add(temail);

        password = new JLabel("Password ");
        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setSize(100, 20);
        password.setLocation(250, 150);
        mainFrame.add(password);

        tpassword = new JPasswordField();
        tpassword.setFont(new Font("Arial", Font.PLAIN, 15));
        tpassword.setSize(190, 20);
        tpassword.setLocation(350, 150);
        mainFrame.add(tpassword);

        String[] options = {"Voter", "Auditor"};
        JComboBox userType = new JComboBox(options);
        userType.setFont(new Font("Arial", Font.BOLD, 15));
        userType.setBounds(340, 200, 100, 20);
        mainFrame.add(userType);

        JButton button = new JButton("Log In");
        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setSize(100, 20);
        button.setLocation(340, 250);
        mainFrame.add(button);

        JButton backButton = new JButton("Back");
        //backButton.setFont(new Font("Arial", Font.PLAIN, 15));
        backButton.setSize(65, 25);
        backButton.setLocation(185, 5);
        mainFrame.add(backButton);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String applicationUserType;
                if (userType.getSelectedIndex() == 0) applicationUserType = Voter.class.getName();
                else if (userType.getSelectedIndex() == 1) applicationUserType = Auditor.class.getName();
                else {
                    JOptionPane.showMessageDialog(mainFrame, "Must check a box.", "Inane warning", JOptionPane.WARNING_MESSAGE);
                    return; //resets button after warning
                }
                try {
                    ApplicationUser applicationUser = VotingData.login(temail.getText(), tpassword.getText(), applicationUserType);
                    if (applicationUserType.equals(Voter.class.getName())) {
                        mainFrame.remove(backButton);
                        mainFrame.remove(userType);
                        mainFrame.remove(tpassword);
                        mainFrame.remove(password);
                        mainFrame.remove(temail);
                        mainFrame.remove(email);
                        mainFrame.remove(login);
                        mainFrame.remove(button);
                        //JPanel homePanel = new HomePanel(mainFrame, LogInPanel.this);
                        JPanel homePanel = new VoterPortal(mainFrame, applicationUser);
                        mainFrame.add(homePanel);
                        JOptionPane.showMessageDialog(mainFrame, "Login Successful!");
                        LogInPanel.super.setVisible(false);
                    } else if (applicationUserType.equals(Auditor.class.getName())) {
                        mainFrame.remove(backButton);
                        mainFrame.remove(userType);
                        mainFrame.remove(tpassword);
                        mainFrame.remove(password);
                        mainFrame.remove(temail);
                        mainFrame.remove(email);
                        mainFrame.remove(login);
                        mainFrame.remove(button);
                        JPanel auditPanel = new AuditPanel(mainFrame);
                        mainFrame.add(auditPanel);
                        JOptionPane.showMessageDialog(mainFrame, "Login Successful!");
                        LogInPanel.super.setVisible(false);
                    }
                } catch (IllegalArgumentException | SQLException exception) {
                    JOptionPane.showMessageDialog(mainFrame, "Invalid Login", "Inane error", JOptionPane.ERROR_MESSAGE);
                    tpassword.setText("");
                    temail.setText("");
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(mainFrame, "my bad the everyone usin the sql server rn", "Inane error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.remove(backButton);
                mainFrame.remove(userType);
                mainFrame.remove(tpassword);
                mainFrame.remove(password);
                mainFrame.remove(temail);
                mainFrame.remove(email);
                mainFrame.remove(button);
                mainFrame.remove(login);
                previousPanel.setVisible(true);
                LogInPanel.super.setVisible(false);
            }
        });

    }
}
