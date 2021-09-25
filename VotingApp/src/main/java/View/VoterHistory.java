package View;

import Model.ApplicationUser;
import Model.VotingData;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class VoterHistory extends JPanel {
    private JPanel voter_history_panel;
    private JButton goBackButton;
    private JEditorPane editorPane1;
    private JLabel Title;


    public VoterHistory(JFrame mainFrame, JPanel previousPanel, ApplicationUser applicationUser) {
        add(voter_history_panel);
        goBackButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel voterPortal = new VoterPortal(mainFrame, applicationUser);
                mainFrame.add(voterPortal);
                VoterHistory.super.setVisible(false);
            }
        });
        List<String> dates = VotingData.voteHistory(applicationUser.getCode());
        String str_dates = "You voted on election(s): \n";
        for (String x : dates) {
            str_dates += x + "\n";
        }
        editorPane1.setText(str_dates);

    }
}
