package org.AbstractComponents;

import org.PageObjectModel.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponents {
    WebDriver driver;

    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//button[@routerlink = '/dashboard/myorders']")
    WebElement order;

    public OrderPage goToOrdersPage(){
        order.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }


    public void waitForElementToAppear(By findBy){

        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
        w.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebElementToAppear(WebElement findBy){

        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
        w.until(ExpectedConditions.visibilityOf(findBy));
    }
    public void waitForElementToDisappear(By findBy) {

        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
        w.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
    }

}
