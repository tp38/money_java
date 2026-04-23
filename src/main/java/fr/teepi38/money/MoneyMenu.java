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
public class MoneyMenu extends JMenuBar {
    private JMenu menuAccounts;
    private JMenu menuCategories;
    private JMenu menuSearch;

    /**
     * constructor
     * @param mi the listener
     * @since 0.0
     */
    MoneyMenu( MoneyControler mi ) {
        super();
        setName("menuBar");

        menuAccounts = new JMenu("Comptes");
        menuAccounts.setEnabled(false);
        JMenuItem accountItem = new JMenuItem( "Comptes" );
        menuAccounts.add( accountItem );

        menuCategories = new JMenu("Categories");
        menuCategories.setEnabled(false);

        menuSearch = new JMenu("Recherches");
        menuSearch.setEnabled(false);

        JMenu menuHelp = new JMenu("Aide");
        JMenu menuQuitter = new JMenu("Quitter");

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

        JMenuItem exitItem = new JMenuItem("Quitter");
        exitItem.setName("exit_item");
        exitItem.addActionListener( mi );
        menuQuitter.add(exitItem);

        add( menuAccounts );
        add( menuCategories );
        add( menuSearch );
        add( menuHelp );
        add( menuQuitter );
    }

    public void enableEntries() {
        menuAccounts.setEnabled(true);
        menuCategories.setEnabled(true);
        menuSearch.setEnabled(true);
    }
}
