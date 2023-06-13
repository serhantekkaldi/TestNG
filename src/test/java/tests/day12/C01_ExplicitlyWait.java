package tests.day12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.TestBase;

public class C01_ExplicitlyWait extends TestBase {

    @Test
    public void implicitlyWaitTest() {
        // https://the-internet.herokuapp.com/dynamic_controls adresine gidin
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        //Remove butonuna basin.
        driver.findElement(By.xpath("//button[text()='Remove']")).click();

        // It's gone! yazisinin görüntülendigini dogrulayin.
        SoftAssert softAssert = new SoftAssert();
        WebElement yazi = driver.findElement(By.xpath("//p[@id='message']"));
        softAssert.assertTrue(yazi.isDisplayed()); // TestBase class inda implicitlyWait 15 saniyeye kadar bekletttigi icin
        //  hata almayiz ve test basarili olur. eger 1 saniyeye düsürürsek hataaliriz
        softAssert.assertAll();

        //Add buttonuna basin
        driver.findElement(By.xpath("//button[@onclick='swapCheckbox()']"));

        // It's back mesajinin göründügünü test edin
        WebElement yaziYeni = driver.findElement(By.xpath("//p[@id='message']"));
        Assert.assertTrue(yaziYeni.isDisplayed());

    }


    @Test
    public void explicitlyWaitTest() {
        // https://the-internet.herokuapp.com/dynamic_controls adresine gidin
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        //Remove butonuna basin.
        driver.findElement(By.xpath("//button[text()='Remove']")).click();

        // It's gone! yazisinin görüntülendigini dogrulayin.
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // explicitlyWait i istersek locate islemi ile birlikte tek satirda yapabiliriz.
        // WebElement yaziLocateIleBirlikte = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='message']")));
        // Assert.assertTrue(yaziLocateIleBirlikte.isDisplayed());

        // veya once locate edip uygun method kullanarak beklemeyi yaptirabiliriz. Ancak bu  test icin önce Web elementi olusturmak anlamsiz olur.
        // Cünkü biz wait islemini zaten o webelement olsusun diye yapiyoruz.
        // wait olmadan o element olmaz, o element olmadan da sectigimiz method calismaz.

        WebElement yazi = driver.findElement(By.xpath("//p[@id='message']"));
        wait.until(ExpectedConditions.visibilityOf(yazi));
        Assert.assertTrue(yazi.isDisplayed());

        //Add buttonuna basin
        driver.findElement(By.xpath("//button[@onclick='swapCheckbox()']"));

        // It's back mesajinin göründügünü test edin

        WebElement back = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='message']")));
        Assert.assertTrue(back.isDisplayed());


    }
}