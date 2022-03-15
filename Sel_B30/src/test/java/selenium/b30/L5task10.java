package selenium.b30;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class L5task10 extends TestBase{

    @Test
    public void l5task10(){

        user("http://localhost/litecart/",  "Online Store | My Store");

        WebElement product = driver.findElement(By.cssSelector("div[id = box-campaigns] li.product:nth-child(1)")); // первый товар блока Campaigns

        /*Получаем и сохраняем для сравнения данные товара на главной странице*/
        String productLink = product.findElement(By.cssSelector(".link")).getAttribute("href");  // ссылка на страницу товара
        String productName = product.findElement(By.cssSelector(".name")).getAttribute("textContent"); // название товара
        WebElement regularPrice = product.findElement(By.cssSelector(".regular-price"));//элемент с обычной ценой товара
        WebElement campaignPrice = product.findElement(By.cssSelector(".campaign-price"));//элемент с акционной ценой товара
        String regularPriceValue = regularPrice.getAttribute("textContent");//обычная цена товара (значение)
        String campaignPriceValue = campaignPrice.getAttribute("textContent");//акционная цена товара (значение)
        String regularPriceClass = regularPrice.getAttribute("class");//получаем класс для обычной цены товара
        String campaignPriceClass = campaignPrice.getAttribute("class");//получаем класс для акционной цены товара
        String regularPriceTag = regularPrice.getAttribute("tagName");//определяем зачеркнутость обычной цены, по тегу
        String campaignPriceTag = campaignPrice.getAttribute("tagName");//определяем зачеркнутость обычной цены, по тегу

        product.click();//переходим на страницу товара

        /*Получаем и сохраняем для сравнения данные товара на странице товара*/
        String productLinkP = driver.getCurrentUrl();//получаем ссылку на текущую страницу
        String productNameP = driver.findElement(By.cssSelector("#box-product .title")).getAttribute("textContent");// название товара
        WebElement regularPriceP = driver.findElement(By.cssSelector("#box-product .regular-price"));//элемент с обычной ценой товара
        WebElement campaignPriceP = driver.findElement(By.cssSelector("#box-product .campaign-price"));//элемент с акционной ценой товара
        String regularPriceValueP = regularPriceP.getAttribute("textContent");//обычная цена товара (значение)
        String campaignPriceValueP = campaignPriceP.getAttribute("textContent");//акционная цена товара (значение)
        String regularPriceClassP = regularPriceP.getAttribute("class");//получаем класс для обычной цены товара
        String campaignPriceClassP = campaignPriceP.getAttribute("class");//получаем класс для акционной цены товара
        String regularPriceTagP = regularPriceP.getAttribute("tagName");//определяем зачеркнутость обычной цены, по тегу
        String campaignPriceTagP = campaignPriceP.getAttribute("tagName");//определяем зачеркнутость обычной цены, по тегу

        /*Сравниваем значения*/
        assertEquals(productLink, productLinkP);
        assertEquals(productName, productNameP);
        assertEquals(regularPriceValue, regularPriceValueP);
        assertEquals(campaignPriceValue, campaignPriceValueP);
        assertEquals(regularPriceClass, regularPriceClassP);
        assertEquals(campaignPriceClass, campaignPriceClassP);
        assertEquals(regularPriceTag, regularPriceTagP);
        assertEquals(campaignPriceTag, campaignPriceTagP);
    }
}
