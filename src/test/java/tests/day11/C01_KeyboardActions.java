package tests.day11;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C01_KeyboardActions extends TestBase {
    // https://www.amazon.com
    // Arama kutusuna actions methodlarini kullanarak samsung A71 yazdirin
    // Aramanin gerceklestigini test edin

    @Test
    public void test() throws InterruptedException {
        driver.get("https://www.amazon.com");
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        Actions actions = new Actions(driver);
       /*
        actions.click(searchBox).perform();
        actions.sendKeys("samsung ").perform();
        actions.keyDown(Keys.SHIFT).perform();
        actions.sendKeys("a").perform();
        actions.keyUp(Keys.SHIFT).perform();
        actions.sendKeys("71").perform();
        actions.sendKeys(Keys.ENTER).perform();
          */

        actions.click(searchBox)
               .sendKeys("samsung ")
                .keyDown(Keys.SHIFT)
                .sendKeys("a")
                .keyUp(Keys.SHIFT)
                .sendKeys("71")
                .perform();

        Thread.sleep(3000);
        actions.sendKeys(Keys.ENTER).perform();

        String arananKelime = "samsung A71";
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(arananKelime),"arama yapilamadi");








    }

}
