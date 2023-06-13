package tests.day11;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

import javax.swing.*;

public class C02_KeyboardActions extends TestBase {
    // https://html.com/tags/iframe/ sayfasina gidelim.
    // videoyu görecek kadar asagi inin
    // videoyu izlemek icin Play tusuna basin
    //videoyu calistirdiginizi test edin

    @Test
    public  void test(){
        // https://html.com/tags/iframe/ sayfasina gidelim.
        driver.get("https://html.com/tags/iframe/");
        // videoyu görecek kadar asagi inin
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN)
                .sendKeys(Keys.PAGE_DOWN)
                .perform();

        // videoyu izlemek icin Play tusuna basin
        WebElement iFrame = driver.findElement(By.className("lazy-loaded"));
        driver.switchTo().frame(iFrame);

        driver.findElement(By.xpath("//button[@class='ytp-large-play-button ytp-button']")).click();
        //videoyu calistirdiginizi test edin
        WebElement play = driver.findElement(By.xpath("//button[@class='ytp-large-play-button ytp-button']"));
        Assert.assertFalse(play.isDisplayed());

    }
}
