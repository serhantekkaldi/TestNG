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

import java.util.concurrent.TimeUnit;

public class C01_HardAssertion {
            //  amazon sayfasina gidin
            //  url nin amazon icerdigini test edin
            //  title in amazon icerdigini test edin
            //  java kelimesi aratin ve listedeki ilk urunun java kelimesi icerdigni test edin


    WebDriver driver;

    @BeforeClass
    public  void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void test1(){
        driver.get("https://www.amazon.com");
        Assert.assertTrue(driver.getCurrentUrl().contains("amazon"));
        Assert.assertTrue(driver.getTitle().contains("Amazon"));

        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("java" + Keys.ENTER);

        WebElement ilkÜrün = driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[1]"));
        Assert.assertTrue(ilkÜrün.getText().contains("Java"));
    }
 }
