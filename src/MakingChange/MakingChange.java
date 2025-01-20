package MakingChange;
import javax.swing.*;
import java.awt.*;

public class MakingChange {
    public static void main(String[] args) {
        JFrame frame = new JFrame("MakingChange");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        RegisterPanel registerPanel = new RegisterPanel();
        frame.add(registerPanel);
        frame.setVisible(true);
    }
}
