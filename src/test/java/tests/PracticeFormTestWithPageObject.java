package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class PracticeFormTestWithPageObject extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void successfulRegistrationTest() {
        registrationPage.openPage()
                .setFirstName("alex")
                .setLastName("petrov")
                .setEmail("alex.petrov@gmail.com")
                .setGender("Other")
                .setUserNumber("7123456789")
                .setDateOfBirth("30","July","2008")
                .setSubjectsInput("Maths")
                .setHobbies("Reading")
                .uploadPicture("img/test1.jpg")
                .setCurrentAddress("Pushkina str 2")
                .setState("NCR")
                .setCity("Delhi")
                .setButton()
                .appearModalWindow("Thanks for submitting the form")
                .checkResult("Student Name", "alex petrov")
                .checkResult("Student Email", "alex.petrov@gmail.com")
                .checkResult("Gender", "Other")
                .checkResult("Mobile", "7123456789");
    }
}
