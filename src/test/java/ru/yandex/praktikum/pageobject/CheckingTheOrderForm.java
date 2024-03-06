package ru.yandex.praktikum.pageobject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.pageobject.constants.OrderForm;
import ru.yandex.praktikum.pageobject.constants.OrderFormPage;
import ru.yandex.praktikum.pageobject.constants.UrlConstants;

import static org.junit.Assert.assertTrue;

public class CheckingTheOrderForm {
    private final String expectedFormTitle = "Для кого самокат";
    WebDriver driver;
    @Before
    public void startUp() {
        driver = new FirefoxDriver();
        driver.get(UrlConstants.URL);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    public void tesTheAppearanceOfTheOrderForm() {
        new OrderForm(driver)
                .scrollToDownOrderButton()
                .clickDownOrderButton();

        OrderFormPage orderFormPage = new OrderFormPage(driver); // Создаем экземпляр OrderFormPage

        assertTrue(orderFormPage.getTheTitleAfterClickingOnTheOrderButton().contains(expectedFormTitle));
    }
}

