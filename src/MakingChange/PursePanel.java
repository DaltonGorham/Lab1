package MakingChange;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PursePanel extends JPanel {
    private Purse purse;

    PursePanel() {
        this.purse = new Purse();
        setPreferredSize(new Dimension(800, 600));
    }

    public void setPurse(Purse p) {
        this.purse = p;
        revalidate();
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int xPos = 50;
        int yPos = screenHeight / 2 - 475;
        final int yourChangeStringX = 50;
        final int yourChangeStringY = 50;


        g.drawString(("Your change: " + purse.toString()), yourChangeStringX, yourChangeStringY);

        if (!purse.isEmpty()) {
            final int imgWidth = 180;
            final int imgHeight = 90;
            final int gap = 10;

            Image image;

            List<HashMap.Entry<Denomination, Integer>> sortedCash = new ArrayList<>(purse.entrySet());
            sortedCash.sort((bill1, bill2) ->
                    Double.compare(bill2.getKey().amt(), bill1.getKey().amt()));

            for (Map.Entry<Denomination, Integer> entry : sortedCash) {
                ImageIcon icon = this.getImage(entry.getKey());
                int count = entry.getValue();


                if (icon != null) {
                    image = icon.getImage();
                    for (int i = 0; i < count; i++) {
                        g.drawImage(image, xPos, yPos, imgWidth, imgHeight, this);
                        xPos += imgWidth + gap;

                        if (xPos > getWidth() - imgWidth - gap) {
                            xPos = 50;
                            yPos += imgHeight + gap;
                        }


                    }
                }
                else {
                    g.drawString("image not found for " + entry.getKey(), xPos, yPos);
                    yPos += 20;
                }

                // New row after all needed coins/bills are printed
                xPos = 50;
                yPos += imgHeight + gap;

            }
        }
    }

    public ImageIcon getImage(Denomination d) {
        String path = "/Images/" + d.img();
        URL imgURL = getClass().getResource(path);

        return new ImageIcon(imgURL);
    }


}



