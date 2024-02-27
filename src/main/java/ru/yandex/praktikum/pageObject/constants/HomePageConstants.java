package ru.yandex.praktikum.pageObject.constants;

import org.openqa.selenium.By;

public class HomePageConstants {
    public static final By QUESTION_32 = By.id("accordion__heading-32");
    public static final By QUESTION_33 = By.id("accordion__heading-33");
    public static final By ANSWER_32 = By.id("accordion__panel-32");
    public static final By ANSWER_33 = By.id("accordion__panel-33");
    public static final By ITEM_ANSWER_32 = By.xpath("..//div[@data-accordion-component='AccordionItemPanel' and @aria-labelledby='accordion__heading-0']");
    public static final By ITEM_ANSWER_33 = By.xpath("..//div[@data-accordion-component='AccordionItemPanel' and @aria-labelledby='accordion__heading-1']");
    public static final String TEXT_ANSWER_32 = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    public static final String TEXT_ANSWER_33 = "Пока что у нас так: один заказ — один самокат. " +
            "Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
}
