package View;

import Model.ApplicationUser;
import Model.VotingData;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BallotIssue extends JPanel {
    private JCheckBox noCheckBox;
    private JCheckBox yesCheckBox4;
    private JCheckBox yesCheckBox3;
    private JCheckBox noCheckBox1;
    private JCheckBox noCheckBox5;
    private JCheckBox yesCheckBox1;
    private JCheckBox noCheckBox3;
    private JCheckBox yesCheckBox;
    private JLabel ballotIssueText;
    private JPanel BallotIssue;
    private JButton submitButton;
    private int preYesCounter = 0;
    private int mayYesCounter = 0;
    private int preNoCounter = 0;
    private int mayNoCounter = 0;
    private final int ballotId = 1;

    public BallotIssue(JFrame mainFrame, JPanel previousPanel, ApplicationUser applicationUser) {
        add(BallotIssue);

        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!yesCheckBox.isSelected() && !noCheckBox.isSelected()) {
                    JOptionPane.showMessageDialog(mainFrame, "One or more mayoral Issues not selected.", "Inane warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (!yesCheckBox4.isSelected() && !noCheckBox5.isSelected()) {
                    JOptionPane.showMessageDialog(mainFrame, "One or more mayoral Issues not selected.", "Inane warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (!yesCheckBox3.isSelected() && !noCheckBox1.isSelected()) {
                    JOptionPane.showMessageDialog(mainFrame, "One or more Presidential Issues not selected.", "Inane warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (!yesCheckBox1.isSelected() && !noCheckBox3.isSelected()) {
                    JOptionPane.showMessageDialog(mainFrame, "One or more Presidential Issues not selected.", "Inane warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(mainFrame, "Vote Completed!");
                JPanel welcomePanel = new WelcomePanel(mainFrame);
                if (yesCheckBox.isSelected()) {
                    VotingData.updateBallotIssueCount(1, true);
                    VotingData.updateVoterBallot(VotingData.getVoterId(applicationUser.getCode()), ballotId);
                    preYesCounter++;
                } else {
                    VotingData.updateBallotIssueCount(1, false);
                    VotingData.updateVoterBallot(VotingData.getVoterId(applicationUser.getCode()), ballotId);
                }

                if (yesCheckBox1.isSelected()) {
                    VotingData.updateBallotIssueCount(2, true);
                    VotingData.updateVoterBallot(VotingData.getVoterId(applicationUser.getCode()), ballotId);
                    preYesCounter++;
                } else {
                    VotingData.updateBallotIssueCount(2, false);
                    VotingData.updateVoterBallot(VotingData.getVoterId(applicationUser.getCode()), ballotId);
                }

                if (yesCheckBox3.isSelected()) {
                    VotingData.updateBallotIssueCount(3, true);
                    VotingData.updateVoterBallot(VotingData.getVoterId(applicationUser.getCode()), ballotId);
                    mayYesCounter++;
                } else {
                    VotingData.updateBallotIssueCount(3, false);
                    VotingData.updateVoterBallot(VotingData.getVoterId(applicationUser.getCode()), ballotId);
                }
                if (yesCheckBox4.isSelected()) {
                    VotingData.updateBallotIssueCount(4, true);

                    VotingData.updateVoterBallot(VotingData.getVoterId(applicationUser.getCode()), ballotId);
                    mayYesCounter++;
                } else {
                    VotingData.updateBallotIssueCount(4, false);
                    VotingData.updateVoterBallot(VotingData.getVoterId(applicationUser.getCode()), ballotId);
                }

                JPanel voterPortal = new VoterPortal(mainFrame, applicationUser);
                mainFrame.add(voterPortal);
                BallotIssue.super.setVisible(false);

                preNoCounter = 2 - preYesCounter;
                mayNoCounter = 2 - mayYesCounter;
                System.out.println(preYesCounter);
                System.out.println(mayYesCounter);
                System.out.println(preNoCounter);
                System.out.println(mayNoCounter);
            }
        });
    }
}
