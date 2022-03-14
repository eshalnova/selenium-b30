package selenium.b30;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class L4task6 extends TestBase{

    @Test
    public void l4task6n() {

        authorization("http://localhost/litecart/admin/login.php", "admin", "admin", "My Store");

        int l1size = driver.findElements(By.id("app-")).size(); //вычисляем количество элементов меню первого уровня

        //Внутри большого цикла ищем и проходим по очереди каждый пункт меню первого уровня
        for (int i = 1; i <= l1size; i++) { //повторяем цикл до тех пор, пока не закончатся элементы первого уровня
            String l1selector = "li[id=app-]:nth-child(" + i + ")";     //формируем селектор для каждого из пунктов меню первого уровня
            WebElement li1 = driver.findElement(By.cssSelector(l1selector)); //нашли очередной элемент первого уровня
            li1.click(); //кликнули на него
            int li2Size = driver.findElements(By.cssSelector(l1selector + " li")).size(); //проверили, есть ли у элемента хотя бы один дочерний
            System.out.println("Тег H1 для пункта: " + driver.findElement(By.tagName("h1")).getText()); //проверили наличие тега и вывели его

            //Внутри вложенного цикла для всех пунктов меню первого уровня у которых есть дочерние - проходим по всем вложенным
            //Начинаем со второго дочернего, так как первый уже выбран. Можно решить иначе, проверяя наличие класса selected, но это следующая лекция
            if (li2Size > 1) {
                for (int j = 2; j <= li2Size; j++) {
                    String l2selector = l1selector + " li" + ":nth-child(" + j + ")" ;//формируем селектор для каждого из дочерних элементов
                    WebElement li2 = driver.findElement(By.cssSelector(l2selector));//нашли очередной дочерний элемент
                    li2.click();//кликнули на него
                    System.out.println("Тег H1 для пункта: " + driver.findElement(By.tagName("h1")).getText());//проверили наличие тега и вывели его
                }
            }
        }
        driver.quit();
    }
}
