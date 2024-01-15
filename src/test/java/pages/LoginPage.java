package pages;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {

    private final SelenideElement LOGIN_PAGE_TITLE = $(AppiumBy.id("com.alfabank.qapp:id/tvTitle"));
    private final SelenideElement ERROR_MESSAGE = $(AppiumBy.id("com.alfabank.qapp:id/tvError"));
    private final SelenideElement USER_NAME_INPUT = $(AppiumBy.id("com.alfabank.qapp:id/etUsername"));

    @Override
    public void pageIsOpened(String titleText) {
        checkIsOpened(LOGIN_PAGE_TITLE, titleText);
    }

    public void setValueInEmailAndPassword(String login, String password) {
        input.setValueInInput("Логин", login);
        input.setValueInInput("Пароль", password);
    }

    public void clickConfirmButton() {
        button.clickButton("Вход");
    }

    public void getErrorMessageAndCompareText(String errorMessage) {
        ERROR_MESSAGE.shouldBe(visible).shouldHave(text(errorMessage));
    }


    public String trimUnresolvedCharacters(String str) {

        String text = str.replaceAll("[^A-Za-z.,/' _-]", "");

        return text.length() > 50 ? text.substring(0, 50) : text;
    }

    public String getTextFromLoginInput(){
       return USER_NAME_INPUT.getText();
    }


}
