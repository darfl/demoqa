package pages.components;

import com.codeborne.selenide.SelenideElement;
import enums.ResultTableEnums;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultTableComponent {

    private final SelenideElement
            resultTitle = $("#example-modal-sizes-title-lg"),
            resultTable = $(".table-responsive");

    public ResultTableComponent checkTitle() {
        resultTitle.shouldHave(exactText("Thanks for submitting the form"));
        return this;
    }

    public ResultTableComponent checkTable(ResultTableEnums key, String value) {
        System.out.println(key + " " + value);
        resultTable.$(byText(key.toString())).parent()
                .shouldHave(text(value));
        return this;
    }

    public ResultTableComponent checkTitleMissing() {
        resultTitle.shouldNotBe(visible);
        return this;
    }
}