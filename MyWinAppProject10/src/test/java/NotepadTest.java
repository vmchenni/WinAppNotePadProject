import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//import sun.plugin2.os.windows.Windows;

import java.net.URL;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

//import static com.sun.org.apache.xml.internal.serialize.LineSeparator.Windows;

public class NotepadTest {
    private static WindowsDriver notepadSession = null;
    public static String getDate(){
        LocalDate date = LocalDate.now();
        return date.toString();
    }
    @BeforeClass
    public static void setUp() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("app", "C:\\WINDOWS\\system32\\notepad.exe");
            capabilities.setCapability("platformName","Windows");
            capabilities.setCapability("deviceName", "WindowsPC");
            notepadSession = new WindowsDriver(new URL("http://127.0.0.1:4723/"), capabilities);
            notepadSession.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @AfterMethod
    public void cleanApp(){
        notepadSession.quit();
        setUp();
    }
    @AfterSuite
    public void tearDown(){
        notepadSession.quit();
    }
    @Test
    public void checkAboutWindow() {
        notepadSession.findElementByName("Help").click();
        notepadSession.findElementByName("About Notepad").click();
        notepadSession.findElementByName("OK").click();
    }
    @Test
    public void sendTestText(){
        notepadSession.findElementByClassName("Edit").sendKeys(getDate());
        notepadSession.findElementByClassName("Edit").clear();
    }
    @Test()
    public void pressTimeAndDateButton(){
        notepadSession.findElementByName("Edit").click();
        notepadSession.findElementByAccessibilityId("26").click();
        Assert.assertNotNull(notepadSession.findElementByClassName("Edit"));
        notepadSession.findElementByClassName("Edit").clear();
    }
}
