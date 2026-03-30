package fr.teepi38.money;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * define the menu in money_java application
 * @since 0.3
 * @version 0.0
 * @see javax.swing.JMenuBar
 */
class MoneyMenu extends JMenuBar {

    /**
     * constructor
     * @param mi the listener
     * @since 0.0
     */
    MoneyMenu( MoneyControler mi ) {
        super();
        setName("menuBar");

        JMenu menuDb = new JMenu("Databases" );
        menuDb.setEnabled(false);

        JMenu menuAccounts = new JMenu("Comptes");
        menuAccounts.setEnabled(false);

        JMenu menuCategories = new JMenu("Categories");
        menuCategories.setEnabled(false);

        JMenu menuSearch = new JMenu("Recherches");
        menuSearch.setEnabled(false);

        JMenu menuHelp = new JMenu("Aide");
        menuHelp.setEnabled(true);
        JMenuItem welcomeItem = new JMenuItem("Accueil");
        welcomeItem.setName("welcome_item" );
        welcomeItem.addActionListener( mi );
        menuHelp.add(welcomeItem);

        JMenuItem aboutItem = new JMenuItem("A propos de ...");
        aboutItem.setName("about_item" );
        aboutItem.addActionListener( mi );
        menuHelp.add(aboutItem);

        JMenuItem readmeItem = new JMenuItem("Lisez-moi");
        readmeItem.setName( "readme_item" );
        readmeItem.addActionListener( mi );
        menuHelp.add(readmeItem);

        add( menuDb );
        add( menuAccounts );
        add( menuCategories );
        add( menuSearch );
        add( menuHelp );
    }
}
