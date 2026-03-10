package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

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
                .setDateOfBirth("30","July","2008");


        $("#subjectsInput").setValue("Maths").pressEnter();


        $("#submit").scrollTo();
        $("#hobbiesWrapper").$(byText("Reading")).click();


        $("#uploadPicture").uploadFromClasspath("img/test1.jpg");

        $("#currentAddress").setValue("Pushkina str 2");


        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();

        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();


        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(exactText("Thanks for submitting the form"));


        $(".table-responsive").shouldHave(text("alex"),
                text("petrov"),
                text("alex.petrov@gmail.com"),
                text("7123456789"));


        registrationPage.checkResult("Student Name", "alex petrov")
                .checkResult("Student Email", "alex.petrov@gmail.com");
    }
}
