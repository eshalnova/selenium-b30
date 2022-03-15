package selenium.b30;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import java.util.ArrayList;

public class L10task17 extends TestBase {

    @Test
    public void l10task17(){

        authorization("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1", "admin", "admin", "Catalog | My Store");//авторизация в админке

        ArrayList<LogEntry> logs = new ArrayList<>();//создаем коллекцию для логов

        /*Проходим по всем товарам раздела. Начинаем с третьего, так как раньше - папки*/
        for (int i=3; i<=driver.findElements(By.cssSelector(".dataTable tr.row")).size()+1; i++){

            WebElement row =  driver.findElement(By.cssSelector(".dataTable .row:nth-child(" + i + ")"));
            String checkboxName = row.findElement(By.cssSelector("input")).getAttribute("name");
            if (checkboxName.contains("products")){
                row.findElement(By.cssSelector("a")).click();
                logs.addAll(driver.manage().logs().get("browser").getAll());
            }
            driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        }

        /*Выводим информацию о найденных в логе сообщениях*/
        if(logs.size() == 0){
            System.out.println("Сообщения в логе браузера отсутствуют");
        }
        else {
            System.out.println("В логе браузера - " + logs.size() +" сообщений");
        };
        }
    }