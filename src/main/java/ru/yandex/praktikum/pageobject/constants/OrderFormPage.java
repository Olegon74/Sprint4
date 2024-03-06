package ru.yandex.praktikum.pageobject.constants;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OrderFormPage {
    WebDriver driver;
    private static final By TITLE_OF_THE_ORDER_FORM = By.xpath(".//div[@class='Order_Header__BZXOb' and text()='Для кого самокат']");
    public OrderFormPage(WebDriver driver) {

        this.driver = driver;
    }

    public String getTheTitleAfterClickingOnTheOrderButton() {
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(TITLE_OF_THE_ORDER_FORM).getText() != null
                && !driver.findElement(TITLE_OF_THE_ORDER_FORM).getText().isEmpty()));

        return driver.findElement(TITLE_OF_THE_ORDER_FORM).getText();

    }
}
