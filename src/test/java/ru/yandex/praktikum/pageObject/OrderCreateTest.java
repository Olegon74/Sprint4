package ru.yandex.praktikum.pageObject;

//import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.pageObject.constants.AboutRenter;
import ru.yandex.praktikum.pageObject.constants.AboutScooter;
import ru.yandex.praktikum.pageObject.constants.HomePage;
import ru.yandex.praktikum.pageObject.constants.PopUpWindow;

import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.pageObject.constants.CreateOrderButton.DOWN_BUTTON;
import static ru.yandex.praktikum.pageObject.constants.CreateOrderButton.UP_BUTTON;
import static ru.yandex.praktikum.pageObject.constants.RentDurationConstants.*;
import static ru.yandex.praktikum.pageObject.constants.ScooterColours.BLACK;
import static ru.yandex.praktikum.pageObject.constants.ScooterColours.GREY;

@RunWith(Parameterized.class)
public class OrderCreateTest {
    private WebDriver driver;
    private final String site = "https://qa-scooter.praktikum-services.ru/";
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
    private final Enum button;

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
                {DOWN_BUTTON, "Петя", "Петин", "Адрес 23", 7, "7988844442222", "28.02.2024", THREE_DAYS, GREY, "comments two"},
                {DOWN_BUTTON, "Имя Три", "Фамилия", "Адрес 3", 10, "790033111111", "27.02.2024", FOUR_DAYS, BLACK, "comments three"},
        };
    }

    @Before
    public void startUp() {
        driver = new FirefoxDriver();
        driver.get(site);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCreateOrderWithUpButton() {
        new HomePage(driver)
                .waitForLoadHomePage()
                .clickCreateOrderButton(button);

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

        assertTrue(popUpWindow.getHeaderAfterCreateOrder().contains(expectedHeader));
    }
}


