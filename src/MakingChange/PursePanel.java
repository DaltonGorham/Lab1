package MakingChange;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PursePanel extends JPanel {
   private Purse purse;

    PursePanel() {
        this.purse = new Purse();

    }

    public void setPurse(Purse p) {
        this.purse = p;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int xPos = this.getWidth() / 2;
        int yPos = this.getHeight() / 2;

        g.drawString("Change Back", 50,70);

        if (purse.isEmpty()){
            g.drawString("Empty Purse", 50,40);
        }
        else {
            for (Map.Entry<Denomination, Integer> entry : purse.entrySet()) {
                Image image = getImage(entry.getKey()).getImage();

                g.drawImage(image,xPos, yPos, this);
                yPos += 20;

            }
        }



    }

   public ImageIcon getImage(Denomination d) {
         return new ImageIcon("/Images/" + d.img());

    }
}
