package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.TestData;

import java.util.Map;

import static enums.ResultTableEnums.*;

@Tag("regression")
@DisplayName("Регистрация")
public class PracticeFormTest extends TestBase {
    TestData testData = new TestData();

    @Test
    @Tag("positive")
    @DisplayName("Проверка успешной регистрации со всеми полями")
    void successfulRegistrationTest() {
        registrationPage
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setEmail(testData.userEmail)
                .setGender(testData.gender)
                .setUserNumber(testData.phoneNumber)
                .setDateOfBirth(testData.day, testData.month, testData.year)
                .setSubjectsInput(testData.subjects)
                .setHobbies(testData.hobbies)
                .uploadPicture(testData.uploadFile)
                .setCurrentAddress(testData.streetAddress)
                .setState(testData.state)
                .setCity(testData.city)
                .clickSubmitButton()

                .checkResultTitle()
                .checkResultTable(Map.of(
                        STUDENT_NAME, testData.firstName + " " + testData.lastName,
                        STUDENT_EMAIL, testData.userEmail,
                        GENDER, testData.gender,
                        MOBILE, testData.phoneNumber,
                        DATE_OF_BIRTH, testData.day + " " + testData.month + "," + testData.year,
                        SUBJECTS, testData.subjects,
                        HOBBIES, testData.hobbies,
                        PICTURE, testData.uploadFile,
                        ADDRESS, testData.streetAddress,
                        STATE_AND_CITY, testData.state + " " + testData.city));
    }

    @Test
    @DisplayName("Проверка регистрации с заполненными обязательными полями")
    void requiredFieldsFormTest() {
        registrationPage
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGender(testData.gender)
                .setUserNumber(testData.phoneNumber)
                .clickSubmitButton()

                .checkResultTitle()
                .checkResultTable(Map.of(
                        STUDENT_NAME, testData.firstName + " " + testData.lastName,
                        GENDER, testData.gender,
                        MOBILE, testData.phoneNumber));
    }

    @Test
    @Tag("negative")
    @DisplayName("Проверка регистрации с некорректным телефоном")
    void invalidPhoneRegistrationTest() {
        registrationPage
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGender(testData.gender)
                .setUserNumber(testData.invalidPhoneNumber + testData.randomString)
                .clickSubmitButton()

                .checkTitleMissing();
    }
}
