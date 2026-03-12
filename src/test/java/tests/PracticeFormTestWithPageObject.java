package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.util.Map;

import static enums.ResultTableEnums.*;

public class PracticeFormTestWithPageObject extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void successfulRegistrationTest() {
        registrationPage.openPage();
        registrationPage
                .setFirstName("alex")
                .setLastName("petrov")
                .setEmail("alex.petrov@gmail.com")
                .setGender("Other")
                .setUserNumber("7123456789")
                .setDateOfBirth("30", "July", "2008")
                .setSubjectsInput("Maths")
                .setHobbies("Reading")
                .uploadPicture("img/test1.jpg")
                .setCurrentAddress("Pushkina str 2")
                .setState("NCR")
                .setCity("Delhi")
                .clickSubmitButton()

                .checkResultTitle()
                .checkResultTable(Map.of(
                        STUDENT_NAME, "alex petrov",
                        STUDENT_EMAIL, "alex.petrov@gmail.com",
                        GENDER, "Other",
                        MOBILE, "7123456789",
                        DATE_OF_BIRTH, "30 July,2008",
                        SUBJECTS, "Maths",
                        HOBBIES, "Reading",
                        PICTURE, "test1.jpg",
                        ADDRESS, "Pushkina str 2",
                        STATE_AND_CITY, "NCR Delhi"));
    }

    @Test
    void requiredFieldsFormTest() {
        registrationPage.openPage();
        registrationPage
                .setFirstName("alex")
                .setLastName("petrov")
                .setGender("Other")
                .setUserNumber("7123456789")
                .clickSubmitButton()

                .checkResultTitle()
                .checkResultTable(Map.of(
                        STUDENT_NAME, "alex petrov",
                        GENDER, "Other",
                        MOBILE, "7123456789"));
    }

    @Test
    void invalidPhoneRegistrationTest() {
        registrationPage.openPage();
        registrationPage
                .setFirstName("alex")
                .setLastName("petrov")
                .setGender("Other")
                .setUserNumber("7123456md7")
                .clickSubmitButton()

                .checkTitleMissing();
    }
}
