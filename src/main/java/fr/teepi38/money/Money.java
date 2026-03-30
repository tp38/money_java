package fr.teepi38.money;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import fr.teepi38.money.gui.InfoMessage;
import fr.teepi38.money.gui.WelcomePanel;
/**
 * Main class for couchdb mney client
 * @author : <a href="mailto:thierry.probst@free.fr">Thierry Probst</a>
 * @version 0.3, 21/03/2026
 * @since 0.0.0
 */
public class Money extends JFrame
{

    private MoneyControler money_controler;

    // protected HelpManager help;
    protected AppDef app;
    protected MoneyMenu main_menu_bar;
    protected JPanel current_panel;
    protected InfoMessage info_msg;

    /**
     * constructor
     * @param parent the parent frame
     * @since 0.3
     * @see javax.swing.JFrame
     */
    public Money( AppDef app ) {
        // the frame container
        super(app.getName() + " - " + app.getVersion());
        setName("money_frame");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener( new ApplicationExit() );
        setPreferredSize( new Dimension( 800, 600 ) );
        setLocation(200, 200);

        // the main panel with a border layout
        JPanel mainPane = new JPanel( new BorderLayout() );
        mainPane.setName("money_panel");
        mainPane.setOpaque(true);
        setContentPane(mainPane);

        // help = new HelpManager(app);

        // menu on the north zone
        money_controler = new MoneyControler( this );
        main_menu_bar = new MoneyMenu( money_controler );
        add( main_menu_bar, BorderLayout.PAGE_START );

        // welcome image on the center zone
        WelcomePanel welcomePane = new WelcomePanel();
        current_panel = welcomePane;
        add( welcomePane, BorderLayout.CENTER );

        // info_msg on the south zone
        info_msg = new InfoMessage();
        SimpleDateFormat sdf = new SimpleDateFormat( "dd.MM.yyyy" );
        info_msg.setText("Bienvenue sur " + app.getName() + " v" + app.getVersion() + " (" + sdf.format( app.getDate() ) + ")");
        add(info_msg, BorderLayout.PAGE_END);

        pack();
        setVisible(true);
    }


    /**
     * read some static data from conf.properties 
     * @since 0.2
     */
    public static AppDef readProperties() {
        AppDef app = AppDef.builder().build();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        Properties props = new Properties();
        try ( FileInputStream fis = new FileInputStream("src/main/resources/conf.properties") ) {
            props.load( fis );
            app.setName( props.getProperty("application.name") );
            app.setVersion( props.getProperty("application.version") );
            app.setDate( sdf.parse( props.getProperty("application.date") ) );
        } catch( Exception e ) {
            // System.out.println( "impossible d'ouvrir le fichier conf.properties ");
            e.printStackTrace();
        }
        return app;
    }


    /**
     * the main function
     * 
     * @param args a String array contening all CLI options
     * @since 0.0
     * @throws Exception exception
     */
    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AppDef app = readProperties();
                new Money( app );
            }
        });

    }
    
}



