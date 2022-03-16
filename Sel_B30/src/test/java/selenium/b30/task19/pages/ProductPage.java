package selenium.b30.task19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

/*Страница товара*/
public class ProductPage extends Page {

    @FindBy(css = "div#box-product.box h1.title")
    public WebElement checkh1;

    @FindBy(css="button[name='add_cart_product']")
    public WebElement addButton;

    @FindBy(css=".options select")
    public WebElement productSize;

    @FindBy(className="quantity")
    public WebElement cart;

    public void addProduct(){
        int cartSize = Integer.parseInt(cart.getText());
        try {
            productSize.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        }
        catch (Exception e){}
        addButton.click();
        wait.until(textToBePresentInElement(cart,String.valueOf(cartSize + 1)));
    }

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
