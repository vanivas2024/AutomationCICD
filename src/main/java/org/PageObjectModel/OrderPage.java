package org.PageObjectModel;

import org.AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponents {

    public WebDriver driver;
    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//table[@class ='table table-bordered table-hover ng-star-inserted']/tbody/tr/td[2]")
    List<WebElement> orderList;

    public Boolean verifyOrderDisplay(String productName) {
        Boolean name = orderList.stream().anyMatch(p-> p.getText().equalsIgnoreCase(productName));
        return name;
    }



}
