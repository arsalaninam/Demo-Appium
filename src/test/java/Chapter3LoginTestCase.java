import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class Chapter3LoginTestCase {

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
    }

    @Test
    public void loginTest() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement screen = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Login Screen")));
        screen.click();

        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("username")));
        username.sendKeys("alice");

        WebElement password = driver.findElement(MobileBy.AccessibilityId("password"));
        password.sendKeys("mypassword");

        WebElement loginButton = driver.findElement(MobileBy.AccessibilityId("loginBtn"));
        loginButton.click();

        WebElement loginText = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        MobileBy.xpath("//android.widget.TextView[contains(@text, 'You are logged in')]"))
        );
        assert (loginText.getText().contains("alice"));
    }

    @After
    public void tearUp() {
        if (driver != null) {
            driver.quit();
        }
    }
}
