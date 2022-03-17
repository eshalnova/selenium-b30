package selenium.b30;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.util.List;
import java.util.Random;

public class L6task12 extends TestBase {

    @Test
    public void l6task12(){

        String productName =  "Test " + new Random().nextInt(1000)+ " Duck " + new Random().nextInt(1000); //Задаем имя создаваемого элемента

        authorization("http://localhost/litecart/admin/login.php", "admin", "admin", "My Store");//авторизация в админке

        /*Переходим в раздел Catalog и ждем его загрузку*/
        driver.findElement(By.cssSelector("li[id=app-]:nth-child(2)")).click();
        wait.until(ExpectedConditions.urlToBe("http://localhost/litecart/admin/?app=catalog&doc=catalog"));
        /*Нажимаем кнопку добавления элемента и ждем загрузку формы*/
        driver.findElement(By.cssSelector("td#content a.button:nth-child(2)")).click();
        wait.until(ExpectedConditions.titleIs("Add New Product | My Store"));
        /*Заполняем данные на вкладке General*/
        driver.findElement(By.cssSelector("#tab-general input[name='status']")).click();
        driver.findElement(By.cssSelector("#tab-general input[name='name[en]']")).sendKeys(productName);
        driver.findElement(By.cssSelector("#tab-general input[name='code']")).sendKeys("testDuck1");
        driver.findElement(By.cssSelector("#tab-general input[name='categories[]'][data-name='Root']")).click();
        driver.findElement(By.cssSelector("#tab-general input[name='categories[]'][data-name='Rubber Ducks']")).click();
        WebElement quantity = driver.findElement(By.cssSelector("#tab-general input[name='quantity']"));
        quantity.clear();
        quantity.sendKeys("10,25");
        driver.findElement(By.cssSelector("#tab-general input[name='new_images[]']")).sendKeys(new File("src\\test\\java\\selenium\\b30\\files\\hedgehog.jpg").getAbsolutePath());
        /*Переходим на вкладку Information и заполняем данные*/
        driver.findElement(By.cssSelector("div.tabs ul.index a[href='#tab-information']")).click();
        driver.findElement(By.cssSelector("#tab-information select[name='manufacturer_id'] option[value='1']")).click();
        driver.findElement(By.cssSelector("#tab-information input[name='short_description[en]']")).sendKeys("This is a short description of the Red Star Duck");
        driver.findElement(By.cssSelector("#tab-information .trumbowyg-editor")).sendKeys("This is a full description of the Red Star Duck\n\rWhat a wonderful thing it is!");
        /*Переходим на вкладку Prices и заполняем данные*/
        driver.findElement(By.cssSelector("div.tabs ul.index a[href='#tab-prices']")).click();
        WebElement purchasePrice = driver.findElement(By.cssSelector("#tab-prices input[name='purchase_price']"));
        purchasePrice.clear();
        purchasePrice.sendKeys("15,5");
        driver.findElement(By.cssSelector("#tab-prices select[name='purchase_price_currency_code'] option[value='USD']")).click();
        driver.findElement(By.cssSelector("#tab-prices input[name='prices[USD]']")).sendKeys("50");
        driver.findElement(By.cssSelector(".button-set > button[name=save]")).click();

        /*Проверяем наличие элемента в списке - получаем список всех элементов и ищем среди них нужный*/
        List<WebElement> anchors = driver.findElements(By.cssSelector("td#content tr.row > td:nth-child(3) > a"));
        boolean found = false;
        for(WebElement anchor:anchors){
            found = anchor.getText().equals(productName);
            if(found) {
                System.out.println("Элемент " + productName + " добавлен успешно");
                break;
            }
        }
        driver.quit();
    }
}
