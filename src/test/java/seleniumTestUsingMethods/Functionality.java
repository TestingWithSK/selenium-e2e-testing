package seleniumTestUsingMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Functionality {

    public static WebDriver driver;
    public static String productTitle;

    public void launchApplication(String url) {
        try {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(url);
        } catch (Exception e) {
            throw new RuntimeException("Failed to launch application.");
        }
    }

    public void login(String username, String password) {
        try {
            WebElement input_username = driver.findElement(By.id("user-name"));
            input_username.sendKeys(username);
            WebElement input_password = driver.findElement(By.id("password"));
            input_password.sendKeys(password);
            WebElement button_login = driver.findElement(By.id("login-button"));
            Thread.sleep(1000);
            button_login.click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to login.");
        }
    }

    public void selectProduct(String productName) {
        try {
            List<WebElement> allProductsName = driver.findElements(By.className("inventory_item_name"));
            for (WebElement product : allProductsName) {
                if (product.getText().equals(productName)) {
                    Thread.sleep(1000);
                    product.click();
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to select product.");
        }
    }

    public void verifyProductDetailsPage(String productName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("back-to-products"))));

            WebElement label_productTitle = driver.findElement(By.className("inventory_details_name"));
            productTitle = label_productTitle.getText();

            if (productTitle.equals(productName)) {
                System.out.println("Product Details: Product matched.");
            } else {
                System.out.println("Product Details: Wrong item.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Fail to verify product details on product details page.");
        }
    }

    public void addProductInCart() {
        try {
            WebElement button_addToCart = driver.findElement(By.cssSelector("button[id^='add-to-cart']"));
            Thread.sleep(1000);
            button_addToCart.click();
        } catch (Exception e) {
            throw new RuntimeException("Fail to add product in cart.");
        }
    }

    public void goToCartPage() {
        try {
            WebElement button_goToCart = driver.findElement(By.className("shopping_cart_link"));
            Thread.sleep(1000);
            button_goToCart.click();
        } catch (Exception e) {
            throw new RuntimeException("Fail to navigate to cart page.");
        }
    }

    public void goToCheckoutPage() {
        try {
            WebElement button_checkout = driver.findElement(By.id("checkout"));
            Thread.sleep(1000);
            button_checkout.click();
        } catch (Exception e) {
            throw new RuntimeException("Fail to navigate to checkout page.");
        }
    }

    public void enterPersonalDetails(String firstName, String lastName, String zipCode) {
        try {
            WebElement input_firstName = driver.findElement(By.id("first-name"));
            input_firstName.sendKeys(firstName);

            WebElement input_lastName = driver.findElement(By.id("last-name"));
            input_lastName.sendKeys(lastName);

            WebElement input_zipCode = driver.findElement(By.id("postal-code"));
            input_zipCode.sendKeys(zipCode);

            WebElement button_continue = driver.findElement(By.id("continue"));
            Thread.sleep(1000);
            button_continue.click();
        } catch (Exception e) {
            throw new RuntimeException("Fail to enter personal details.");
        }
    }

    public void verifyProductCheckoutPageAndCompleteCheckout(String productName) {
        try {
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
        } catch (Exception e) {
            throw new RuntimeException("Fail to verify product details on checkout page.");
        }
    }

    public void verifySuccessMessage() {
        try {
            String expectMessage = "THANK YOU FOR YOUR ORDER";
            WebElement label_completeMessage = driver.findElement(By.className("complete-header"));
            String actualMessage = label_completeMessage.getText();

            if (expectMessage.equals(actualMessage)) {
                System.out.println("Success: Item checkout.");
            } else {
                System.out.println("Fail: Item checkout.");
            }

            Thread.sleep(1000);
        } catch (Exception e) {
            throw new RuntimeException("Fail to verify success message.");
        }
    }

    public void closeBrowser() {
        try {
            driver.quit();
        } catch (Exception e) {
            throw new RuntimeException("Fail to close browser.");
        }
    }

}
