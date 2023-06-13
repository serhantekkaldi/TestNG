package tests.day12;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C02_Faker extends TestBase {
    @Test
    public  void test(){
        // https://facebook.com adresine gidin
        driver.get("https://facebook.com");
        driver.findElement(By.xpath("//button[@class='_42ft _4jy0 _9xo7 _4jy3 _4jy1 selected _51sy']")).click();
        // "create new account" butonuna basin
        driver.findElement(By.linkText("Neues Konto erstellen")).click();

        // "firstName" giris kutusuna bir isim yazin.
        WebElement nameSearchBox = driver.findElement(By.xpath("//input[@name='firstname']"));

        Faker faker = new Faker();
        String email = faker.internet().emailAddress();

        Actions actions = new Actions(driver);
        actions
                .click(nameSearchBox)
                .sendKeys(faker.name().name())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.name().lastName())
                .sendKeys(Keys.TAB)
                .sendKeys(email)
                .sendKeys(Keys.TAB)
                .sendKeys(email)
                .sendKeys(Keys.TAB)
                .sendKeys(faker.internet().password())
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(String.valueOf(faker.number().numberBetween(1,31)))
                .sendKeys(Keys.TAB)
                .sendKeys("Mai.")
                .sendKeys(Keys.TAB)
                .sendKeys(String.valueOf(faker.number().numberBetween(1955,2001)))
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ARROW_RIGHT)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER)
                .sendKeys(Keys.ENTER)
                .perform();









    }


}
