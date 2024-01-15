package elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Input {
    private String inputLocator = "//android.widget.EditText[@text='%s']";

    public void setValueInInput(String label, String value) {
        $x(String.format(inputLocator, label)).sendKeys(value);
    }




}
