package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import enums.ResultTableEnums;
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

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        //Selenide.executeJavaScript("$('#fisedban').remove()");
        Selenide.executeJavaScript("arguments[0].remove();", $("footer"));
        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return new RegistrationPage();
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    public RegistrationPage setGender(String value) {
        genderWrapper.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationPage setSubjectsInput(String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage setHobbies(String value) {
        hobbiesWrapper.$(byText(value)).click();
        return this;
    }

    public RegistrationPage uploadPicture(String value) {
        uploadPicture.uploadFromClasspath("img/" + value);
        return this;
    }

    public RegistrationPage setCurrentAddress(String value) {
        submitButton.scrollTo();
        currentAddress.setValue(value);
        return this;
    }

    public RegistrationPage setState(String value) {
        stateList.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage setCity(String value) {
        cityList.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage clickSubmitButton() {
        //submitButton.scrollTo();
        actions().moveToElement($(submitButton)).perform();

        submitButton.click();
        return this;
    }


    public RegistrationPage checkResultTitle() {
        resultTableComponent.checkTitle();
        return this;
    }


    public RegistrationPage checkResultTable(Map<ResultTableEnums, String> results) {
        results.forEach((key, value) -> resultTableComponent.checkTable(key, value));
        return this;
    }

    public RegistrationPage checkTitleMissing() {
        resultTableComponent.checkTitleMissing() ;
        return this;
    }
}
