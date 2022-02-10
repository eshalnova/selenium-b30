package selenium.b30;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyFirstTest {

    public static void main(String[] args){
        WebDriver driver = new ChromeDriver();
        driver.get("https://ru.wikipedia.org/wiki");
        driver.close();
    }
}
