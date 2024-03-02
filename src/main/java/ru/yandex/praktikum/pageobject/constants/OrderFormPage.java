package ru.yandex.praktikum.pageobject.constants;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderFormPage {
    public static WebDriver driver;
    private static final By titleOfTheOrderForm = By.xpath(".//div[@class='Order_Header__BZXOb' and text()='Для кого самокат']");
    public OrderFormPage(WebDriver driver) {
        this.driver = driver;
    }

    public static String getTheTitleAfterClickingOnTheOrderButton() {
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(titleOfTheOrderForm).getText() != null
                && !driver.findElement(titleOfTheOrderForm).getText().isEmpty()));

        return driver.findElement(titleOfTheOrderForm).getText();

    }
}
