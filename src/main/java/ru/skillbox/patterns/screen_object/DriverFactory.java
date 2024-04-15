package ru.skillbox.patterns.screen_object;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.MobileCapabilityType.NO_RESET;

public class DriverFactory {

    AppiumDriver<?> driver;

    public AppiumDriver<?> setUp(Platform platform) throws MalformedURLException {
        switch (platform) {
            case ANDROID:
                return createAndroidDriver();
            case IOS:
                return createIOSDriver();
            default:
                throw new IllegalArgumentException("No such Platform!");
        }
    private IOSDriver<?> createIOSDriver() throws MalformedURLException{
            return null;
        }

        public AndroidDriver<?> createAndroidDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "ANDROID");
        capabilities.setCapability(APP_PACKAGE, "ru.filit.mvideo.b2c");
        capabilities.setCapability(APP_ACTIVITY, ".ui.splash.view.SplashScreen");
        capabilities.setCapability(NO_RESET, true);
        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver<>(remoteUrl, capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver = EventFiringWebDriverFactori.getEventFiringWebDriver(driver, new EventListener());

        return (AndroidDriver<?>) driver;
    }
}
