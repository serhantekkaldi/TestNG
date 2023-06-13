package tests.day08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class C02_SoftAssertion {
         //  amazon sayfasina gidin
         //  url nin amazon icerdigini test edin
         //  title in amazon icerdigini test edin
         //  java kelimesi aratin ve listedeki ilk urunun java kelimesi icerdigni test edin
         //  testleri Soft assertion ile yapin

    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void test(){
        SoftAssert softassert = new SoftAssert();
        driver.get("https://www.amazon.com");
        softassert.assertTrue(driver.getCurrentUrl().contains("amazon"),"url amazon icermiyor");
        softassert.assertTrue(driver.getTitle().contains("amazon"), "title amazon icermiyor");
        System.out.println("assertion failed oldugu halde bu satir calisir"); //softassert oldugu icin calisir..

        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("java" + Keys.ENTER);

        WebElement ilkÜrün = driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[1]"));
        softassert.assertTrue(ilkÜrün.getText().contains("Java"),"ilk ürün java icermiyor");

        softassert.assertAll();
        // Assertion lar PASS olsa da olmasa da AssertAll a kadar tüm satirlar calisir.
        // Eger testlerden 1 tanesi bile failed ise AssertAll dan sonra execution stops
        System.out.println("AsssertAll dan sonraki satir oldugu icin bunu yazdirmaz");
    }
}
