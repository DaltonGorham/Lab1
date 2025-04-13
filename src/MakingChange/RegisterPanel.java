package MakingChange;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Map;

public class RegisterPanel extends JPanel {
    Register register;
    JPanel inputPanel;
    JTextField input;
    PursePanel changePanel;
    JLabel label;

    public RegisterPanel() {
        register = new Register();
        setLayout(new BorderLayout());
        inputPanel = new JPanel();
        input = new JTextField(30);
        label = new JLabel("Enter the amount of change to receive back: ");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        inputPanel.add(label);
        input.setPreferredSize(new Dimension(100, 30));
        input.addActionListener(new InputListener());
        inputPanel.add(input);
        changePanel = new PursePanel();

        // add the Purse Panel as an observer of register
        register.addObserver(changePanel);

        add(inputPanel, BorderLayout.NORTH);
        add(changePanel, BorderLayout.CENTER);

    }


    public class InputListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            try {
                double changeBack = Double.parseDouble(input.getText());
                register.makeChange(BigDecimal.valueOf(changeBack));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(RegisterPanel.this, "Please enter a valid number",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
