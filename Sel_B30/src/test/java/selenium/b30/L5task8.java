package selenium.b30;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.*;

public class L5task8 extends TestBase{

    @Test
    public void l5task8p1() {

        authorization("http://localhost/litecart/admin/?app=countries&doc=countries", "admin", "admin", "Countries | My Store");

        List<WebElement> rows = driver.findElements(By.cssSelector("table.dataTable tr.row")); //находим все строки таблицы стран
        ArrayList<String> hrefs = new ArrayList<>(); //готовим коллекцию под список ссылок стран с геозонами
        String prevCountry = "";
        /*Для каждой строки проверяем правильность расположения*/
        for (WebElement row:rows) {
            WebElement countryLink = row.findElement(By.cssSelector("td:nth-child(5) a"));//находим ячейку в которой находится название страны
            String country = countryLink.getText(); // получаем название страны
            int compare = country.compareToIgnoreCase(prevCountry); // сравниваем название со следующим и предыдущим
            Assertions.assertTrue(compare >= 0, "Ошибка в сортировке на стране: "+ country); // проверяем правильная ли сортировка и выводим сообщение если есть ошибка
            /* для текущей страны получаем количество зон и если их больше 0 то добавляем страну в список для последующей проверки сортировки геозон*/
            String numZones = row.findElement(By.cssSelector("td:nth-child(6)")).getText();
            if(Integer.parseInt(numZones) > 0){
                hrefs.add(countryLink.getAttribute("href"));
            }
            prevCountry = country;
        }
        System.out.println(rows.size() + " строк отсортировано верно");

        /*Проверяем сортировку геозон, из коллекции заполненной при проверке стран*/
        for (String href:hrefs) {
            driver.get(href);
            wait.until(ExpectedConditions.titleIs("Edit Country | My Store"));
            String country = driver.findElement(By.cssSelector("input[name=name]")).getAttribute("value");
            String cellsSel = "table#table-zones.dataTable tr:not(.header) td:nth-child(3)";
            List<WebElement> cells = driver.findElements(By.cssSelector(cellsSel));
            int zoneCount = cells.size()-1;
            String prevZoneName = "";
            for (int i = 0; i < zoneCount; i++) {
                String zoneName = cells.get(i).getText();
                int compare = zoneName.compareToIgnoreCase(prevZoneName);
                Assertions.assertTrue(compare >= 0,
                        "Ошибка при сортировке геозон для страны: "+ country + ", геозона:" + zoneName + ", ссылка: " + href);
                prevZoneName = zoneName;
            }
            System.out.println(zoneCount + " геозон отсортировано верно для страны: " + country);
        }
        driver.quit();
    }
}

