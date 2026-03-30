package fr.teepi38.money.gui.help;

import java.io.IOException;
import java.awt.Dimension;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * display the README.html file on a JEditorPane
 * @author : <a href="mailto:thierry.probst@free.fr">Thierry Probst</a>
 * @version 0.0, 21/03/2026
 * @since 0.0.2
 * @see javax.swing.JScrollPane
 */
public class HelpReadmePanel extends JPanel {

    /**
     * constructor : read file, set it uneditable and display it
     * @since 0.0
     */
    public HelpReadmePanel() {
        super();
        setName("help_readme_pane");
        Dimension d = new Dimension(750, 500);
        JEditorPane edit = null;

        try {
            edit = new JEditorPane( "file:src/main/resources/docs/README.html" );
            edit.setEditable(false);
            edit.setName("help_readme_editor");
        } catch( IOException i ) {
            System.out.println( "IO exception" );
        }

        JScrollPane sp = new JScrollPane(edit);
        sp.setName("help_readme_scrollpane");
        sp.setPreferredSize(d);

        add( sp );
    }
    
}
