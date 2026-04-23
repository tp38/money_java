package fr.teepi38.money.gui.help;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

import javax.swing.JFrame;

public class HelpReadmePanelTest {
    private FrameFixture ff;

    class HelpReadmePanelFrame extends JFrame {
    public HelpReadmePanelFrame() {
        add( new HelpReadmePanel() );
        pack();
        }
    }

    @BeforeAll
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    public void onSetUp() {
        JFrame frame = GuiActionRunner.execute( () -> new HelpReadmePanelFrame() );
        ff = new FrameFixture(frame);
        ff.show();
    }

    @Test
    public void is_named() {
        ff.panel("help_readme_pane").scrollPane("help_readme_scrollpane");
    }

    @Test
    public void have_an_editor_pane_with_author() {
        String text = ff.panel("help_readme_pane").textBox("help_readme_editor").requireNotEditable().target().getText();
        assertTrue( text.contains("author") );
    }

    @Test
    public void have_an_editor_pane_with_changelog() {
        String text = ff.panel("help_readme_pane").textBox("help_readme_editor").requireNotEditable().target().getText();
        assertTrue( text.contains("changelog") );
    }

    @AfterEach
    public void tearDown() {
        ff.cleanUp();
    }

}


