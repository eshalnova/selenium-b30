package selenium.b30;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class L4task7 extends TestBase {

    @Test
    public void l4task7() {
        user("http://localhost/litecart/",  "Online Store | My Store");

        String[] boxes = {"box-most-popular", "box-campaigns", "box-latest-products"};//пишем в одном месте все id всех блоков с товарами
        //проходимся по всем блокам
        for (int i=0; i< boxes.length; i++){
            int productCount = driver.findElements(By.cssSelector("div[id=" + boxes[i] + "] li.product")).size();//вычисляем количество элементов внутри блока
            //далее в цикле проходим по всем элементам блока
            for (int j=1; j<=productCount; j++){
                String selector = "div[id=" + boxes[i] + "] li.product" + ":nth-child(" + j + ")";//формируем селектор для элемента
                WebElement product = driver.findElement(By.cssSelector(selector + " div.name"));//получаем имя элемента для доальнейшего использования
                int stickers = driver.findElements(By.cssSelector(selector + " div.sticker")).size();//вычисляем количество стикеров у элемента

                //Выводим список элементов у которых количество стикеров отлично от 1
                if (stickers == 0){
                    System.out.println("Отсутствует хотя бы один стикер у элемента: " + driver.findElement(By.cssSelector("div[id=" + boxes[i] + "] h3.title")).getText() + ", " + product.getText());
                }
                if (stickers >= 2) {
                    System.out.println("У элемента больше одного стикера: " + driver.findElement(By.cssSelector("div[id=" + boxes[i] + "] h3.title")).getText() + ", " + product.getText());
                }
            }
        }
        driver.quit();
    }
}
