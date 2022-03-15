package selenium.b30;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class L8task14 extends TestBase {

    @Test
    public void l8task14(){

        authorization("http://localhost/litecart/admin/?app=countries&doc=countries", "admin", "admin", "Countries | My Store");//Авторизация в админке и переход на нужную страницу
        /*Выбираем случайную страну и открываем ее страницу*/
        driver.findElement(By.cssSelector("table.dataTable tr.row:nth-child(5) > td:nth-child(5) > a:nth-child(1)")).click();
        wait.until(titleIs("Edit Country | My Store"));
        /*Ищем все элементы - внешние ссылки*/
        List<WebElement> extLinks = driver.findElements(By.cssSelector("#content i.fa-external-link"));
        String parentWnd = driver.getWindowHandle();
        Set<String> oldWnds = driver.getWindowHandles();
        /*И переходим по каждой*/
        for (WebElement link:extLinks) {
            link.click();
            String newWindowHnd = wait.until(new ExpectedCondition<String>() {
                public String apply(WebDriver input) {
                    Set<String> newWnds = input.getWindowHandles();
                    newWnds.removeAll(oldWnds);
                    return newWnds.size()>0 ? newWnds.iterator().next() : null;
                }
            });
            /*Пишем список посещенных ссылок*/
            driver.switchTo().window(newWindowHnd);
            System.out.println("Внешняя ссылка: " + driver.getCurrentUrl());
            driver.close();
            driver.switchTo().window(parentWnd);
        }
    }
}
