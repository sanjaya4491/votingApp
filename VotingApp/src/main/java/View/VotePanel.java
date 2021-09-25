package View;

import Model.ApplicationUser;
import Model.VotingData;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VotePanel extends JPanel {
    private JCheckBox patMannCheckBox;
    private JCheckBox sonicCheckBox;
    private JCheckBox powerPuffCheckBox;
    private JCheckBox dawnKeyKongCheckBox;
    private JButton backButton;
    private JButton continueButton;
    private JPanel voteForm;
    private JPanel mayoralPanel;
    private JPanel presidentPanel;
    private JPanel backgroundPanel;
    private JLabel ballotName;
    int candidate1 = 1;
    int candidate2 = 2;
    int candidate3 = 3;
    int candidate4 = 4;
    private int ballotRaceId1 = 1;
    private int ballotRaceId2 = 2;
    private int ballotId = 1;

    public VotePanel(JFrame mainFrame, JPanel previousPanel, ApplicationUser applicationUser) {


        voteForm.setSize(800, 800);

        add(voteForm);


        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                previousPanel.setVisible(true);
                VotePanel.super.setVisible(false);
            }
        });


        continueButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                JPanel ballotIssue = new BallotIssue(mainFrame, VotePanel.this, applicationUser);
                if (patMannCheckBox.isSelected()) {
                    VotingData.updateCandidateBallotRace(ballotRaceId1, candidate1);
                    VotingData.updateVoterBallot(VotingData.getVoterId(applicationUser.getCode()), ballotId);
                } else if (dawnKeyKongCheckBox.isSelected()) {
                    VotingData.updateCandidateBallotRace(ballotRaceId1, candidate2);
                    VotingData.updateVoterBallot(VotingData.getVoterId(applicationUser.getCode()), ballotId);

                }
                if (sonicCheckBox.isSelected()) {
                    VotingData.updateCandidateBallotRace(ballotRaceId2, candidate3);
                    VotingData.updateVoterBallot(VotingData.getVoterId(applicationUser.getCode()), ballotId);

                } else if (powerPuffCheckBox.isSelected()) {
                    VotingData.updateCandidateBallotRace(ballotRaceId2, candidate4);
                    VotingData.updateVoterBallot(VotingData.getVoterId(applicationUser.getCode()), ballotId);
                }

                if (!patMannCheckBox.isSelected() && !dawnKeyKongCheckBox.isSelected()) {
                    JOptionPane.showMessageDialog(mainFrame, "Must check one person for the mayoral Race.", "Inane warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!sonicCheckBox.isSelected() && !powerPuffCheckBox.isSelected()) {
                    JOptionPane.showMessageDialog(mainFrame, "Must check one person for the Presidential Race.", "Inane warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }


                mainFrame.add(ballotIssue);
                View.VotePanel.super.setVisible(false);
            }
        });


    }
}
