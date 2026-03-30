package fr.teepi38.money;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * an option pane to confirm before closing
 * @author : <a href="mailto:thierry.probst@free.fr">Thierry Probst</a>
 * @version 0.0, 21/03/2026
 * @since 0.0.2
 * @see java.awt.event.WindowAdapter
 */
class ApplicationExit extends WindowAdapter {

    /**
     * what to do before closing
     * @since 0.0
     * @see java.awt.event.WindowEvent
     */
    public void windowClosing( WindowEvent e) {
        JFrame frame = (JFrame)e.getSource();

        int result = JOptionPane.showConfirmDialog(
            frame,
            "Êtes vous sur de vouloir quitter l'application ?",
            "Quitter MyMoneyRelax",
            JOptionPane.YES_NO_OPTION );
        
        if( result == JOptionPane.YES_OPTION ) {
            frame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        }
    }
}