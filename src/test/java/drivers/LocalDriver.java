package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;

public class LocalDriver implements WebDriverProvider {


    private static final String LOCAL_URL = ConfigReader.localConfig.localUrl();
    private static final String DEVICE_NAME = ConfigReader.localConfig.deviceName();
    private static final String PLATFORM_VERSION = ConfigReader.localConfig.platformVersion();
    private static String APP_PACKAGE = ConfigReader.localConfig.appPackage();
    private static String APP_ACTIVITY = ConfigReader.localConfig.appActivity();
    private static final String APK_PATH = ConfigReader.localConfig.apkPath();


    public static URL getAppiumServerUrl() {
        try {
            return new URL(LOCAL_URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {

        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);

        options.setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName(ANDROID)
                .setDeviceName(DEVICE_NAME)
                .setPlatformVersion(PLATFORM_VERSION)
                .setApp(getApk().getAbsolutePath())
                .setAppPackage(APP_PACKAGE)
                .setAppActivity(APP_ACTIVITY);
        new AndroidDriver(getAppiumServerUrl(), options);

        return new AndroidDriver(getAppiumServerUrl(), options);


    }

    private File getApk() {
        return new File(APK_PATH);
    }
}
