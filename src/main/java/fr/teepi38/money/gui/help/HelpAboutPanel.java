package fr.teepi38.money.gui.help;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.teepi38.money.AppDef;

/**
 * display the about data (appName, appVersion and appDate)
 * @author : <a href="mailto:thierry.probst@free.fr">Thierry Probst</a>
 * @version 0.0, 21/03/2026
 * @since 0.0.2
 * @see javax.swing.JPanel
 */
public class HelpAboutPanel  extends JPanel {

    /**
     * constructor : create a panel with data and display it
     * @since 0.0
     */
    public HelpAboutPanel( AppDef app_def ) {
        super(); 
        setName("help_about_pane");
        setLayout( new GridLayout(4, 2) );

        SimpleDateFormat sdf = new SimpleDateFormat( "dd.MM.yyyy" );
        Dimension d = new Dimension(150, 35);


        JLabel appLbl = new JLabel("application : ");
        appLbl.setName("help_about_app_lbl");
        appLbl.setMaximumSize(d);
        add( appLbl );

        JLabel appName = new JLabel(app_def.getName());
        appName.setName("help_about_app_value");
        appName.setMaximumSize(d);
        add( appName );
        
        JLabel versionLbl = new JLabel("version : ");
        versionLbl.setName("help_about_version_lbl");
        versionLbl.setMaximumSize(d);
        add( versionLbl );

        JLabel versionName = new JLabel(app_def.getVersion());
        versionName.setName("help_about_version_value");
        versionName.setMaximumSize(d);
        add( versionName );
        
        JLabel dateLbl = new JLabel("date : ");
        dateLbl.setName("help_about_date_lbl");
        dateLbl.setMaximumSize(d);
        add( dateLbl );

        JLabel dateName = new JLabel(sdf.format( app_def.getDate() ) );
        dateName.setName("help_about_date_value");
        dateName.setMaximumSize(d);
        add( dateName );
        
        JLabel authorLbl = new JLabel("auteur : ");
        authorLbl.setName("help_about_author_lbl");
        authorLbl.setMaximumSize(d);
        add( authorLbl );

        JLabel authorName = new JLabel( app_def.getAuthor() );
        authorName.setName("help_about_author_value");
        authorName.setMaximumSize(d);
        add( authorName );

    }
    
}
