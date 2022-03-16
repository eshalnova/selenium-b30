package selenium.b30;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class L5task9 extends TestBase{

        @Test
        public void l5task8p2(){

            authorization("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones", "admin", "admin", "Geo Zones | My Store");

            List<WebElement> cells = driver.findElements(By.cssSelector("tr.row td:nth-child(3) a"));//список ячеек стран
            /*Формируем коллекцию ссылок на страницы стран*/
            ArrayList<String> hrefs = new ArrayList<>();
            for(WebElement cell:cells){
                hrefs.add(cell.getAttribute("href"));
            }
            /*Для каждой страны переходим по ссылке и проверяем сортировку геозон*/
            for(String href:hrefs){
                driver.get(href);
                wait.until(ExpectedConditions.titleIs("Edit Geo Zone | My Store"));
                String geoZoneName = driver.findElement(By.cssSelector("input[name=name]")).getAttribute("value");
                cells = driver.findElements(By.cssSelector("table#table-zones.dataTable tr td:nth-child(3) option[selected]"));
                String prevZoneName = "";
                for(WebElement cell:cells){
                    String zoneName = cell.getText();
                    int compare = zoneName.compareToIgnoreCase(prevZoneName); // сравниваем название геозоны со следующим и предыдущим
                    Assertions.assertTrue(compare >= 0,"Ошибка сортировки для: "+ geoZoneName + ", геозона:" + zoneName + ", ссылка: " + href); // проверяем правильная ли сортировка и выводим сообщение если есть ошибка
                    prevZoneName = zoneName;
                }
                System.out.println(cells.size() + " геозон отсортировано верно для страны: " + geoZoneName);
            }
            driver.quit();
        }
    }

