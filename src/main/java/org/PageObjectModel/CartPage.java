package org.PageObjectModel;

import org.AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponents {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }



    @FindBy(xpath = "//div[@class ='cartSection']/h3")
    List<WebElement> cartList;

    @FindBy(xpath = "//ul/li/button[@class ='btn btn-primary']")
    WebElement checkout;




    public List<WebElement> getProductsFromCart() {
        return cartList;

    }

    public Boolean validatingProductName(String productName) {
        Boolean match = getProductsFromCart().stream().anyMatch(c -> c.getText().equalsIgnoreCase(productName));
        return match;
    }

    public PaymentPage checkoutCart() {
        checkout.click();
        PaymentPage paymentPage = new PaymentPage(driver);
        return paymentPage;
    }




}


