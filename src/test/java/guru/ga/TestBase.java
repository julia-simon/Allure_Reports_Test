package guru.ga;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll()
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "Chrome";
        Configuration.timeout = 3000;
        Configuration.holdBrowserOpen = true;
        WebDriverManager.chromedriver().setup();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
}
