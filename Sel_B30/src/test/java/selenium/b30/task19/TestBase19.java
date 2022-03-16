package selenium.b30.task19;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase19 {
    private WebDriver driver;
    public LiteCart liteCart;

    @BeforeEach
    public void start(){
        driver = new ChromeDriver();
        liteCart = new LiteCart(driver);
    }

    @AfterEach
    public void stop(){
        driver.quit();
        driver=null;
    }

}
