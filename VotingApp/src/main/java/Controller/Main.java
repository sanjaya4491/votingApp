package Controller;

import View.MainFrame;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        BasicConfigurator.configure();
        Logger.getLogger("com.zaxxer.hikari").setLevel(Level.ERROR);

        // Basic frame
        JFrame frame = new MainFrame();
        frame.setSize(800, 800);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
