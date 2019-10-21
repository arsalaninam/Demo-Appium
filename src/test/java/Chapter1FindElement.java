import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Chapter1FindElement {

    private static final String APP = "https://github.com/cloudgrey-io/the-app/releases/download/v1.9.0/TheApp-v1.9.0.apk";
    private static final String APPIUM = "http://localhost:4723/wd/hub";

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "9");
        cap.setCapability("deviceName", "AndroidEmulator");
        cap.setCapability("automationName", "UiAutomator2");
        cap.setCapability("app", APP);

        driver = new AndroidDriver(new URL(APPIUM), cap);
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
    }

    @Test
    public void test() {
        List<WebElement> elements = driver.findElements(MobileBy.AccessibilityId("Login Screen"));
        System.out.println(elements.size());
    }

    @After
    public void tearUp() {
        if (driver != null) {
            driver.quit();
        }
    }
}
