package View;

import Model.ApplicationUser;
import Model.VotingData;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VoterPortal extends JPanel {

    private JPanel Portal;
    private JButton voteButton;
    private JButton resultsButton;
    private JButton votingHistoryButton;
    private JButton logoutButton;
    private JLabel title;
    private boolean canVote = false;

    public VoterPortal(JFrame mainFrame, ApplicationUser applicationUser) {
        add(Portal);
        canVote = !(VotingData.voterHasVoted(VotingData.getVoterId(applicationUser.getCode()), 1));


        voteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (canVote) {
                    JPanel votePanel = new VotePanel(mainFrame, VoterPortal.this, applicationUser);
                    mainFrame.add(votePanel);
                    VoterPortal.super.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "You Have Already Voted", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        });
        votingHistoryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel voterHistory = new VoterHistory(mainFrame, VoterPortal.this, applicationUser);
                mainFrame.add(voterHistory);
                VoterPortal.super.setVisible(false);
            }
        });

        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel welcomePanel = new WelcomePanel(mainFrame);
                mainFrame.add(welcomePanel);
                VoterPortal.super.setVisible(false);
            }
        });


        resultsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel resultsPanel = new ResultsPanel(mainFrame, VoterPortal.this);
                mainFrame.add(resultsPanel);
                VoterPortal.super.setVisible(false);
            }
        });


    }


}
