package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.RegistrationPage;
import utils.TestData;

import static com.codeborne.selenide.Selenide.$;

public class TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll

    static void setUpBeforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void setUpAfterEach() {
        Configuration.holdBrowserOpen = false;
        //Selenide.closeWebDriver();
    }
}
