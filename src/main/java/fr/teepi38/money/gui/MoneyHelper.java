package fr.teepi38.money.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MoneyHelper {
    private JFrame parent;

    public MoneyHelper( JFrame parent ) {
        this.parent = parent;
    }
    
    public void about( String name, String version, String date, String url) {
        JOptionPane.showMessageDialog(parent,
            name + "\nv" + version + "\n" + date + "\n" + url,
            "A propos de ...",
            JOptionPane.PLAIN_MESSAGE
        );
    }
}
