package fr.teepi38.money.gui.help;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import javax.swing.JFrame;

public class HelpReadmePanelTest  extends AssertJSwingJUnitTestCase {
    private FrameFixture ff;

    class HelpReadmePanelFrame extends JFrame {
    public HelpReadmePanelFrame() {
        add( new HelpReadmePanel() );
        pack();
        }
    }

    @Override
    public void onSetUp() {
        JFrame frame = GuiActionRunner.execute( () -> new HelpReadmePanelFrame() );
        ff = new FrameFixture(robot(), frame);
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

}


