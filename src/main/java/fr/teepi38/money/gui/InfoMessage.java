package fr.teepi38.money.gui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * manage text color on the bottom jlabel
 * @author : <a href="mailto:thierry.probst@free.fr">Thierry Probst</a>
 * @version 0.0, 21/03/2026
 * @since 0.0.3
  */
public class InfoMessage extends JLabel {

    /**
     * constructor
     * @since 0.0
     */
    public InfoMessage() {
        super();
        setName("info_msg");
        setMaximumSize( new Dimension(800, 60));
        setFont( new Font("Serif", Font.PLAIN, 18) );
    }
    
    /**
     * set text in blue
     * @param msg
     * @since 0.0
     */
    public void InfoText( String msg ) {
        setText("<html><span style='color:blue'>"+msg+"</span></html>");
    }
}
