package fr.teepi38.money.gui.help;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.swing.JFrame;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.teepi38.money.AppDef;

public class HelpManagerTest {
    private FrameFixture ff;
    private AppDef app;
    private SimpleDateFormat sdf;
    private HelpManager hm;

    @BeforeAll
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    public void onSetUp() {
        app = AppDef.builder().build();
        sdf = new SimpleDateFormat("dd.MM.yyyy");

        Properties props = new Properties();
        try ( FileInputStream fis = new FileInputStream("src/main/resources/conf.properties") ) {
            props.load( fis );
            app.setName( props.getProperty("application.name") );
            app.setVersion( props.getProperty("application.version") );
            app.setDate( sdf.parse( props.getProperty("application.date") ) );
            app.setAuthor( props.getProperty("application.author") );
        } catch( Exception e ) {
            // System.out.println( "impossible d'ouvrir le fichier conf.properties ");
            e.printStackTrace();
        }

        JFrame frame = GuiActionRunner.execute( () -> new JFrame() );
        ff = new FrameFixture(frame);
        ff.show();

        hm = new HelpManager( app );
    }

    @Test
    public void have_a_about_method() {
        GuiActionRunner.execute( () -> ff.target().add( hm.about() ) );
        ff.panel("help_about_pane");
    }

    @Test
    public void have_a_readme_method() {
        GuiActionRunner.execute( () -> ff.target().add( hm.readme() ) );
        ff.textBox("help_readme_editor");
    }

    @AfterEach
    public void tearDown() {
        ff.cleanUp();
    }
    
}
