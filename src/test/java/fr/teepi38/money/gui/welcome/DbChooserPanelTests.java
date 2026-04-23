package fr.teepi38.money.gui.welcome;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import fr.teepi38.money.dao.couchdb.RelaxDb;

public class DbChooserPanelTests {
    private FrameFixture ff;
    private ArrayList<RelaxDb> dbs;

    class DbChooserFrame extends JFrame {
        public DbChooserFrame() {
            add( new DbChooserPanel() );
            pack();
        }
    }    

    @BeforeAll
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    public void setUp() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // we must read data from databases.json

        try {
            InputStream json = new FileInputStream( "src/test/resources/databases.json" );
            dbs = mapper.readValue( json, mapper.getTypeFactory()
                    .constructCollectionType(ArrayList.class, RelaxDb.class) );
            json.close();
        } catch( Exception e) {
            e.printStackTrace();
        }

        JFrame frame = GuiActionRunner.execute( () -> new DbChooserFrame() );
        ff = new FrameFixture(frame);
        ff.show();
    }

    @Test
    public void should_have_a_jtextfield_named_login_txt() {
        ff.label("login_lbl").requireVisible();
        ff.textBox("login_txt").requireVisible().requireEditable();
    }

    @Test
    public void should_have_a_jpasswordfield_named_password_txt() {
        ff.label("password_lbl").requireVisible();
        ff.textBox("password_txt").requireVisible().requireEditable();
    }
    
    @Test
    public void should_read_data_from_databases_json_file() {
        ArrayList<String> expected_strings = new ArrayList<>();
        // see onSetup method
        dbs.forEach( db -> expected_strings.add( db.summary() ) );
               
        String[] values = ff.list("db_list").contents();
        assertArrayEquals( expected_strings.toArray(), values);
    }

    @Test
    public void list_should_be_in_single_selection_mode() 
    // throws InterruptedException 
    {
        ff.label("databases_lbl").requireVisible();
        ff.list("db_list").requireVisible();
        // Thread.sleep(3000);
        assertEquals( ListSelectionModel.SINGLE_SELECTION, ff.list("db_list").target().getSelectionMode() );
    }

    @Test
    public void should_have_a_valid_button() {
        ff.button("db_connect_btn").requireText("Connexion").requireVisible();
    }

    @AfterEach
    public void tearDown() {
        ff.cleanUp();
    }
}
