package pages;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;

import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage{
    private final SelenideElement MAIN_PAGE_TITLE = $(AppiumBy.cssSelector(".android.widget.TextView"));


    @Override
    public void pageIsOpened(String titleText) {
        checkIsOpened(MAIN_PAGE_TITLE, titleText);
    }
}
