package ru.yandex.praktikum.pageobject.constants;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ru.yandex.praktikum.pageobject.constants.ScooterColours.*;


public class AboutScooter {

    WebDriver driver;
    private final By rentHeader = By.xpath(".//div[@class='Order_Content__bmtHS']");
    private final By date = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By durationRent = By.xpath(".//span[@class='Dropdown-arrow']");
    private final By colourBlack = By.id("black");
    private final By colourGrey = By.id("grey");
    private final By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By createOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");

    public AboutScooter(WebDriver driver) {
        this.driver = driver;
    }

    //метод ожидания загрузки страницы
    public AboutScooter waitAboutRentHeader() {
        new WebDriverWait(driver,10).until(driver -> (driver.findElement(rentHeader).getText() != null
                && !driver.findElement(rentHeader).getText().isEmpty()
        ));
        return this;
    }

    public AboutScooter inputDate(String newDate) {
        driver.findElement(date).sendKeys(newDate);
        return this;
    }

    public AboutScooter inputDuration(String newDuration) {
        driver.findElement(durationRent).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(By.className("Dropdown-menu"))).click();
        return this;
    }

    public AboutScooter changeColour(Enum colour) {
        if (colour.equals(BLACK)) {
            driver.findElement(colourBlack).click();
        } else if (colour.equals(GREY)) {
            driver.findElement(colourGrey).click();
        }
        return this;
    }

    public AboutScooter inputComment(String newComment) {
        driver.findElement(comment).sendKeys(newComment);
        return this;
    }

    public void clickButtonCreateOrder() {

        driver.findElement(createOrderButton).click();
    }
}



