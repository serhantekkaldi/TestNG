package tests.day09;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class C02_Iframe {
    // https://the-internet.herokuapp.com/iframe adresine gidin
    // Bir method olusturun  : iframeTest
    //            "An Iramecontaining,,,." textinin erisilebilir oldugunu test edin ve konsolda yazdirin.
    //             TextBox a "Merhaba Dünya!" yazin
    //             TextBox ain altinda bulunan "Elmental Selenium" linkinin textinin görünür oldugunu dogrulayin ve
    //             konsolda  yazdirin


    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }

    @Test
    public void iframeTest() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/iframe");
        // "An Iramecontaining,,,." textinin erisilebilir oldugunu test edin ve konsolda yazdirin.
        WebElement baslik = driver.findElement(By.tagName("h3"));
        Assert.assertTrue(baslik.isEnabled(),"Baslik yazisi erisilebilir degil");
        System.out.println(baslik.getText());

        // TextBox a "Merhaba Dünya!" yazin
        driver.switchTo().frame(0); //index, value veya id, web element ile yapabiliriz.
        // WebElement ile
        //WebElement IFrame = driver.findElement(By.id("mce_0_ifr"));
        //driver.switchTo().frame(IFrame);
        WebElement searchBox= driver.findElement(By.xpath("//body[@id='tinymce']"));
        searchBox.clear();
        searchBox.sendKeys("Merhaba Dünya!");
        // Bir iFrame e gecis yaptiktan sonra   yeniden ana sayfa ile ilgili islem yapmak isterseniz
        // gecis yaptigimiz iFrame den geri dönmeliyiz.
        driver.switchTo().parentFrame(); // 1 üst seviyedeki frame e cikartir
        //driver.switchTo().defaultContent(); // En üstteki frame e cikmak icin kullanilir



        //    TextBox ain altinda bulunan "Elmental Selenium" linkinin textinin görünür oldugunu dogrulayin ve
        //    konsolda  yazdirin

        WebElement elemental = driver.findElement(By.linkText("Elemental Selenium"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(elemental.isDisplayed());
        System.out.println(elemental.getText());

        softAssert.assertAll();

        elemental.click();
        Thread.sleep(3000);


    }



}
