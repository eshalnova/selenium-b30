package selenium.b30;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class L6task11 extends TestBase {

    @Test
    public void l6task11(){

        /*Задаем данные для пользователя*/
        String taxId = "64523", company = "Poison Ivy Corp.";
        String firstName = "Ann", lastName = "First";
        String address1 = "Gorohovaya st. 66", address2 = "Gorohovaya st. 77";
        String city = "Chechov", postcode = "55555";
        String hostPart = "@gmail.com", phone = "+1-982-252-255", password = "password";
        String email = firstName + new Random().nextInt(1000) + hostPart;

        user("http://localhost/litecart/en/create_account",  "Create Account | My Store");//Переход на страницу регистрации
        /*Заполняем текстовые поля до поля Country*/
        driver.findElement(By.cssSelector("input[name=tax_id]")).sendKeys(taxId);
        driver.findElement(By.cssSelector("input[name=company]")).sendKeys(company);
        driver.findElement(By.cssSelector("input[name=firstname]")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input[name=lastname]")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input[name=address1]")).sendKeys(address1);
        driver.findElement(By.cssSelector("input[name=address1]")).sendKeys(address2);
        driver.findElement(By.cssSelector("input[name=postcode]")).sendKeys(postcode);
        /*Заполняем страну и штат*/
        driver.findElement(By.cssSelector("input[name=city]")).sendKeys(city);

        Select countryName = new Select(driver.findElement(By.cssSelector("select[name=country_code]")));
        countryName.selectByValue("US");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select[name=zone_code]")));

        Select zoneName = new Select(driver.findElement(By.cssSelector("select[name=zone_code]")));
        zoneName.selectByValue("VA");
        //driver.findElement(By.cssSelector("select[name=zone_code]")).sendKeys(Keys.ENTER);
        /*Заполняем оставшиеся поля*/
        driver.findElement(By.cssSelector("input[name=phone]")).sendKeys(phone);
        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(email);
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("input[name=confirmed_password]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name=create_account]")).click();
        wait.until(ExpectedConditions.titleIs("Online Store | My Store"));
        /*Выходим из созданного аккаунта*/
        driver.findElement(By.linkText("Logout")).click();
        /*Вводим данные для авторизации*/
        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(email);
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name=login]")).click();
        /*Снова выходим из аккаунта*/
        driver.findElement(By.linkText("Logout")).click();
        driver.quit();
    }
}
