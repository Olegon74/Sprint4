package pageObject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.pageObject.constants.HomePage;

import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.pageObject.constants.HomePageConstants.*;

@RunWith(Parameterized.class)
public class HomePageTest {
    private WebDriver driver;
    private final String site = "https://qa-scooter.praktikum-services.ru/";
    private final By question;
    private final By answer;
    private final By labelResult;
    private final String expected;

    public HomePageTest(By question, By answer, By labelResult, String expected) {
        this.question = question;
        this.answer = answer;
        this.labelResult = labelResult;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {QUESTION_32, ANSWER_32, ITEM_ANSWER_32, TEXT_ANSWER_32},
                {QUESTION_33, ANSWER_33, ITEM_ANSWER_33, TEXT_ANSWER_33},

        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(site);
    }

    @After
    public void teardown() {
        driver = new ChromeDriver();
        driver.quit();
    }

    @Test
    public void checkQuestions() {
        new HomePage(driver)
                .waitForLoadHomePage()
                .scrollToQuestions()
                .clickQuestion(question)
                .waitLoadAfterClickQuestion(labelResult);
        String result = driver.findElement(answer).getText();

        assertEquals(expected, result);
    }
}

