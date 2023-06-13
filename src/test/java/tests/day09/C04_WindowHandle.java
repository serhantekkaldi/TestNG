package tests.day09;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.TestBase;

public class C04_WindowHandle extends TestBase {
    // https://the-inernet.herokuapp.com/windows adresine gidin
    // Sayfadaki textin "Opening a nw window" oldugunu dogrulayin
    // Sayfa basliginin(title) "The Internet" oldugunu dogrulayin
    // Click Here butonuna basin
    // Acilan yeni pencerenin sayfa basliginin(title) "New Window" oldugunu dogrulayin
    // Sayfadaki textin "New Window" oldugunu dogrulayin
    // Bir önceki pencereye geri döndükten sonra sayfa baslliginin "The Internet" oldugunu dogrulayin

    @Test
    public void test(){
        // https://the-internet.herokuapp.com/windows adresine gidin
        driver.get("https://the-internet.herokuapp.com/windows");
        // Sayfadaki textin "Opening a nw window" oldugunu dogrulayin
        String actualText = driver.findElement(By.tagName("h3")).getText();
        String expectedText = "Opening a nw window";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualText,expectedText,"yazi istendigi gibi degil");

        // Sayfa basliginin(title) "The Internet" oldugunu dogrulayin
        String actualTitle = driver.getTitle();
        String expectedTitle = "The Internet";
        softAssert.assertEquals(actualTitle,expectedTitle,"title istendigi gibi degil");
        // Click Here butonuna basin
        driver.findElement(By.linkText("Click Here")).click();
        // Acilan yeni pencerenin sayfa basliginin(title) "New Window" oldugunu dogrulayin
        String actualNewTitle = driver.getTitle();
        String expectedNewTitle = "New Window";
        softAssert.assertEquals(actualNewTitle,expectedNewTitle,"yeni Title istendigi gibi degil");
        // Sayfadaki textin "New Window" oldugunu dogrulayin

        // Bir önceki pencereye geri döndükten sonra sayfa baslliginin "The Internet" oldugunu dogrulayin

    }
}
