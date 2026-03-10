package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

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
            stateList = $("#state"),
            stateWrapper = $("#stateCity-wrapper"),
            cityList = $("#city"),
            cityWrapper = $("#stateCity-wrapper"),
            visibleWindow = $(".modal-dialog"),
            visibleHeader = $("#example-modal-sizes-title-lg"),
            resultTable = $(".table-responsive");


    CalendarComponent calendarComponent = new CalendarComponent();

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        //Selenide.executeJavaScript("$('#fisedban').remove()");
        //Selenide.executeJavaScript("$('footer').remove()");
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
        uploadPicture.uploadFromClasspath(value);
        return this;
    }

    public RegistrationPage setCurrentAddress(String value) {
        submitButton.scrollTo();
        currentAddress.setValue(value);
        return this;
    }

    public RegistrationPage setState(String value) {
        stateList.click();
        stateWrapper.$(byText(value)).click();;
        return this;
    }

    public RegistrationPage setCity(String value) {
        cityList.click();
        cityWrapper.$(byText(value)).click();;
        return this;
    }

    public RegistrationPage setButton() {
        submitButton.click();
        return this;
    }

    public RegistrationPage appearModalWindow(String value) {
        visibleWindow.should(appear);
        visibleHeader.shouldHave(exactText(value));
        return this;
    }

    public RegistrationPage checkResult(String key, String value) {
        resultTable.$(byText(key)).parent()
                .shouldHave(text(value));
        return this;
    }
}
