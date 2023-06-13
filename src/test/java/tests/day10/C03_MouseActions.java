package tests.day10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.util.Set;

public class C03_MouseActions extends TestBase {

    // https://the-internet.herokuapp.com/context_menu sitesine gidin
    // Cizili alan üzerinde sag click yapalim
    // Allert te cikan yazinin "You selected a context menu" oldugunu test edelim.
    // Tamam diyerek allert i kapatalim
    // Elemental Selenium linkine tiklayalim
    // Acilan sayfada h1 taginda "Elemental Selenium" yazdigini test edelim

    @Test
    public void test(){
        // https://the.internet.herokuapp.com/context_menu sitesine gidin
        driver.get("https://the-internet.herokuapp.com/context_menu");

        // Cizili alan üzerinde sag click yapalim
        Actions actions = new Actions(driver);
        WebElement ciziliAlan = driver.findElement(By.id("hot-spot"));
        actions.contextClick(ciziliAlan).perform();


        // Allert te cikan yazinin "You selected a context menu" oldugunu test edelim.
        String expectedAllertyazisi = "You selected a context menu";
        String actualAllertYazisi = driver.switchTo().alert().getText();
        Assert.assertEquals(actualAllertYazisi,expectedAllertyazisi,"Allert yazisi beklenenden farkli");

        // Tamam diyerek allert i kapatalim
        driver.switchTo().alert().accept();

        // Elemental Selenium linkine tiklayalim
        String ilkSayfaHandle = driver.getWindowHandle();
        driver.findElement(By.linkText("Elemental Selenium")).click();


        // Acilan sayfada h1 taginda "Elemental Selenium" yazdigini test edelim

        Set<String> windowTumHandel = driver.getWindowHandles();
        String ikinciSayfaHandle = "";

        for (String each : windowTumHandel){
            if (!each.equals(ilkSayfaHandle)){
                ikinciSayfaHandle = each;
            }
        }

        driver.switchTo().window(ikinciSayfaHandle);
        String expectedIkinciSayfaYazi = "Elemantal Selenium";
        String actualIkinciSayfaYazisi = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(actualIkinciSayfaYazisi,expectedIkinciSayfaYazi,"ikinci sayfadaki yazi istenenden farkli");




    }

}
