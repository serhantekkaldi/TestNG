package tests.day11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C05_UploadFile extends TestBase {

    @Test
    public void test() throws InterruptedException {
        // https://the-internet.herokuapp.com/upload adresine gidelim
        driver.get("https://the-internet.herokuapp.com/upload");

        // chooseFile butonuna basalim.

        // Yuklemek istedigimiz dosyayi secelim
        String dosyaYolu = System.getProperty("user.home") + "\\Desktop\\test.jpg";
        WebElement dosyaYukle = driver.findElement(By.id("file-upload"));
        dosyaYukle.sendKeys(dosyaYolu);

        // Upload butonuna basalim...
        driver.findElement(By.id("file-submit")).click();
        Thread.sleep(3000);
        // "File Uploaded!" textinin görüntülendigini test edelim.
        WebElement Uploaded = driver.findElement(By.tagName("h3"));
        Assert.assertTrue(Uploaded.isDisplayed());

    }
}
