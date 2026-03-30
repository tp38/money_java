package fr.teepi38.money;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import fr.teepi38.money.gui.WelcomePanel;
import fr.teepi38.money.gui.help.HelpManager;

/**
 * menu item listener : remplace the central component by the correct (item selected) panel
 * @since 0.3
 * @see java.awt.event.ActionListener
 */
class MoneyControler implements ActionListener {

    final Money outer;

    /**
     * Constructor
     * @param outer the Money class
     */
    MoneyControler( Money outer ) {
        this.outer = outer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() instanceof JMenuItem  ) {
            JMenuItem mi = (JMenuItem)e.getSource();
            outer.remove(outer.current_panel);
            switch( mi.getName() ) {
                case "welcome_item" :
                    outer.current_panel = new WelcomePanel();
                    break;
                case "about_item" :
                case "readme_item" :
                    HelpManager help = new HelpManager(outer.app);
                    if( mi.getName().equals( "about_item") )
                        outer.current_panel = help.about();
                    else
                        outer.current_panel = help.readme();
                    break;
                default:
                    break;
            }
            outer.add( outer.current_panel, BorderLayout.CENTER );
        }
        outer.revalidate();
        outer.current_panel.repaint();
    }

}
