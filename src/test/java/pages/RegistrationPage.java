package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import enums.ResultTableEnums;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.ResultTableComponent;

import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectsInput  = $("#subjectsInput"),
            submitButton = $("#submit"),
            hobbiesWrapper = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture"),
            currentAddress = $("#currentAddress"),
            stateList = $("#react-select-3-input"),
            stateWrapper = $("#stateCity-wrapper"),
            cityList = $("#react-select-4-input"),
            cityWrapper = $("#stateCity-wrapper"),
            visibleWindow = $(".modal-dialog"),
            visibleHeader = $("#example-modal-sizes-title-lg"),
            resultTable = $(".table-responsive");


    CalendarComponent calendarComponent = new CalendarComponent();
    ResultTableComponent resultTableComponent = new ResultTableComponent();

    @Step("Открыть страницу /automation-practice-form и скрыть footer")
    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        //Selenide.executeJavaScript("$('#fisedban').remove()");
        Selenide.executeJavaScript("arguments[0].remove();", $("footer"));
        return this;
    }

    @Step("Ввести имя")
    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return new RegistrationPage();
    }

    @Step("Ввести фамилию")
    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    @Step("Ввести email")
    public RegistrationPage setEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    @Step("Указать пол")
    public RegistrationPage setGender(String value) {
        genderWrapper.$(byText(value)).click();
        return this;
    }

    @Step("Указать номер телефона")
    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    @Step("Указать дату рождения")
    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    @Step("Выбрать предметы")
    public RegistrationPage setSubjectsInput(String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    @Step("Выбрать хобби")
    public RegistrationPage setHobbies(String value) {
        hobbiesWrapper.$(byText(value)).click();
        return this;
    }

    @Step("Загрузить фото")
    public RegistrationPage uploadPicture(String value) {
        uploadPicture.uploadFromClasspath("img/" + value);
        return this;
    }

    @Step("Указать адрес")
    public RegistrationPage setCurrentAddress(String value) {
        submitButton.scrollTo();
        currentAddress.setValue(value);
        return this;
    }

    @Step("Выбрать штат {0}")
    public RegistrationPage setState(String value) {
        stateList.setValue(value).pressEnter();
        return this;
    }

    @Step("Выбрать город {0}")
    public RegistrationPage setCity(String value) {
        cityList.setValue(value).pressEnter();
        return this;
    }
    @Step("Нажать кнопку submit")
    public RegistrationPage clickSubmitButton() {
        //submitButton.scrollTo();
        actions().moveToElement($(submitButton)).perform();

        submitButton.click();
        return this;
    }


    @Step("Проверить наличие заголовка таблицы с введенными данными")
    public RegistrationPage checkResultTitle() {
        resultTableComponent.checkTitle();
        return this;
    }

    @Step("Проверить содержание таблицы с введенными данными")
    public RegistrationPage checkResultTable(Map<ResultTableEnums, String> results) {
        results.forEach((key, value) -> resultTableComponent.checkTable(key, value));
        return this;
    }
    @Step("Проверить отсутствие таблицы с введенными данными")
    public RegistrationPage checkTitleMissing() {
        resultTableComponent.checkTitleMissing() ;
        return this;
    }
}
