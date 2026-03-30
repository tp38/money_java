package fr.teepi38.money.gui.help;

import fr.teepi38.money.AppDef;

/**
 * provide any method for all the help menu tiems
 * @author : <a href="mailto:thierry.probst@free.fr">Thierry Probst</a>
 * @version 0.0, 21/03/2026
 * @since 0.0.2
 */
public class HelpManager {
    /**
     * the data 
     */
    private AppDef app;

    /**
     * Constructor 
     * @param app the data
     * @since 0.0
     * @see fr.teepi38.money.AppDef
     */
    public HelpManager( AppDef app ) {
        this.app = app;
    }

    /**
     * create a HelpAboutPanel 
     * @return the panel
     * @since 0.0
     * @see fr.teepi38.money.gui.help.HelpAboutPanel
     */
    public HelpAboutPanel about() {
        return new HelpAboutPanel(app);
    }
    
    /**
     * create a HelpReadmePanel 
     * @return the panel
     * @since 0.0
     * @see fr.teepi38.money.gui.help.HelpReadmePanel
     */
    public HelpReadmePanel readme() {
        return new HelpReadmePanel();
    }
}
