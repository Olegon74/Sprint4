package ru.yandex.praktikum.pageobject.constants;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class OrderForm {
    WebDriver driver;
    private final By downOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    public OrderForm(WebDriver driver) {
        this.driver = driver;

    }
    //метод прокрутки ко второй кнопке "Заказать"
    public OrderForm scrollToDownOrderButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(downOrderButton));
        return this;
    }

    public OrderForm clickDownOrderButton() {
        driver.findElement(downOrderButton).click();
        return this;
    }
}


