package fr.teepi38.money.gui.welcome;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import fr.teepi38.money.CouchDatabase;

public class DbChooserTest extends AssertJSwingJUnitTestCase {
    private FrameFixture ff;
    private ArrayList<CouchDatabase> dbs;

    class DbChooserFrame extends JFrame {
        public DbChooserFrame() {
            add( new DbChooser() );
            pack();
        }
    }    

    @Override
    protected void onSetUp() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        InputStream json = new FileInputStream( "src/main/resources/databases.json" );
        dbs = mapper.readValue( json, mapper.getTypeFactory()
                .constructCollectionType(ArrayList.class, CouchDatabase.class) );
        json.close();

        JFrame frame = GuiActionRunner.execute( () -> new DbChooserFrame() );
        ff = new FrameFixture(robot(), frame);
        ff.show();
    }
    
    @Test
    public void should_read_data_from_db_text_file() {
        ArrayList<String> expected_strings = new ArrayList<>();
        dbs.forEach( db -> expected_strings.add( db.summary() ) );
               
        String[] values = ff.list("db_list").contents();
        assertArrayEquals( expected_strings.toArray(), values);
    }

    @Test
    public void list_should_be_in_single_selection_mode() /*throws InterruptedException*/ {
        assertEquals( ListSelectionModel.SINGLE_SELECTION, ff.list("db_list").target().getSelectionMode() );
        // Thread.sleep(5000);
    }

    @Test
    public void should_have_a_connect_button_enabled_for_local() {
        JButtonFixture btn = ff.button("db_connect_btn");
        btn.requireDisabled();
        ff.list("db_list").clickItem(0);
        btn.requireEnabled();
    }

    @Test
    public void should_have_a_replicate_button_enabled_for_local() {
        JButtonFixture btn = ff.button("db_replicate_btn");
        btn.requireDisabled();
        ff.list("db_list").clickItem(1);
        btn.requireDisabled();
        ff.list("db_list").clickItem(0);
        btn.requireEnabled();
    }

    @Test
    public void should_have_a_connect_button_enabled_for_distant() {
        JButtonFixture btn = ff.button("db_connect_btn");
        btn.requireDisabled();
        ff.list("db_list").clickItem(1);
        btn.requireEnabled();
    }

    @Test
    public void should_have_replicate_button_disabled_for_distant() {
        JButtonFixture btn = ff.button("db_replicate_btn");
        btn.requireDisabled();
        ff.list("db_list").clickItem(1);
        btn.requireDisabled();
    }

}
