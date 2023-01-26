package seleniumTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class EndToEndTest {

    public static void main(String[] args) throws InterruptedException {
        // Create the WebDriver instance
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to website
        driver.get("https://www.saucedemo.com/");

        // Login to the application
        WebElement input_username = driver.findElement(By.id("user-name"));
        input_username.sendKeys("standard_user");
        WebElement input_password = driver.findElement(By.id("password"));
        input_password.sendKeys("secret_sauce");
        WebElement button_login = driver.findElement(By.id("login-button"));
        // adding sleep method for slow execution.
        Thread.sleep(1000);
        button_login.click();

        // select a product - Product Name = Sauce Labs Onesie
        String productName = "Sauce Labs Onesie";
        List<WebElement> allProductsName = driver.findElements(By.className("inventory_item_name"));
        for (WebElement product : allProductsName) {
            if (product.getText().equals(productName)) {
                Thread.sleep(1000);
                product.click();
                break;
            }
        }

        // navigate to product detail page and verify the correct selected product
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("back-to-products"))));

        WebElement label_productTitle = driver.findElement(By.className("inventory_details_name"));
        String productTitle = label_productTitle.getText();

        if (productTitle.equals(productName)) {
            System.out.println("Product Details: Product matched.");
        } else {
            System.out.println("Product Details: Wrong item.");
        }

        // click add to cart button
        WebElement button_addToCart = driver.findElement(By.cssSelector("button[id^='add-to-cart']"));
        Thread.sleep(1000);
        button_addToCart.click();

        // go to cart page
        WebElement button_goToCart = driver.findElement(By.className("shopping_cart_link"));
        Thread.sleep(1000);
        button_goToCart.click();

        // click checkout button
        WebElement button_checkout = driver.findElement(By.id("checkout"));
        Thread.sleep(1000);
        button_checkout.click();

        // enter details and click continue
        String firstName = "Shubham";
        String lastName = "Kumar";
        String zipCode = "123455";

        WebElement input_firstName = driver.findElement(By.id("first-name"));
        input_firstName.sendKeys(firstName);

        WebElement input_lastName = driver.findElement(By.id("last-name"));
        input_lastName.sendKeys(lastName);

        WebElement input_zipCode = driver.findElement(By.id("postal-code"));
        input_zipCode.sendKeys(zipCode);

        WebElement button_continue = driver.findElement(By.id("continue"));
        Thread.sleep(1000);
        button_continue.click();

        // verify product on checkout page and click finish
        WebElement label_productTitleOnCheckout = driver.findElement(By.className("inventory_item_name"));
        productTitle = label_productTitleOnCheckout.getText();

        if (productTitle.equals(productName)) {
            System.out.println("Checkout: Product matched.");
        } else {
            System.out.println("Checkout: Wrong item.");
        }

        WebElement button_finish = driver.findElement(By.id("finish"));
        Thread.sleep(1000);
        button_finish.click();

        // verify
        String expectMessage = "THANK YOU FOR YOUR ORDER";
        WebElement label_completeMessage = driver.findElement(By.className("complete-header"));
        String actualMessage = label_completeMessage.getText();

        if (expectMessage.equals(actualMessage)) {
            System.out.println("Success: Item checkout.");
        } else {
            System.out.println("Fail: Item checkout.");
        }

        Thread.sleep(1000);

        // close the WebDriver instance
        driver.close();
    }

}
