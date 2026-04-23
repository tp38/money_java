package fr.teepi38.money.gui.welcome;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import fr.teepi38.money.dao.Credential;
import fr.teepi38.money.dao.couchdb.RelaxDb;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

/**
 * A panel with a list of known RelaxDb and a button to connect to the selected one.
 * RelaxDb are read from resources/database.json
 * 
 * @author : <a href="mailto:thierry.probst@free.fr">Thierry Probst</a>
 * @version 0.0, 16/04/2026
 * @since 0.0.2
 * @see javax.swing.JPanel
 * @see java.awt.event.ActionListener
 */
public class DbChooserPanel extends JPanel 
// implements ActionListener 
{

    private DefaultListModel<RelaxDb> data;
    private JList<RelaxDb> db_list;
    private JButton db_connect_btn;
    private RelaxDb current_db;
    private Credential current_cred;
    private JTextField login_txt;
    private JPasswordField password_txt;

    /**
     * constructor : create UI :
     * a panel with :
     *  - a jlist to choose the working database
     *  - a text field to set the login
     *  - a password field  to set ... the password
     *  - and a connect button to validate all data
     * @since 0.0
     */
    public DbChooserPanel() {
        super( new GridBagLayout() );

        Dimension lbl_size = new Dimension(150, 20);

        setName("db_chooser_panel");
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5, 5, 5, 5);
        gc.anchor = GridBagConstraints.WEST;

        readDataFromFile("src/main/resources/databases.json");

        // databases label
        JLabel databases_lbl = new JLabel("Databases :");
        databases_lbl.setName("databases_lbl");

        gc.gridx = 0; gc.gridy = 0; gc.gridwidth = GridBagConstraints.REMAINDER;
        add( databases_lbl, gc );

        // databases list
        db_list = new JList<>(data);
        db_list.setName("db_list");
        db_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        db_list.clearSelection();
        db_list.setCellRenderer( new DbCellRenderer() );

        JScrollPane sc = new JScrollPane( db_list );
        sc.setMinimumSize( new Dimension(600, 480));

        gc.gridx = 0; gc.gridy = 1;
        add(sc, gc);

        // login label
        JLabel login_lbl = new JLabel( "Login :");
        login_lbl.setName( "login_lbl" );
        login_lbl.setPreferredSize(lbl_size);

        gc.gridx = 0; gc.gridy = 2;
        add( login_lbl, gc );

        // login textfield
        login_txt = new JTextField();
        login_txt.setName("login_txt");
        login_txt.setPreferredSize(lbl_size);

        gc.gridx = 0; gc.gridy = 3;
        add( login_txt, gc );

        // login label
        JLabel password_lbl = new JLabel( "Password :");
        password_lbl.setName( "password_lbl" );

        gc.gridx = 0; gc.gridy = 4;
        add( password_lbl, gc );

        // login password field
        password_txt = new JPasswordField();
        password_txt.setName("password_txt");
        password_txt.setPreferredSize(lbl_size);

        gc.gridx = 0; gc.gridy = 5;
        add( password_txt, gc );

        // connect button
        db_connect_btn = new JButton("Connexion");
        db_connect_btn.setName("db_connect_btn");
        // db_connect_btn.addActionListener( this );
        gc.gridx = 0; gc.gridy = 6; gc.gridwidth = 1;
        add( db_connect_btn, gc );
    }

    public void setActionListener( ActionListener l ) {
        db_connect_btn.addActionListener(l);
    }

    /**
     * return the current Credential object
     * @return a Credential object with login and password
     * @since 0.0
     * @see fr.teepi38.money.dao.Credential
     */
    public Credential getCredential() {
        char[] password = password_txt.getPassword();
        String pwd = new String( password );
        if( ! login_txt.getText().equals( "" ) && ! pwd.equals( "" ) ) {
            current_cred = Credential.builder().login( login_txt.getText()).pwd( pwd ).build();
        } else {
            current_cred = null;
        }
        for(int i=0; i < password.length ; i++ ) { password[i] = 0; }
        return current_cred;
    }

    /**
     * return the current RelaxDb object
     * @return a RelaxDb object with the URI of selected database
     * @since 0.0
     * @see fr.teepi38.money.dao.couchdb.RelaxDb
     */
    public RelaxDb getRelaxDb() {
        if( db_list.getSelectedValuesList().size() == 1  ) {
            current_db = db_list.getSelectedValue();
        } else {
            current_db = null;
        }
        return current_db;
    } 

    /**
     * read RelaxDb from a specific json file
     * @param path a string with path from root of project
     * @since 0.0.2
     */
    private void readDataFromFile( String path ) {
        // read data from file and store them in a DefaultListModel
        data = new DefaultListModel<>();

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try ( InputStream json = new FileInputStream( path ) ) {
            ArrayList<RelaxDb> dbs = mapper.readValue( json, mapper.getTypeFactory()
                .constructCollectionType(ArrayList.class, RelaxDb.class) );
            dbs.forEach( db -> data.addElement( db ) );
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    /**
     * cell renderer for DbList
     * @author <a href="mailto:thierry.probst@free.fr">Thierry Probst</a>
     * @version 0.0
     * @since 0.0.2
     * @see javax.swing.ListCellRenderer
     * @see javax.swing.JLabel
     */
    class DbCellRenderer extends JLabel implements ListCellRenderer<Object> {

        /**
         * constructor
         * @since 0.0.2
         */
        public DbCellRenderer() {
            setOpaque( true );
        }

        /**
         * render the cell
         * @since 0.0.2
         * @param list the selected JList
         * @param value the object
         * @param index the index of the object in the list
         * @param isSelected boolean true if the cell is selected
         * @param cellHasFocus boolean true if the cell has focus
         * @return return a JLabel for DbCellRenderer
         */
        public Component getListCellRendererComponent( JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus ) {
            Color background;
            Color foreground;
            
            setText( ((RelaxDb)value).summary() );

            if (isSelected) {
                background = Color.LIGHT_GRAY;
                foreground = Color.BLUE;
            } else {
                background = Color.WHITE;
                foreground = Color.DARK_GRAY;
            };

            setBackground(background);
            setForeground(foreground);        
            
            return this;
        }
    }

}

