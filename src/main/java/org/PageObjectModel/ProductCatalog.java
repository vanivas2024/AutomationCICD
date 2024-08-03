package org.PageObjectModel;

import org.AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalog extends AbstractComponents {

    WebDriver driver;

    public ProductCatalog(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//div[@class = 'card-body']/h5/b")
    List<WebElement> products;

    @FindBy(xpath = "//button[@routerlink= '/dashboard/cart']")
    WebElement goToCart;

    By addCart = By.xpath("//div[@class = 'card-body']/button[2]");
    By freezeDisappear = By.cssSelector(".ng-animating");
    By toastMessage = By.id("toast-container");


    public List<WebElement> getProducts() {
        return products;

    }

    public WebElement getProductName(String productName) {
        WebElement prod = getProducts().stream().filter(s ->
                s.getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
        return prod;
    }

    public void addProductToCart(String productName) {
        WebElement prod = getProductName(productName);
        prod.findElement(addCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(freezeDisappear);
    }

    public CartPage goToCart() {
        goToCart.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

}


