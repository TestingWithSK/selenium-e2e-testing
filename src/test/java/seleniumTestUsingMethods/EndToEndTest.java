package seleniumTestUsingMethods;

public class EndToEndTest {

    public static void main(String[] args) {

        Functionality functionality = new Functionality();

        functionality.launchApplication("https://www.saucedemo.com/");
        functionality.login("standard_user", "secret_sauce");
        functionality.selectProduct("Sauce Labs Onesie");
        functionality.verifyProductDetailsPage("Sauce Labs Onesie");
        functionality.addProductInCart();
        functionality.goToCartPage();
        functionality.goToCheckoutPage();
        functionality.enterPersonalDetails("Shubham", "Kumar", "123455");
        functionality.verifyProductCheckoutPageAndCompleteCheckout("Sauce Labs Onesie");
        functionality.verifySuccessMessage();
        functionality.closeBrowser();

    }
}
