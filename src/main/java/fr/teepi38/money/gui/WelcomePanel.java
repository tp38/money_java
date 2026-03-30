package fr.teepi38.money.gui;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomePanel extends JPanel {

    public WelcomePanel() {
        setName("welcome_panel");
        // welcome image
        JLabel welcomeImg = new JLabel();
        welcomeImg.setName("welcome_img");
        welcomeImg.setToolTipText("cartoon-money.png");
        try {
            File f = new File("src/main/resources/cartoon-money.png" );
            BufferedImage bufferedImage = ImageIO.read( f );
            ImageIcon welcomeIcon = new ImageIcon(bufferedImage);
            welcomeImg.setIcon(welcomeIcon);
        } catch ( IOException e ) {
            welcomeImg.setText("impossible d'ouvrir cartoon-money.png");
        } finally {
            add( welcomeImg );
        }
    }
}
