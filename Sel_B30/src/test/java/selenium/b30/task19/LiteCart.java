package selenium.b30.task19;

import org.openqa.selenium.WebDriver;
import selenium.b30.task19.pages.CartPage;
import selenium.b30.task19.pages.MainPage;
import selenium.b30.task19.pages.ProductPage;

public class LiteCart extends TestBase19 {

    private CartPage cartPage;
    private MainPage mainPage;
    private ProductPage productPage;

    public LiteCart(WebDriver driver){
        cartPage = new CartPage(driver);
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
    }

    public void open(){
        mainPage.mainPageOpen();
    }

    public void addProduct(){
        mainPage.selectProduct();
        productPage.addProduct();
    }

    public void openCart(){
        mainPage.goToCart();
    }

    public void removeAllProducts(){
        cartPage.delAllProduct();
    }

}
