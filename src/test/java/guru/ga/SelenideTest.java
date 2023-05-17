package guru.ga;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class SelenideTest extends TestBase {

    private static final String REPOSITORY_NAME = "eroshenkoam/allure-example",
            ISSUE_NAME = "issue_to_test_allure_report";

    @Test
    public void checkIssueNameTest() {
        open("https://github.com");
        $(".header-search-input").click();
        $(".header-search-input").sendKeys("eroshenkoam/allure-example");
        $(".header-search-input").submit();
        $(linkText("eroshenkoam/allure-example")).click();
        $("#issues-tab").click();
        $("#issue_81").shouldHave(text("issue_to_test_allure_report"));
    }

    @Test
    void checkIssueNameWithLambdaStepsTest() {
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий " + REPOSITORY_NAME, () -> {
            $(".header-search-input").sendKeys(REPOSITORY_NAME);
            $(".header-search-input").submit();
        });
        step("Кликаем по ссылке репозитория " + REPOSITORY_NAME, () -> {
            $(linkText(REPOSITORY_NAME)).click();
        });
        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем, что для issue 81" + " отображается название: " + ISSUE_NAME, () -> {
            $("#issue_81").shouldHave(text(ISSUE_NAME));
        });
    }

    @Test
    void checkIssueNameWithStepsTest() {
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY_NAME);
        steps.clickOnRepositoryLink(REPOSITORY_NAME);
        steps.openIssuesTab();
        steps.checkNameForIssueWithNumber(ISSUE_NAME);
    }
}