package fr.teepi38.money.gui.welcome;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    public ImagePanel() {
        setName("image_panel");
        // welcome image
        JLabel welcomeLbl = new JLabel();
        welcomeLbl.setName("welcome_img");
        welcomeLbl.setToolTipText("cartoon-money.png");
        try {
            File f = new File("src/main/resources/cartoon-money.png" );
            BufferedImage bufferedImage = ImageIO.read( f );
            ImageIcon welcomeIcon = new ImageIcon(bufferedImage);
            welcomeLbl.setIcon(welcomeIcon);
        } catch ( IOException e ) {
            welcomeLbl.setText("impossible d'ouvrir cartoon-money.png");
        } finally {
            add( welcomeLbl );
        }
    }
}
