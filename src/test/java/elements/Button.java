package elements;

import static com.codeborne.selenide.Selenide.$x;

public class Button {

    private String buttonLocator = "//android.widget.Button[@text='%s']";
    public void clickButton(String text){
        $x(String.format(buttonLocator,text)).click();
    }
}
