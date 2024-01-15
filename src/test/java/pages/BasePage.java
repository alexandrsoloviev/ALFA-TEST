package pages;

import com.codeborne.selenide.SelenideElement;
import elements.Button;
import elements.Input;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public abstract class BasePage {

    Input input = new Input();
    Button button = new Button();


    public abstract void pageIsOpened(String titleText);

    void checkIsOpened(SelenideElement pageTitle, String text){
        pageTitle.shouldBe(visible).shouldHave(text(text));
    }
}
