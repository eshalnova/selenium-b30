package selenium.b30.task19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*Корзина*/

public class CartPage extends Page {

    @FindBy(css="button[name='remove_cart_item']")
    public WebElement delButton;

    @FindBy(css="ul.shortcuts > li")
    public WebElement shortcut;

    public int getTableLines(){
        return driver.findElements(By.cssSelector("div#box-checkout-summary tr")).size();
    }

    public int getCartSize(){
        return driver.findElements(By.cssSelector("ul.items li")).size();
    }

    public void delAllProduct() {
        int items = getCartSize();
        for (int i = 0; i < items; i++) {
            if(i == 0 && items >1){
                shortcut.click();
            }
            int lineCnt = getTableLines();
            delButton.click();
            wait.until((WebDriver d) -> d.findElements(By.cssSelector("div#box-checkout-summary tr")).size() < lineCnt);
        }
    }

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
