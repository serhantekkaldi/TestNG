package tests.day10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.TestBase;

import java.util.Set;

public class C01_WindowHandle extends TestBase {
    @Test
    public void test(){
        // https://the-internet.herokuapp.com/windows adresine gidin
        driver.get("https://the-internet.herokuapp.com/windows");
        // Sayfadaki textin "Opening a new window" oldugunu dogrulayin
        String actualText = driver.findElement(By.tagName("h3")).getText();
        String expectedText = "Opening a nw window";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualText,expectedText,"yazi istendigi gibi degil");

        // Sayfa basliginin(title) "The Internet" oldugunu dogrulayin
        String actualTitle = driver.getTitle();
        String expectedTitle = "The Internet";
        softAssert.assertEquals(actualTitle,expectedTitle,"title istendigi gibi degil");

        System.out.println("ilk sayfanin handle degeri: " + driver.getWindowHandle());
        //window handle ederken ilk adim 1 sayfa acikken o sayfanin degerini alip bir String e atamak.
        String ilkSayfaHandle = driver.getWindowHandle();


        // Click Here butonuna basin
        //Bu satirda 2. window acildi.
        driver.findElement(By.linkText("Click Here")).click();
        // Iki sayfa acildiginda her iki sayfanin Handle degerlerini koymak icin bie Set olusturup
        // getWindowHandles() mwthodu ile bu degerleri elde etmek.

        Set<String> tümWindowHandle = driver.getWindowHandles();
        System.out.println("Tum Handlelar: " + tümWindowHandle);
        // Set icersinde birinci sayfanin Handle degerine esit olmayan Handle degerini bulup bir String degiskene
        // atamak.

        String ikinciWindowHandle = "";
        for (String each:tümWindowHandle) {
            if (!each.equals(ilkSayfaHandle)){
                ikinciWindowHandle = each;
            }
        }
        // Bu satira geldigimizde 2. sayfanin Handle degeri var olacak...
        System.out.println("Ikinci sayfa Handle degeri: " + ikinciWindowHandle);

        // Acilan yeni pencerenin sayfa basliginin(title) "New Window" oldugunu dogrulayin
        driver.switchTo().window(ikinciWindowHandle);
        //switchTo() ile window degistirerek gidecegimiz window un windowHandle degerine ihtiyacimiz var.

        String actualNewTitle = driver.getTitle();
        String expectedNewTitle = "New Window";
        softAssert.assertEquals(actualNewTitle,expectedNewTitle,"yeni sayfanin title i yanlis");


        // Sayfadaki textin "New Window" oldugunu dogrulayin
        WebElement newWindowText = driver.findElement(By.tagName("h3"));
        String expectedNewText = "New Window";
        String actualNewText = newWindowText.getText();
        softAssert.assertEquals(actualNewText,expectedNewText,"beklenen text farkli");

        // Bir önceki pencereye geri döndükten sonra sayfa baslliginin "The Internet" oldugunu dogrulayin
        driver.switchTo().window(ilkSayfaHandle);
        actualTitle = driver.getTitle();
        expectedTitle = "The Internet";
        softAssert.assertEquals(actualTitle,expectedTitle,"title istendigi gibi degil");

        softAssert.assertAll();
    }
}
