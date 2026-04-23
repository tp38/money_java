package fr.teepi38.money.gui.welcome;

import javax.swing.JFrame;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ImagePanelTests {
    private FrameFixture ff;

    class ImageFrame extends JFrame {
        public ImageFrame() {
            add( new ImagePanel() );
            pack();
        }
    }    

    @BeforeAll
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    public void setUp() {
        JFrame frame = GuiActionRunner.execute( () -> new ImageFrame() );
        ff = new FrameFixture(frame);
        ff.show();
    }

    @Test
    public void should_have_a_label_named() {
        ff.label("welcome_img");

    }

    @AfterEach
    public void tearDown() {
        ff.cleanUp();
    }    
}
