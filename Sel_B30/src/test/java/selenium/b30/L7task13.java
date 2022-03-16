package selenium.b30;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class  L7task13 extends TestBase{

    @Test
    public void l7task13(){

        driver.get("http://localhost/litecart/");//Переход на страницу

        /*Добавление товара в корзину, повторяем три раза циклом*/
        for (int i = 0; i < 3; i++) {
            wait.until(titleIs("Online Store | My Store"));//каждый раз ждем чтобы загрузилась нужная страница
            /*Ищем первый товар*/
            WebElement product = driver.findElement(By.cssSelector("div.name"));
            String productName = product.getText();
            product.click();
            /*На странице товара добавляем его в корзину*/
            wait.until(textToBePresentInElement(driver.findElement(By.cssSelector("div#box-product.box h1.title")),productName));
            int quantity = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText());
            /*Проверяем есть ли у товара опция "Size"*/
            try {
                driver.findElement(By.cssSelector(".options select")).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);//Если есть - выбираем первое значение из списка
            }
            catch (Exception e){}//Если нет - ловим ошибку
            driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();
            wait.until(textToBePresentInElement(driver.findElement(By.cssSelector("span.quantity")),String.valueOf(quantity + 1)));
            driver.get("http://localhost/litecart/");
        }

        /*Удаляем товар из корзины*/
        driver.findElement(By.cssSelector("div#cart a.link")).click();
        int items = driver.findElements(By.cssSelector("ul.items li")).size();
        for (int i = 0; i < items; i++) { //Повторяем цикл по числу добавленных товаров - могло быть добавлено больше или меньше
            if(i == 0 && items >1){
                driver.findElement(By.cssSelector("ul.shortcuts > li")).click();
            }
            int lineCnt = driver.findElements(By.cssSelector("div#box-checkout-summary tr")).size();
            driver.findElement(By.cssSelector("li.item:nth-child(1) button[name='remove_cart_item']")).click();
            wait.until((WebDriver d) -> d.findElements(By.cssSelector("div#box-checkout-summary tr")).size() < lineCnt);
        }
    }
}
