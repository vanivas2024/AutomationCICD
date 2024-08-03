package org.Tests;


import Data.DataReader;
import org.PageObjectModel.*;
import org.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class StandAlone extends BaseTest {


    @Test(dataProvider = "getData")
    public void submitOrder(HashMap<String,String> input) throws IOException {

        ProductCatalog productCatalog = loginPage.loginData(input.get("email"), input.get("password"));
        List<WebElement> products = productCatalog.getProducts();
        WebElement prod = productCatalog.getProductName(input.get("productName"));
        productCatalog.addProductToCart(input.get("productName"));
        CartPage cartPage = productCatalog.goToCart();
        List<WebElement> cartList = cartPage.getProductsFromCart();
        Boolean equal = cartPage.validatingProductName(input.get("productName"));
        Assert.assertTrue(equal);
        PaymentPage paymentPage = cartPage.checkoutCart();
        paymentPage.autoSuggestCountryName(input.get("countryName"));
        paymentPage.SelectCountry(input.get("countryName"));
        OrderConfirmationPage orderConfirmationPage = paymentPage.paymentCompleted();
        String actualText = orderConfirmationPage.validatingThankYouMessage();
        Assert.assertEquals(actualText, input.get("orderMessage"));
    }

    @Test(dependsOnMethods = {"submitOrder"}, dataProvider = "getData")
    public void orderHistory(HashMap<String,String> input) {

        ProductCatalog productCatalog = loginPage.loginData(input.get("email"), input.get("password"));
        OrderPage orderPage = productCatalog.goToOrdersPage();
        Boolean name = orderPage.verifyOrderDisplay(input.get("productName"));
        Assert.assertTrue(name,input.get("productName"));
    }

    @DataProvider
    public Object[] [] getData() throws IOException {
        DataReader dataReader = new DataReader();
        List<HashMap<String,String>> data =dataReader.getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Data\\ProductData.json");
        return new Object[][] {{data.get(0)}};
    }
}

