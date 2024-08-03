package org.PageObjectModel;

import org.AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.WebElementToJsonConverter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractComponents {


    WebDriver driver;

    public LoginPage(WebDriver driver){
        super(driver);
        this.driver =driver;
        PageFactory.initElements(driver,this);

    }

    @FindBy(id ="userEmail")
    WebElement userEmail;

    @FindBy(xpath ="//input[@id='userPassword']")
    WebElement userPassword;

    @FindBy(id ="login")
    WebElement login;

    @FindBy(css="[class*='flyInOut']")
    WebElement errorMessage;

    public ProductCatalog loginData(String email, String password){

        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        login.click();
        ProductCatalog productCatalog = new ProductCatalog(driver);
        return productCatalog;

    }
    public void goToUrl(String url){

        driver.get(url);
    }

    public String getErrorMessage() {
        waitForWebElementToAppear(errorMessage);
        String error = errorMessage.getText();
        return error;
    }

}

