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
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        setPreferredSize(new Dimension(screenWidth, screenHeight));
    }

    public void setPurse(Purse p) {
        this.purse = p;
        revalidate();
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Font font = new Font("Arial", Font.BOLD, 35);
        g.setFont(font);
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int xPos = 50;
        int yPos = screenHeight / 2 - 400;


        g.drawString("Your change: ", 50, 70);

        if (purse.isEmpty()) {
            g.drawString("Empty Purse", xPos, yPos);
        } else {
            int imgWidth = 220;
            int imgHeight = 90;
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
                        xPos += imgWidth + 10;
                        if (xPos > getWidth() - imgWidth - 10) {
                            xPos = 50;
                            yPos += imgHeight + 10;
                        }
                    }
                }
                else {
                    g.drawString("image not found for " + entry.getKey(), xPos, yPos);
                    yPos += 20;
                }

                // New row after all needed coins/bills are printed
                xPos = 50;
                yPos += imgHeight + 10;

            }
        }
    }

    public ImageIcon getImage(Denomination d) {
        String path = "/Images/" + d.img();
        URL imgURL = getClass().getResource(path);

        return new ImageIcon(imgURL);
    }
}

