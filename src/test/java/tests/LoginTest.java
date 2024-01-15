package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginTest extends BaseTest {

    String testData = "Login _,.'/Логин!!№;%?(^^^^LoginLoginLoginLoginLoginLoginLoginLoginLogin";
    String expected;
    String actual;

    @Test
    @DisplayName("Страница Login должна открываться")
    void loginPageShouldBeOpened() {
        step("Проверить, что страница Login открыта", () -> {
            loginPage.pageIsOpened("Вход в Alfa-Test");
        });
    }

    @Test
    @DisplayName("Пользователь должен быть авторизован, введя валидный логин и пароль")
    void userShouldBeAuthoriseWithValidLoginAndPassword() {
        step("Ввести валидный логин и пароль", () -> {
            loginPage.setValueInEmailAndPassword("Login", "Password");
        });
        step("Нажать кнопку Вход", () -> {
            loginPage.clickConfirmButton();
        });
        step("Ожидаемый результат: Пользователь авторизован, пользователь просматривает страницу Main", () -> {
            mainPage.pageIsOpened("Вход в Alfa-Test выполнен");
        });
    }

    @CsvFileSource(resources = "/testData/testData.csv")
    @ParameterizedTest(name = "При введении логина: {0} и пароля {1}, текст о ошибке должен быть: {2}")
    void userShouldNotBeAuthoriseWithWrongLoginAndPassword(String login, String pass, String errorMessage) {
        step("Ввести логин и пароль", () -> {
            loginPage.setValueInEmailAndPassword(login, pass);
        });
        step("Нажать кнопку Вход", () -> {
            loginPage.clickConfirmButton();
        });
        step("Ожидаемый результат: Текст полученной ошибки должен соответствовать требованиям", () -> {
            loginPage.getErrorMessageAndCompareText(errorMessage);
        });
    }

    @Test
    @DisplayName("При введении запрещенных символов они должны обрезаться")
    void unresolvedCharactersShouldBeTrimmed(){
        step("Ввести в поле Логин данные, содержащие запрещенные символы",()->{
            loginPage.setValueInEmailAndPassword(testData,"");
        });
        step("Получить текст из поля Логин",()->{
            actual = loginPage.getTextFromLoginInput();
        });
        step("Применить регулярное выражение, что бы обрезать запрещенные символы", ()->{
            expected = loginPage.trimUnresolvedCharacters(actual);
        });
        step("Ожидаемый результат: При вставке недопустимые символы должны быть обрезаны",()->{
            assertEquals(expected, actual);
        });
    }

}
