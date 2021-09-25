package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterPanel extends JPanel {
    // Components of the Form
    private Container c;
    private final JLabel title;
    //private JLabel dontNeed;
    private final JLabel firstName;
    private final JLabel lastName;
    private final JTextField tlastName;
    private final JTextField tfirstName;
    private final JLabel email;
    private final JTextField temail;
    private final JLabel password;
    private final JPasswordField tpassword;
    private final JLabel street;
    private final JTextField tstreet;
    private final JLabel city;
    private final JTextField tcity;
    private final JLabel state;
    private final JTextField tstate;
    private final JLabel zip;
    private final JTextField tzip;
    private final JLabel add;
    private final JButton sub;
    private JButton reset;
    private JLabel res;

    // constructor, to initialize the components
    // with default values.
    public RegisterPanel(JFrame mainFrame, JPanel previousPanel) {

        JFrame register = new JFrame();
        int x = mainFrame.getWidth();
        int y = mainFrame.getHeight();

        title = new JLabel("Registration Form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(400, 40);
        title.setLocation(275, 50);

        firstName = new JLabel("First ");
        firstName.setFont(new Font("Arial", Font.PLAIN, 20));
        firstName.setSize(100, 20);
        firstName.setLocation(75, 100);

        tfirstName = new JTextField();
        tfirstName.setFont(new Font("Arial", Font.PLAIN, 15));
        tfirstName.setSize(190, 20);
        tfirstName.setLocation(175, 100);

        lastName = new JLabel("Last ");
        lastName.setFont(new Font("Arial", Font.PLAIN, 20));
        lastName.setSize(100, 20);
        lastName.setLocation(450, 100);

        tlastName = new JTextField();
        tlastName.setFont(new Font("Arial", Font.PLAIN, 15));
        tlastName.setSize(190, 20);
        tlastName.setLocation(550, 100);

        email = new JLabel("Email ");
        email.setFont(new Font("Arial", Font.PLAIN, 20));
        email.setSize(100, 20);
        email.setLocation(75, 150);

        temail = new JTextField();
        temail.setFont(new Font("Arial", Font.PLAIN, 15));
        temail.setSize(190, 20);
        temail.setLocation(175, 150);


        password = new JLabel("Password ");
        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setSize(100, 20);
        password.setLocation(450, 150);

        tpassword = new JPasswordField();
        tpassword.setFont(new Font("Arial", Font.PLAIN, 15));
        tpassword.setSize(190, 20);
        tpassword.setLocation(550, 150);

        add = new JLabel("Address: ");
        add.setFont(new Font("Arial", Font.PLAIN, 20));
        add.setSize(100, 20);
        add.setLocation(75, 200);

        street = new JLabel("Street: ");
        street.setFont(new Font("Arial", Font.PLAIN, 20));
        street.setSize(100, 20);
        street.setLocation(75, 250);

        tstreet = new JTextField();
        tstreet.setFont(new Font("Arial", Font.PLAIN, 15));
        tstreet.setSize(190, 20);
        tstreet.setLocation(175, 250);

        city = new JLabel("City: ");
        city.setFont(new Font("Arial", Font.PLAIN, 20));
        city.setSize(100, 20);
        city.setLocation(450, 250);

        tcity = new JTextField();
        tcity.setFont(new Font("Arial", Font.PLAIN, 15));
        tcity.setSize(190, 20);
        tcity.setLocation(550, 250);

        state = new JLabel("State: ");
        state.setFont(new Font("Arial", Font.PLAIN, 20));
        state.setSize(100, 20);
        state.setLocation(75, 300);

        tstate = new JTextField();
        tstate.setFont(new Font("Arial", Font.PLAIN, 15));
        tstate.setSize(190, 20);
        tstate.setLocation(175, 300);

        zip = new JLabel("Zipcode: ");
        zip.setFont(new Font("Arial", Font.PLAIN, 20));
        zip.setSize(100, 20);
        zip.setLocation(450, 300);

        tzip = new JTextField();
        tzip.setFont(new Font("Arial", Font.PLAIN, 15));
        tzip.setSize(190, 20);
        tzip.setLocation(550, 300);

        String[] options = {"Voter", "Auditor"};
        JComboBox userType = new JComboBox(options);
        userType.setFont(new Font("Arial", Font.BOLD, 15));
        userType.setBounds(340, 350, 100, 20);
        mainFrame.add(userType);

        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(340, 400);

        JButton backButton = new JButton("Back");
        //backButton.setFont(new Font("Arial", Font.PLAIN, 15));
        backButton.setSize(65, 25);
        backButton.setLocation(185, 5);
        mainFrame.add(backButton);

        mainFrame.add(title);
        mainFrame.add(firstName);
        mainFrame.add(tfirstName);
        mainFrame.add(tlastName);
        mainFrame.add(lastName);
        mainFrame.add(email);
        mainFrame.add(temail);
        mainFrame.add(password);
        mainFrame.add(tpassword);
        mainFrame.add(add);
        mainFrame.add(street);
        mainFrame.add(tstreet);
        mainFrame.add(city);
        mainFrame.add(tcity);
        mainFrame.add(state);
        mainFrame.add(tstate);
        mainFrame.add(tzip);
        mainFrame.add(zip);
        mainFrame.add(sub);

        mainFrame.setLocationRelativeTo(null);

        sub.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Validations.passwordValidator(tpassword.getText());
                    Validations.addressValidator(new Address(tstreet.getText(), tcity.getText(), tstate.getText(), tzip.getText(), "US"));
                } catch (IllegalArgumentException exception) {
                    JOptionPane.showMessageDialog(mainFrame, exception.getMessage(), "Inane warning", JOptionPane.WARNING_MESSAGE);
                    return;
                } finally {
                    if (Validations.emailValidator(temail.getText()) == false) {
                        JOptionPane.showMessageDialog(mainFrame, "Invalid Email", "Inane warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    } else if (userType.getSelectedIndex() == 0) {
                        Address address = new Address(tstreet.getText(), tcity.getText(), tstate.getText(), tzip.getText(), "United States");
                        Voter voter = new Voter(VotingData.generateUniqueCode(), tfirstName.getText(), tlastName.getText(), temail.getText(), tpassword.getText(), address);
                        try {
                            VotingData.registerVoter(voter);
                            JPanel welcomePanel = new WelcomePanel(mainFrame);
                            mainFrame.add(welcomePanel);
                            JOptionPane.showMessageDialog(mainFrame, "Registration Successful!");
                            RegisterPanel.super.setVisible(false);
                            mainFrame.remove(backButton);
                            mainFrame.remove(title);
                            mainFrame.remove(firstName);
                            mainFrame.remove(tfirstName);
                            mainFrame.remove(tlastName);
                            mainFrame.remove(lastName);
                            mainFrame.remove(email);
                            mainFrame.remove(temail);
                            mainFrame.remove(password);
                            mainFrame.remove(tpassword);
                            mainFrame.remove(add);
                            mainFrame.remove(street);
                            mainFrame.remove(tstreet);
                            mainFrame.remove(city);
                            mainFrame.remove(tcity);
                            mainFrame.remove(state);
                            mainFrame.remove(tstate);
                            mainFrame.remove(tzip);
                            mainFrame.remove(zip);
                            mainFrame.remove(sub);
                            mainFrame.remove(userType);
                        } catch (Exception exception) {
                            JOptionPane.showMessageDialog(mainFrame, "Registration Failed!", "Inane error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } else if (userType.getSelectedIndex() == 1) {
                        Auditor auditor = new Auditor(VotingData.generateUniqueCode(), tfirstName.getText(), tlastName.getText(), temail.getText(), tpassword.getText());
                        try {
                            VotingData.registerAuditor(auditor);
                            JPanel welcomePanel = new WelcomePanel(mainFrame);
                            mainFrame.remove(backButton);
                            mainFrame.add(welcomePanel);
                            mainFrame.remove(title);
                            mainFrame.remove(firstName);
                            mainFrame.remove(tfirstName);
                            mainFrame.remove(tlastName);
                            mainFrame.remove(lastName);
                            mainFrame.remove(email);
                            mainFrame.remove(temail);
                            mainFrame.remove(password);
                            mainFrame.remove(tpassword);
                            mainFrame.remove(add);
                            mainFrame.remove(street);
                            mainFrame.remove(tstreet);
                            mainFrame.remove(city);
                            mainFrame.remove(tcity);
                            mainFrame.remove(state);
                            mainFrame.remove(tstate);
                            mainFrame.remove(tzip);
                            mainFrame.remove(zip);
                            mainFrame.remove(sub);
                            mainFrame.remove(userType);
                            JOptionPane.showMessageDialog(mainFrame, "Registration Successful!");
                            RegisterPanel.super.setVisible(false);
                        } catch (IllegalArgumentException exception) {
                            JOptionPane.showMessageDialog(mainFrame, "Registration Failed!", "Inane error", JOptionPane.ERROR_MESSAGE);
                        } catch (Exception exception) {
                            JOptionPane.showMessageDialog(mainFrame, "my bad the everyone usin the sql server rn", "Inane error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(mainFrame, "Must select a box!", "Inane warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
            }
        });

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                previousPanel.setVisible(true);
                mainFrame.remove(backButton);
                mainFrame.remove(title);
                mainFrame.remove(firstName);
                mainFrame.remove(tfirstName);
                mainFrame.remove(tlastName);
                mainFrame.remove(lastName);
                mainFrame.remove(email);
                mainFrame.remove(temail);
                mainFrame.remove(password);
                mainFrame.remove(tpassword);
                mainFrame.remove(add);
                mainFrame.remove(street);
                mainFrame.remove(tstreet);
                mainFrame.remove(city);
                mainFrame.remove(tcity);
                mainFrame.remove(state);
                mainFrame.remove(tstate);
                mainFrame.remove(tzip);
                mainFrame.remove(zip);
                mainFrame.remove(sub);
                mainFrame.remove(userType);
                RegisterPanel.super.setVisible(false);
            }
        });
    }
}
