package fr.teepi38.money.gui.welcome;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import fr.teepi38.money.CouchDatabase;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

public class DbChooser extends JPanel {
    private DefaultListModel<CouchDatabase> data;
    private JList<CouchDatabase> db_list;
    private JButton db_connect_btn, db_replicate_btn;

    public DbChooser() {
        super( new GridBagLayout() );
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5, 5, 5, 5);
        gc.anchor = GridBagConstraints.WEST;

        readDataFromFile("src/main/resources/databases.json");

        db_list = new JList<>(data);
        db_list.setName("db_list");
        db_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        db_list.clearSelection();
        db_list.addListSelectionListener( new DbChooserActionListener() );
        db_list.setCellRenderer( new DbCellRenderer() );


        JScrollPane sc = new JScrollPane( db_list );
        sc.setMinimumSize( new Dimension(600, 480));
        
        gc.gridx = 0; gc.gridy = 0; gc.gridwidth = GridBagConstraints.REMAINDER;
        add( sc, gc );

        db_connect_btn = new JButton("Connexion");
        db_connect_btn.setName("db_connect_btn");
        db_connect_btn.setEnabled(false);
        gc.gridx = 0; gc.gridy = 1; gc.gridwidth = 1;
        add( db_connect_btn, gc );

        db_replicate_btn = new JButton("Replication");
        db_replicate_btn.setName("db_replicate_btn");
        db_replicate_btn.setEnabled(false);
        gc.gridx = 1; gc.gridy = 1; gc.gridwidth = 1;
        add( db_replicate_btn, gc );

    }


    private void readDataFromFile( String path ) {
        // read data from file and store them in a DefaultListModel
        data = new DefaultListModel<>();

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try ( InputStream json = new FileInputStream( path ) ) {
            ArrayList<CouchDatabase> dbs = mapper.readValue( json, mapper.getTypeFactory()
                .constructCollectionType(ArrayList.class, CouchDatabase.class) );
            dbs.forEach( db -> data.addElement( db ) );
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    class DbChooserActionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if( ! db_list.isSelectionEmpty() ) {
                List<CouchDatabase> selected_values = db_list.getSelectedValuesList();
                if( selected_values.size() == 1 ) {
                    db_connect_btn.setEnabled(false);
                    db_replicate_btn.setEnabled(false);
                    CouchDatabase v = selected_values.get(0);
                    if( v != null ) {
                        db_connect_btn.setEnabled(true);
                        if( v.repl_init() != null ) {
                            db_replicate_btn.setEnabled(true);
                        }
                    }
                }
            } else {
                db_connect_btn.setEnabled(false);
                db_replicate_btn.setEnabled(false);
            }
        }
        
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
        
        setText( ((CouchDatabase)value).summary() );

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

