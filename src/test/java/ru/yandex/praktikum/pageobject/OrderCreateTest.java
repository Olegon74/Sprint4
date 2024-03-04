package ru.yandex.praktikum.pageobject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.pageobject.constants.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.pageobject.constants.CreateOrderButton.DOWN_BUTTON;
import static ru.yandex.praktikum.pageobject.constants.CreateOrderButton.UP_BUTTON;
import static ru.yandex.praktikum.pageobject.constants.RentDurationConstants.*;
import static ru.yandex.praktikum.pageobject.constants.ScooterColours.BLACK;
import static ru.yandex.praktikum.pageobject.constants.ScooterColours.GREY;

@RunWith(Parameterized.class)
public class OrderCreateTest {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final int stateMetroNumber;
    private final String telephoneNumber;
    private final String date;
    private final String duration;
    private final Enum colour;
    private final String comment;
    private final String expectedHeader = "Заказ оформлен";
    private final String expectedFormTitle = "Для кого самокат";
    private final Enum git button;

    public OrderCreateTest(Enum button, String name, String surname, String address, int stateMetroNumber, String telephoneNumber,
                           String date, String duration, Enum colour, String comment) {
        this.button = button;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.stateMetroNumber = stateMetroNumber;
        this.telephoneNumber = telephoneNumber;
        this.date = date;
        this.duration = duration;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {UP_BUTTON, "Тест", "Тестов", "Адрес 12", 12, "799900003333", "29.02.2024", ONE_DAY, GREY, "comments one"},
                {UP_BUTTON, "Вася", "Фамилия", "Адрес 13", 7, "7988323243234", "01.03.2024", TWO_DAYS, BLACK, "comments two"},

        };
    }

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
    public void testCreateOrderUpButton() {
        new HomePage(driver)
                .waitForLoadHomePage()
                .сlickTheOrderOneButton(button);


        new AboutRenter(driver)
                .waitForLoadOrderPage()
                .inputName(name)
                .inputSurname(surname)
                .inputAddress(address)
                .changeStateMetro(stateMetroNumber)
                .inputTelephone(telephoneNumber)
                .clickNextButton();

        new AboutScooter(driver)
                .waitAboutRentHeader()
                .inputDate(date)
                .inputDuration(duration)
                .changeColour(colour)
                .inputComment(comment)
                .clickButtonCreateOrder();

        PopUpWindow popUpWindow = new PopUpWindow(driver);
        popUpWindow.clickButtonYes();

        if (button.equals(UP_BUTTON)) {
            assertTrue(popUpWindow.getHeaderAfterCreateOrder().contains(expectedHeader));
        }
    }


    @Test
    public void testCreateOrderWithOrderButton() {
        new HomePage(driver)
                .waitForLoadHomePage()
                .clickTheOrderTwoButton(button);

        OrderFormPage orderFormPage = new OrderFormPage(driver); // Создаем экземпляр OrderFormPage
         if (button.equals(DOWN_BUTTON)) {
             assertTrue(orderFormPage.getTheTitleAfterClickingOnTheOrderButton().contains(expectedFormTitle));
        }
    }
}




