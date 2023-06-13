package tests.day09;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class C01_Allerts {
    // https://the-internet.herokuapp.com/javascript_alerts adresine gidin
    // Bir method olusturun : acceptAlert
    //           1.butona tiklayin, uyaridaki OK butonuna tiklayin ve result mesajinin
    //             "You successfully clicked an alert" oldugunu test edin.
    // Bir method olusturun : dismissAlert
    //           2. butona tiklayin, uyaridaki Cancel butonuna tiklayin ve result mesajinin
    //           "successfuly" icermedigini test edin.
    // Bir method olusturun: sendKeysAlert
    //           3.butona tiklayin, uyaridaki metin kutusuna isminizi yazin,
    //           OK butonuna tiklayin ve result mesajinda isminizin görüntülendigini dogrulayin


    WebDriver driver;

    // Her Allert JS(javasicript) Allert degildir
    // Allert ciktiginda sag click yapip inceleyebiliyorsak bu bir HTML alert tir.
    // HTML alert ler siradan webelement ler olarak locate edilip kullanilabilir
    // Sag click yapamiyorsak alert bir JS Allert tir  ve swtichTo() kullanilarak  handle edilir.


    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

    }

    @Test
    public void acceptAlert(){
        //           1.butona tiklayin, uyaridaki OK butonuna tiklayin ve result mesajinin
        //             "You successfully clicked an alert" oldugunu test edin.
        driver.findElement(By.xpath("//button[@onclick ='jsAlert()']")).click();
        driver.switchTo().alert().accept();
        WebElement result = driver.findElement(By.id("result"));
        String expectedResult = "You successfully clicked an alert";
        String actualResult = result.getText();
        Assert.assertEquals(actualResult,expectedResult,"Sonuc yazisi istenilen ile ayni degil");
    }

    @Test
    public void dismissAlert(){
        //           2. butona tiklayin, uyaridaki Cancel butonuna tiklayin ve result mesajinin
        //           "successfuly" icermedigini test edin.
        driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
        driver.switchTo().alert().dismiss();
        String expectedResult = "successfuly";
        WebElement result = driver.findElement(By.id("result"));
        String actualResult = result.getText();

        Assert.assertFalse(actualResult.contains(expectedResult),"Result yazisi istenmeyen kelimeyi icerir");



    }

    @Test
    public void sendKeysAlert(){
        //           3.butona tiklayin, uyaridaki metin kutusuna isminizi yazin,
        //           OK butonuna tiklayin ve result mesajinda isminizin görüntülendigini dogrulayin
        driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
        String name = "Serhan";
        driver.switchTo().alert().sendKeys(name);
        driver.switchTo().alert().accept();
        WebElement result = driver.findElement(By.id("result"));

        String actualResult = result.getText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(actualResult.contains(name),"ismim görüntülenmedi");

        softAssert.assertAll();

    }

    @AfterClass
    public void tearDown(){

        driver.close();
    }

}
