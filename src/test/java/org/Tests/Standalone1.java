package org.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Standalone1 {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("jmax@gmail.com");
        driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Test@123");
        driver.findElement(By.id("login")).click();
        List<WebElement> products = driver.findElements(By.xpath("//div[@class = 'card-body']/h5/b"));
        WebElement prod = products.stream().filter(s-> s.getText().contains("ZARA")).findFirst().orElse(null);
        prod.findElement(By.xpath("//div[@class = 'card-body']/button[2]")).click();
        w.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
        w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
        driver.findElement(By.xpath("//button[@routerlink= '/dashboard/cart']")).click();
        List<WebElement> cart = driver.findElements(By.xpath("//div[@class ='cartSection']/h3"));
        Boolean match = cart.stream().anyMatch(c-> c.getText().equalsIgnoreCase("ZARA COAT 3"));
        Assert.assertTrue(match);
        driver.findElement(By.xpath("//ul/li/button[@class ='btn btn-primary']")).click();
        w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Select Country']")));
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
        List <WebElement> country = driver.findElements(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']"));
        WebElement ctry = country.stream().filter(li-> li.getText().equalsIgnoreCase("India")).findFirst().orElse(null);
        ctry.click();
        driver.findElement(By.xpath("//div[@class='actions']/a")).click();
        w.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
        String text = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(text, "THANKYOU FOR THE ORDER.");






    }
}
