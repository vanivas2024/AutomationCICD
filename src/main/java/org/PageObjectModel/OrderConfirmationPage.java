package org.PageObjectModel;

import org.AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPage extends AbstractComponents {

    WebDriver driver;

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }



    @FindBy(tagName ="h1")
    WebElement message;

    By toastMessage = By.id("toast-container");


    public String validatingThankYouMessage(){
        waitForElementToAppear(toastMessage);
        String text =message.getText();
        return text;
    }




}



