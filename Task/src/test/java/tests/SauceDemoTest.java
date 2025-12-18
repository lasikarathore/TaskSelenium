package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.*;

public class SauceDemoTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test(priority = 1)
    public void loginTest() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.urlContains("inventory.html"));
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @Test(priority = 2)
    public void productPageValidation() {
        Assert.assertTrue(driver.findElement(By.className("title")).isDisplayed());

        List<WebElement> products = driver.findElements(By.className("inventory_item"));
        Assert.assertTrue(products.size() > 0);

        Assert.assertTrue(driver.findElement(By.className("shopping_cart_link")).isDisplayed());
    }

    @Test(priority = 3)
    public void sortByNameAToZ() {
        Select select = new Select(driver.findElement(By.className("product_sort_container")));
        select.selectByVisibleText("Name (A to Z)");

        List<WebElement> names = driver.findElements(By.className("inventory_item_name"));
        List<String> uiNames = new ArrayList<>();

        for (WebElement e : names) {
            uiNames.add(e.getText());
        }

        List<String> sortedNames = new ArrayList<>(uiNames);
        Collections.sort(sortedNames);

        Assert.assertEquals(uiNames, sortedNames);
    }

    @Test(priority = 4)
    public void sortByPriceHighToLow() {
        Select select = new Select(driver.findElement(By.className("product_sort_container")));
        select.selectByVisibleText("Price (high to low)");

        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
        List<Double> uiPrices = new ArrayList<>();

        for (WebElement e : prices) {
            uiPrices.add(Double.parseDouble(e.getText().replace("$", "")));
        }

        List<Double> sortedPrices = new ArrayList<>(uiPrices);
        sortedPrices.sort(Collections.reverseOrder());

        Assert.assertEquals(uiPrices, sortedPrices);
    }

    @Test(priority = 5)
    public void addToCart() {

        // Wait until products are visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("inventory_item")));

        // Get only "Add to Cart" buttons
        List<WebElement> addToCartButtons =
                driver.findElements(By.className("btn_inventory"));

        // Safety check
        Assert.assertFalse(addToCartButtons.isEmpty(),
                "No Add to Cart buttons found!");

        // Click first Add to Cart button
        addToCartButtons.get(0).click();

        // Verify cart badge
        WebElement badge = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("shopping_cart_badge")));

        Assert.assertEquals(badge.getText(), "1");

        // Open cart
        driver.findElement(By.className("shopping_cart_link")).click();

        // Verify product in cart
        Assert.assertTrue(driver.findElement(
                By.className("inventory_item_name")).isDisplayed());
    }


    @Test(priority = 6)
    public void checkoutFlow() {

        // Ensure we are on cart page
        wait.until(ExpectedConditions.urlContains("cart.html"));

        // Click Checkout
        driver.findElement(By.id("checkout")).click();

        // Step 1: Checkout Information
        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));

        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("500001");
        driver.findElement(By.id("continue")).click();

        // Step 2: Checkout Overview
        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));

        // Verify Finish button is present BEFORE clicking
        WebElement finishButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("finish")));

        finishButton.click();

        // Step 3: Order Complete
        wait.until(ExpectedConditions.urlContains("checkout-complete.html"));

        Assert.assertTrue(driver.findElement(
                        By.className("complete-header")).isDisplayed(),
                "Order confirmation not displayed");
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}