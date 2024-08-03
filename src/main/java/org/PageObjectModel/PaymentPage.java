package org.PageObjectModel;

import org.AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PaymentPage extends AbstractComponents {

    WebDriver driver;

    public PaymentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }


    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement country;

    @FindBy(xpath = "//button[@class='ta-item list-group-item ng-star-inserted']")
    List <WebElement> countryList;

    @FindBy(xpath ="//div[@class='actions']/a")
    WebElement placeOrder;


    By countryDropDown = By.xpath("//input[@placeholder='Select Country']");

    public void autoSuggestCountryName(String countryName) {
        waitForElementToAppear(countryDropDown);
        country.sendKeys(countryName);
    }
    public List<WebElement> getCountry() {
        return  countryList;

    }
    public void SelectCountry(String countryName){
        WebElement ctry = getCountry().stream().filter(li-> li.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null);
        ctry.click();
    }

    public OrderConfirmationPage paymentCompleted(){
        placeOrder.click();
        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
        return orderConfirmationPage;
    }




}


