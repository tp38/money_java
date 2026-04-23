package fr.teepi38.money.gui.help;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.teepi38.money.AppDef;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.swing.JFrame;

public class HelpAboutPanelTest {
    private AppDef app;
    private SimpleDateFormat sdf;
    private FrameFixture ff;


    class HelpAboutPanelFrame extends JFrame {
        public HelpAboutPanelFrame() {
            add( new HelpAboutPanel( app ) );
            pack();
        }
    }

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

        JFrame frame = GuiActionRunner.execute( () -> new HelpAboutPanelFrame() );
        ff = new FrameFixture(frame);
        ff.show();
    }

    @Test
    public void have_a_pane() {
        ff.panel("help_about_pane");
    }

    @Test
    public void have_an_application_line() {
        ff.panel("help_about_pane").label("help_about_app_lbl").requireVisible().requireText("application : ");
        ff.panel("help_about_pane").label("help_about_app_value").requireVisible().requireText(app.getName());
    }

    @Test
    public void have_a_version_line() {
        ff.panel("help_about_pane").label("help_about_version_lbl").requireVisible().requireText("version : ");
        ff.panel("help_about_pane").label("help_about_version_value").requireVisible().requireText(app.getVersion());
    }

    @Test
    public void have_a_date_line() {
        ff.panel("help_about_pane").label("help_about_date_lbl").requireVisible().requireText("date : ");
        ff.panel("help_about_pane").label("help_about_date_value").requireVisible().requireText( sdf.format( app.getDate() ) );
    }

    @Test
    public void have_a_author_line() {
        ff.panel("help_about_pane").label("help_about_author_lbl").requireVisible().requireText("auteur : ");
        ff.panel("help_about_pane").label("help_about_author_value").requireVisible().requireText( app.getAuthor() );
    }

    @AfterEach
    public void tearDown() {
        ff.cleanUp();
    }
}

