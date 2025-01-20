package MakingChange;
import javax.swing.*;
import java.awt.*;


/*
    This is the main method for the GUI application
    Creates a JFrame and adds the register panel to it
 */
public class MakingChange {
    public static void main(String[] args) {
        JFrame frame = new JFrame("MakingChange");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RegisterPanel registerPanel = new RegisterPanel();
        frame.add(registerPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(true);
    }
}
