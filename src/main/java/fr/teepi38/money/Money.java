package fr.teepi38.money;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

import fr.teepi38.money.gui.MoneyHelper;
/**
 * Main class for couchdb mney client
 * @author : <a href="mailto:thierry.probst@free.fr">Thierry Probst</a>
 * @version 0.0.3, 18/02/2026
 * @since 0.0.0
 */
public class Money extends JSplitPane
{

    private static String appName;
    private static String appVersion;
    private static String appDate;

    private static JFrame frame;

    public Money( JFrame parent ) {
        super( JSplitPane.VERTICAL_SPLIT );
        frame = parent;

        setName("mainPane");

        // menu
        JMenuBar menubar = new JMenuBar();
        menubar.setName("menuBar");

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
        JMenuItem aboutItem = new JMenuItem("A propos de ...");
        aboutItem.addActionListener( new AboutActionListener() );
        menuHelp.add(aboutItem);

        menubar.add( menuDb );
        menubar.add(menuAccounts);
        menubar.add(menuCategories);
        menubar.add(menuSearch);
        menubar.add(menuHelp);

        frame.setJMenuBar( menubar );

        // welcome image
        try {
            File f = new File("src/main/ressources/cartoon-money.png" );
            BufferedImage bufferedImage = ImageIO.read( f );
            ImageIcon welcomeIcon = new ImageIcon(bufferedImage);
            JLabel welcomeImg = new JLabel(welcomeIcon);
            welcomeImg.setName("board_panel");
            welcomeImg.setToolTipText("cartoon-money.png");
            setTopComponent( welcomeImg );

        } catch ( IOException e ) {
            JLabel welcomLabel = new JLabel("impossible d'ouvrir cartoon-money.png");
            setTopComponent(welcomLabel);
            // e.printStackTrace();
        }


        // info_text
        JLabel info_text = new JLabel("Bienvenue sur " + appName + " v" + appVersion + " (" + appDate + ")");
        info_text.setName("info_text");
        setBottomComponent(info_text);
        setPreferredSize( new Dimension(800, 50));

        // setResizeWeight(0.9);
        // resetToPreferredSizes();
    }

    private class AboutActionListener implements ActionListener {
        public void actionPerformed( ActionEvent e ) {
            MoneyHelper moneyhelper = new MoneyHelper(frame);
            moneyhelper.about( appName, appVersion, appDate, "https://github.com/tp38/money_java/blob/main/README.md" );
        }
    }    

    public static void readProperties() {
        Properties props = new Properties();
        try ( FileInputStream fis = new FileInputStream("src/main/ressources/conf.properties") ) {
            props.load( fis );
            appName = props.getProperty("application.name");
            appVersion = props.getProperty("application.version");
            appDate = props.getProperty("application.date");
        } catch( Exception e ) {
            System.out.println( "impossible d'ouvrir le fichier conf.properties ");
            appName = new String( "MyMoneyRelax");
            appVersion = new String( "0.0.0" );
            appDate = new String("27.02.2026");
            // e.printStackTrace();
        }
    }

    public static void createAndShowGUI() {
        frame = new JFrame(appName + " - " + appVersion);
        frame.setName("money_main_frame");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener( new ApplicationExit() );
        frame.setPreferredSize( new Dimension( 800, 600 ) );
        frame.setLocation(200, 200);

        JComponent mainPane = new Money( frame );
        mainPane.setOpaque(true);
        frame.setContentPane(mainPane);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * the main function
     * 
     * @param args a String array contening all CLI options
     * @since 0.0.0
     * @throws Exception exception
     */
    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                readProperties();
                createAndShowGUI();
            }
        });

    }
}

class ApplicationExit extends WindowAdapter {
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

