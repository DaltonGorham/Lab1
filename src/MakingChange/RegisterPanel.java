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

    public RegisterPanel() {

        inputPanel = new JPanel();
        input = new JTextField(30);
        input.setPreferredSize(new Dimension(100, 30));
        input.addActionListener(new InputListener());
        inputPanel.add(input);

        changePanel = new PursePanel();
        this.add(inputPanel);
        this.add(changePanel);
        this.setVisible(true);
    }


   public class InputListener implements ActionListener {
       public void actionPerformed(ActionEvent e) {
           register = new Register();

           double changeBack = Double.parseDouble(input.getText());

           Purse change = register.makeChange(BigDecimal.valueOf(changeBack));

           for (Map.Entry<Denomination, Integer> entry : change.entrySet()) {
               Denomination denomination = entry.getKey();
               Integer value = entry.getValue();
               ImageIcon icon = changePanel.getImage(denomination);
               changePanel.setPurse(change);
           }

       }
   }
}
