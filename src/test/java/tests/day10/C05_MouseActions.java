package tests.day10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C05_MouseActions extends TestBase {

    @Test
    public  void test (){
        // https://www.amazon.com adresine gidelim
        driver.get("https://www.amazon.com");
        // Sag üst bölümde bulunan "Account & List" menusunun acilmasi icin mouse u bu menunun ustune getirelim
        WebElement liste = driver.findElement(By.xpath("//span[.='Hallo, Anmelden']"));
        Actions action = new Actions(driver);
        action.moveToElement(liste).perform();
        // "Create a list" butonuna basalim
        driver.findElement(By.xpath("//span[.='Neue Liste anlegen ']")).click();
        // Acilan sayfada "Your Lists" yazisi oldugunu test edelim
        WebElement yourList = driver.findElement(By.xpath("//div[@role='heading']"));
        String actualYourList = yourList.getText();
        String expectedYourList = "Meine Listen";
        Assert.assertEquals(actualYourList,expectedYourList,"Meine Listen beklenen den farkli");

    }
}
