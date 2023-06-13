package tests.day10;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C02_Actions extends TestBase {
    // amazon anasayfasina gidin
    // nutella icin arama yapin
    // 9. ürünü tiklayin

    @Test
    public void test(){
        // amazon anasayfasina gidin
        driver.get("https://www.amazon.com/");

        // nutella icin arama yapin
       WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
       searchBox.sendKeys("nutella" + Keys.ENTER);

        // 9. ürünü tiklayin
       driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal'])[9]")).click();



    }
}
