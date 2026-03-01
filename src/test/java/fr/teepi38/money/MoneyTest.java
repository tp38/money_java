package fr.teepi38.money;

import static org.assertj.core.api.Assertions.*;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;
import static org.assertj.swing.launcher.ApplicationLauncher.application;
import static org.assertj.swing.finder.WindowFinder.findFrame;

import java.awt.Dimension;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.Icon;
import javax.swing.JLabel;

public class MoneyTest extends AssertJSwingJUnitTestCase
{
    private static String appName;
    private static String appVersion;
    private static String appDate;

    @Override
    public void onSetUp() {
        Properties props = new Properties();
        try ( FileInputStream fis = new FileInputStream("src/main/ressources/conf.properties") ) {
            props.load( fis );
            appName = props.getProperty("application.name");
            appVersion = props.getProperty("application.version");
            appDate = props.getProperty("application.date");
        } catch( Exception e ) {
            e.printStackTrace();
        }
        application(Money.class).start();
    }

    @Test
    public void window_have_a_title_and_some_dimensions() {
        findFrame("money_main_frame").using(robot()).requireTitle(appName + " - " + appVersion ).requireSize(new Dimension(800,600));
    }  

    @Test
    public void window_have_a_jpopupmenu_with_entry() {
        findFrame("money_main_frame").using(robot()).menuItemWithPath("Databases")
            .requireVisible()
            .requireDisabled();
        findFrame("money_main_frame").using(robot()).menuItemWithPath("Comptes")
            .requireVisible()
            .requireDisabled();
        findFrame("money_main_frame").using(robot()).menuItemWithPath("Categories")
            .requireVisible()
            .requireDisabled();
        findFrame("money_main_frame").using(robot()).menuItemWithPath("Recherches")
            .requireVisible()
            .requireDisabled();
        findFrame("money_main_frame").using(robot()).menuItemWithPath("Aide")
            .requireVisible()
            .requireDisabled();
    }

    @Test
    public void window_have_a_jlabel_with_a_cartoonMoney_image() {
        Dimension imgSize = new Dimension(500, 465);

        JLabel label = findFrame("money_main_frame").using(robot()).label("board_panel").requireVisible().requireToolTip("cartoon-money.png").target();
        Icon icon = GuiActionRunner.execute(() -> label.getIcon());
        assertThat(icon.getIconHeight()).isEqualTo((int)imgSize.getHeight());
        assertThat(icon.getIconWidth()).isEqualTo((int)imgSize.getWidth());
    }

    @Test
    public void window_have_a_jlabel_saying_welcome() {
        findFrame("money_main_frame").using(robot())
            .label("info_text")
            .requireText( "Bienvenue sur " + appName + " v" + appVersion + " (" + appDate + ")" );
    }

    @Test
    public void window_have_a_dialog_on_exit() {
        findFrame("money_main_frame").using(robot()).close();
        findFrame("money_main_frame").using(robot()).optionPane()
            .requireVisible()
            .requireTitle("Quitter MyMoneyRelax")
            .requireMessage("Êtes vous sur de vouloir quitter l'application ?")
            .noButton().click();
    }
}
