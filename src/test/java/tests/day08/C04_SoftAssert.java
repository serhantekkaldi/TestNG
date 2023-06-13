package tests.day08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class C04_SoftAssert {
    // "https://www.hepsiburada.com/" adresine gidin
    // Basliginin "Turkiye'nin En büyük Alisveris Sitesi" icerdigini dogrulayin
    // search butonuna araba yazip arattirin
    // bulunan sonuc sayisini yazdirin
    // sonuc yazisinin "araba" icerdigini dogrulayin
    // sonuc yazisinin "oto" icermedigini dogrulayin

    WebDriver driver;

    @BeforeClass
    public  void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void test(){
        // "https://www.hepsiburada.com/" adresine gidin
        driver.get("https://www.hepsiburada.com/");
        // Basliginin "Turkiye'nin En büyük Alisveris Sitesi" icerdigini dogrulayin
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(driver.getTitle().contains("Türkiye'nin En Büyük Online Alışveriş Sitesi"), "Title istenilen cümleyi icermiyor");

        // search butonuna araba yazip arattirin
        driver.findElement(By.xpath("//input[@class='desktopOldAutosuggestTheme-input']")).sendKeys("araba" + Keys.ENTER);

        // bulunan sonuc sayisini yazdirin
        WebElement sonucYazisi = driver.findElement(By.className("searchResultSummaryBar-summary"));
        System.out.println(sonucYazisi.getText());

        // sonuc yazisinin "araba" icerdigini dogrulayin
        softAssert.assertTrue(sonucYazisi.getText().contains("araba"),"sonuc yazisi araba icermiyor");

        // sonuc yazisinin "oto" icermedigini dogrulayin
        softAssert.assertFalse(sonucYazisi.getText().contains("oto"), "sonuc yazisi oto iceriyor");

        softAssert.assertAll();

    }

    @AfterClass
    public  void tearDown(){
       // driver.close();
    }



}
