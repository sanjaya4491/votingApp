package Model;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;

public class JLink
        extends JTextField
        implements MouseListener, FocusListener, ActionListener {
    private Color linkColor = new Color(0, 0, 255);
    private Color hoverColor = new Color(255, 0, 0);
    private Color activeColor = new Color(128, 0, 128);

    private Border activeBorder;
    private Border hoverBorder;
    private Border linkBorder;

    private UnderlineType underlineType;

    public enum UnderlineType {
        NONE,
        ALWAYS,
        HOVER,
    }

    public JLink(String text) {
        super(text);
        this.init();
    }

    public Color getLinkColor() {
        return linkColor;
    }

    public void setLinkColor(Color color) {
        linkColor = color;
    }

    public Color getHoverColor() {
        return hoverColor;
    }

    public void setHoverColor(Color color) {
        hoverColor = color;
    }

    public Color getActiveColor() {
        return activeColor;
    }

    public void setActiveColor(Color color) {
        activeColor = color;
    }

    /**
     *
     */
    protected void init() {
        this.addMouseListener(this);
        this.addFocusListener(this);
        this.addActionListener(this);
        setToolTipText(super.getText());

        activeBorder = new MatteBorder(0, 0, 0, 0, activeColor);
        hoverBorder = new MatteBorder(0, 0, 0, 0, hoverColor);
        linkBorder = new MatteBorder(0, 0, 1, 0, linkColor);

        setEditable(false);
        setForeground(linkColor);
        setBorder(linkBorder);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void focusGained(FocusEvent e) {
        setForeground(hoverColor);
        setBorder(hoverBorder);
    }

    @Override
    public void focusLost(FocusEvent e) {
        setForeground(linkColor);
        setBorder(linkBorder);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        setForeground(activeColor);
        setBorder(activeBorder);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setForeground(hoverColor);
        setBorder(hoverBorder);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setForeground(linkColor);
        setBorder(linkBorder);
    }
}
