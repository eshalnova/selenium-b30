package selenium.b30.task19.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/*Главная страница*/
public class MainPage extends Page {

    @FindBy(css="div.name")
    public WebElement product;

    @FindBy(css="Online Store | My Store")
    public String mainCheck;

    @FindBy(css = "div#cart a.link")
    public WebElement cartButton;

    public void selectProduct (){
        product.click();
    }

    public void goToCart (){
        cartButton.click();
        wait.until(titleIs("Checkout | My Store"));
    }

    public void mainPageOpen(){
        driver.get("http://localhost/litecart/");
    }

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
