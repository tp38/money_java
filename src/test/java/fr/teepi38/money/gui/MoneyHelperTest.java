package fr.teepi38.money.gui;

import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

import static org.assertj.swing.launcher.ApplicationLauncher.application;

import java.io.FileInputStream;
import java.util.Properties;

// import org.assertj.swing.fixture.JMenuItemFixture;

import static org.assertj.swing.finder.WindowFinder.findFrame;


import fr.teepi38.money.Money;

public class MoneyHelperTest extends AssertJSwingJUnitTestCase
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
        // JMenuItemFixture item = findFrame("money_main_frame").using(robot()).menuItemWithPath("Aide");
        // if( ! item.isEnabled() ) {
        //     item.target().setEnabled(true);
        // }
    }

    @Test
    public void help_menu_have_an_aboutItem() {
        findFrame("money_main_frame").using(robot())
            .menuItemWithPath("Aide", "A propos de ...")
            .requireEnabled()
            .requireVisible();
    }  

    @Test
    public void aboutItem_show_a_dialog_box() {
        findFrame("money_main_frame").using(robot())
            .menuItemWithPath("Aide", "A propos de ...")
            .click();
        findFrame("money_main_frame").using(robot())
            .optionPane()
            .requireVisible()
            .requireTitle("A propos de ...")
            .requirePlainMessage()
            .requireMessage(appName + "\nv" + appVersion + "\n" + appDate + "\nhttps://github.com/tp38/money_java/blob/main/README.md" );
        
    }  

}
