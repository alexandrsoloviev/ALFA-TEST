package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;


public class BrowserstackDriver implements WebDriverProvider {


    private static final String USER = ConfigReader.browserStackConfig.user();
    private static final String KEY = ConfigReader.browserStackConfig.key();
    private static final String APP = ConfigReader.browserStackConfig.app();
    private static final String DEVICE = ConfigReader.browserStackConfig.device();
    private static final String OS_VERSION = ConfigReader.browserStackConfig.os_version();


    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setCapability("appium:app", APP);
        options.setCapability("appium:deviceName", DEVICE);
        options.setCapability("appium:platformVersion", OS_VERSION);
        try {
           return new AndroidDriver(new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", USER , KEY)), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}