package fr.teepi38.money.gui.welcome;

import javax.swing.JFrame;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WelcomePanelTests {
    private FrameFixture ff;

    class InnerClassFrame extends JFrame {
        public InnerClassFrame() {
            add( new WelcomePanel() );
            pack();
        }
    }    

    @BeforeAll
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    public void setUp() {
        JFrame frame = GuiActionRunner.execute( () -> new InnerClassFrame() );
        ff = new FrameFixture(frame);
        ff.show();
    }

    @Test
    public void should_have_2_components() 
    // throws InterruptedException 
    {
        ff.panel("image_panel").requireVisible();
        ff.panel("db_chooser_panel").requireVisible();
        // Thread.sleep(3000);
    }
    
    @AfterEach
    public void tearDown() {
        ff.cleanUp();
    }    
}
