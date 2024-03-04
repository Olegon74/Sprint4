package ru.yandex.praktikum.pageobject.constants;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ru.yandex.praktikum.pageobject.constants.CreateOrderButton.DOWN_BUTTON;
import static ru.yandex.praktikum.pageobject.constants.CreateOrderButton.UP_BUTTON;


public class HomePage {


    WebDriver driver;
    private final By homeHeader = By.xpath(".//div[@class='Home_FirstPart__3g6vG']");
    private final By upOrderButton = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[@class='Button_Button__ra12g']");

    private final By downOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_UltraBig__UU3Lp']"); //.//button[@class='Button_Button__ra12g Button_UltraBig__UU3Lp']"
    private final By questionsHeader = By.className("Home_FourPart__1uthg");

    public HomePage(WebDriver driver) {
        this.driver = driver;

    }

    //метод ожидания загрузки главной страницы
    public HomePage waitForLoadHomePage() {
        new WebDriverWait(driver, 15).until(driver -> (driver.findElement(homeHeader).getText() != null
                && !driver.findElement(homeHeader).getText().isEmpty()
        ));
        return this;
    }


    //метод ожидания загрузки ответа на вопрос
    public void waitLoadAfterClickQuestion(By accordionLabel) {
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(accordionLabel).getText() != null
                && !driver.findElement(accordionLabel).getText().isEmpty()
        ));
    }

    //метод прокрутки к блоку "Вопросы о важном"
    public HomePage scrollToQuestions() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(questionsHeader));
        return this;
    }

    //метод прокрутки ко второй кнопке "Заказать"
    public HomePage scrollToDownOrderButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(downOrderButton));
        return this;
    }

    public HomePage clickUpOrderButton() {
        driver.findElement(upOrderButton).click();
        return this;
    }

    public HomePage clickDownOrderButton() {
        driver.findElement(downOrderButton).click();
        return this;
    }


    public void сlickTheOrderOneButton(Enum button) {
        if (button.equals(UP_BUTTON)) {
            clickUpOrderButton();
        }
    }
    public void clickTheOrderTwoButton() {
        if (equals(DOWN_BUTTON)) {
            scrollToDownOrderButton();
            clickDownOrderButton();
        }
    }


    public HomePage clickQuestion(By question) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(question))
                .click();
        return this;
    }
}

