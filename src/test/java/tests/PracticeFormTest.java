package tests;

import org.junit.jupiter.api.Test;
import utils.TestData;

import java.util.Map;

import static enums.ResultTableEnums.*;


public class PracticeFormTest extends TestBase {
    TestData testData = new TestData();

    @Test
    void successfulRegistrationTest() {
        registrationPage.openPage();
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
    void requiredFieldsFormTest() {
        registrationPage.openPage();
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
    void invalidPhoneRegistrationTest() {
        registrationPage.openPage();
        registrationPage
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGender(testData.gender)
                .setUserNumber("71234567md")
                .clickSubmitButton()

                .checkTitleMissing();
    }
}
