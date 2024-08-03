package org.Tests;


import org.PageObjectModel.CartPage;
import org.PageObjectModel.OrderConfirmationPage;
import org.PageObjectModel.PaymentPage;
import org.PageObjectModel.ProductCatalog;
import org.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidation extends BaseTest {

    @Test
    public void inValidLogin() throws IOException {

        loginPage.loginData("jmam@gmail.com","Test@123");
        Assert.assertEquals("Incorrect email or password." , loginPage.getErrorMessage());

    }
}
