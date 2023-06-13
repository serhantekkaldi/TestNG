package tests.day08;


import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.TestBase;

public class Ubung extends TestBase {

   @Test
    public void test(){
       driver.get("https://facebook.com");
       driver.findElement(By.xpath("//button[@class='_42ft _4jy0 _9xo7 _4jy3 _4jy1 selected _51sy']")).click();
       driver.findElement(By.linkText("Neues Konto erstellen")).click();
       WebElement nameBox = driver.findElement(By.xpath("//input[@name='firstname']"));

       Faker faker=new Faker();
       String email = faker.internet().emailAddress();

       Actions actions = new Actions(driver);
      actions.click(nameBox)
              .sendKeys(faker.name().firstName())
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
              .sendKeys("Mai")
              .sendKeys(Keys.TAB)
              .sendKeys(String.valueOf(faker.number().numberBetween(1955,2000)))
              .sendKeys(Keys.TAB)
              .sendKeys(Keys.TAB)
              .sendKeys(Keys.ARROW_RIGHT)
              .sendKeys(Keys.TAB)
              .sendKeys(Keys.TAB)
              .sendKeys(Keys.TAB)
              .sendKeys(Keys.ENTER)
              .perform();

   }
}
