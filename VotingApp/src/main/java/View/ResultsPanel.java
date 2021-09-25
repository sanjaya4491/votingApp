package View;

import Model.Candidate;
import Model.VotingData;
import org.javatuples.Pair;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ResultsPanel extends JPanel {
    private JButton backButton;
    private JButton logoutButton;
    private JLabel electionResultsLabel;
    private JLabel mayoralRaceLabel;
    private JLabel mayoralWinnerLabel;
    private JPanel resultsForm;
    private JLabel issue1YesPercentageLabel;
    private JLabel issue1NoPercentageLabel;
    private JLabel issue1YesCountLabel;
    private JLabel issue1NoCountLabel;
    private JLabel ballotRaceWinnerLabel;
    private JLabel winnerLabel;
    private JLabel presidentialWinnerLabel;
    private JLabel issue2YesCountLabel;
    private JLabel issue2YesPercentageLabel;
    private JLabel issue2NoCountLabel;
    private JLabel issue2NoPercentageLabel;
    private JLabel issue3YesCountLabel;
    private JLabel issue3YesPercentageLabel;
    private JLabel issue3NoCountLabel;
    private JLabel issue3NoPercentageLabel;
    private JLabel issue4YesCountLabel;
    private JLabel issue4NoCountLabel;
    private JLabel issue4YesPercentageLabel;
    private JLabel issue4NoPercentageLabel;

    public ResultsPanel(JFrame mainFrame, JPanel previousPanel) {
        add(resultsForm);

        // Mayoral race
        String mayoralWinnerStr;
        if (VotingData.getBallotRaceResult(1) != null) {
            Candidate mayoralWinner = VotingData.getBallotRaceResult(1);
            mayoralWinnerStr = String.format("%s %s", mayoralWinner.getFirstName(), mayoralWinner.getLastName());
        } else {
            mayoralWinnerStr = "Undecided";
        }

        mayoralWinnerLabel.setText(String.format(mayoralWinnerStr));

        // Presidential race
        String presidentialWinnerStr;
        if (VotingData.getBallotRaceResult(2) != null) {
            Candidate presidentialWinner = VotingData.getBallotRaceResult(2);
            presidentialWinnerStr = String.format("%s %s", presidentialWinner.getFirstName(), presidentialWinner.getLastName());
        } else {
            presidentialWinnerStr = "Undecided";
        }

        presidentialWinnerLabel.setText(String.format(presidentialWinnerStr));

        // Issue 1
        Pair<Integer, Integer> issue1Results = VotingData.getBallotIssueResult(1);
        int issue1YesCount = issue1Results.getValue0();
        int issue1NoCount = issue1Results.getValue1();
        int issue1TotalCount = issue1YesCount + issue1NoCount;
        double issue1YesPercentage = (double) issue1YesCount / (double) issue1TotalCount;
        double issue1NoPercentage = (double) issue1NoCount / (double) issue1TotalCount;

        issue1YesCountLabel.setText(String.format("%d", issue1YesCount));
        issue1NoCountLabel.setText(String.format("%d", issue1NoCount));

        issue1YesPercentageLabel.setText(String.format("%.2f %%", issue1YesPercentage * 100));
        issue1NoPercentageLabel.setText(String.format("%.2f %%", issue1NoPercentage * 100));

        // Issue 2
        Pair<Integer, Integer> issue2Results = VotingData.getBallotIssueResult(2);
        int issue2YesCount = issue2Results.getValue0();
        int issue2NoCount = issue2Results.getValue1();
        int issue2TotalCount = issue2YesCount + issue2NoCount;
        double issue2YesPercentage = (double) issue2YesCount / (double) issue2TotalCount;
        double issue2NoPercentage = (double) issue2NoCount / (double) issue2TotalCount;

        issue2YesCountLabel.setText(String.format("%d", issue2YesCount));
        issue2NoCountLabel.setText(String.format("%d", issue2NoCount));

        issue2YesPercentageLabel.setText(String.format("%.2f %%", issue2YesPercentage * 100));
        issue2NoPercentageLabel.setText(String.format("%.2f %%", issue2NoPercentage * 100));

        // Issue 3
        Pair<Integer, Integer> issue3Results = VotingData.getBallotIssueResult(3);
        int issue3YesCount = issue3Results.getValue0();
        int issue3NoCount = issue3Results.getValue1();
        int issue3TotalCount = issue3YesCount + issue3NoCount;
        double issue3YesPercentage = (double) issue3YesCount / (double) issue3TotalCount;
        double issue3NoPercentage = (double) issue3NoCount / (double) issue3TotalCount;

        issue3YesCountLabel.setText(String.format("%d", issue3YesCount));
        issue3NoCountLabel.setText(String.format("%d", issue3NoCount));

        issue3YesPercentageLabel.setText(String.format("%.2f %%", issue3YesPercentage * 100));
        issue3NoPercentageLabel.setText(String.format("%.2f %%", issue3NoPercentage * 100));

        // Issue 4
        Pair<Integer, Integer> issue4Results = VotingData.getBallotIssueResult(4);
        int issue4YesCount = issue4Results.getValue0();
        int issue4NoCount = issue4Results.getValue1();
        int issue4TotalCount = issue4YesCount + issue4NoCount;
        double issue4YesPercentage = (double) issue4YesCount / (double) issue4TotalCount;
        double issue4NoPercentage = (double) issue4NoCount / (double) issue4TotalCount;

        issue4YesCountLabel.setText(String.format("%d", issue4YesCount));
        issue4NoCountLabel.setText(String.format("%d", issue4NoCount));

        issue4YesPercentageLabel.setText(String.format("%.2f %%", issue4YesPercentage * 100));
        issue4NoPercentageLabel.setText(String.format("%.2f %%", issue4NoPercentage * 100));

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ResultsPanel.super.setVisible(false);
                previousPanel.setVisible(true);
            }
        });

        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                WelcomePanel welcomePanel = new WelcomePanel(mainFrame);
                mainFrame.add(welcomePanel);
                ResultsPanel.super.setVisible(false);
            }
        });
    }
}
