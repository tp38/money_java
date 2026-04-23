package fr.teepi38.money.gui.welcome;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class WelcomePanel extends JPanel {
    private DbChooserPanel db;

    public WelcomePanel() {
        super();
        setName("welcome_panel");
        db = new DbChooserPanel();

        JSplitPane split = new JSplitPane();
        split.setLeftComponent( new ImagePanel() );
        split.setRightComponent(db);

        add( split );
    }

    public DbChooserPanel getDb() {
        return db;
    }

}
