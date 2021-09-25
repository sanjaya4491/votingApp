package View;

import Model.Validations;
import Model.VotingData;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AuditPanel extends JPanel {
    private JPanel auditPanel;
    private JButton logoutButton;
    private JEditorPane editorPane1;
    private JTextArea searchBar;
    private JLabel title;
    private JButton searchButton;
    String firstName = null;
    String lastName = null;

    public AuditPanel(JFrame mainFrame) {
        add(auditPanel);

        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String[] splited = searchBar.getText().split("\\s+");
                if (splited.length < 2) {
                    JOptionPane.showMessageDialog(mainFrame, "Please enter firstName{space}lastName", "Inane warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                firstName = splited[0];
                lastName = splited[1];
                if (Validations.firstNameValidator(firstName) && Validations.lastNameValidator(lastName)) {
                    List<String> dates = VotingData.voteHistory(firstName, lastName);
                    String str_dates = firstName + " " + lastName + " voted on election(s): \n";
                    for (String x : dates) {
                        str_dates += x + "\n";
                    }
                    editorPane1.setText(str_dates);
                    return;
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Please enter a valid name", "Inane warning", JOptionPane.WARNING_MESSAGE);
                    searchBar.setText("");
                    return;
                }
            }
        });

        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel welcomePanel = new WelcomePanel(mainFrame);
                mainFrame.add(welcomePanel);
                AuditPanel.super.setVisible(false);
            }
        });

    }
}
