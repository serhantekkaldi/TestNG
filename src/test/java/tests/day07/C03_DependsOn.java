package tests.day07;

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

public class C03_DependsOn {
    // Bir class olusturun : DependsOnTest
    // https://www.amazon.com/ adresine gidin
    // 1.Test : Amazon ana sayfasina gittiginizi test edin
    // 2.Test : 1.Test basarili ise search Box i kullanarak "Nutella" icin arama yapin ve aramanizin gerceklestigini test edin
    // 3.Test : "Nutella" icin arama yapildiysa ilk ürünü tiklayin ve fiyatini $16.83 oldugunu test edin

    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }

    @Test
    public void  test1(){
        driver.get("https://www.amazon.com/");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.amazon.com/","url amazon degil");
    }

    // Testleri birbirine bagladigimzda
    // i-) 2. methodu tek basina calistirmak istesek bile otomatik olarak önce 1. method calisir.
    // ii-) 1. method fail olursa 2.method ignore edilir yani hic calistirilmaz.

    @Test(dependsOnMethods = "test1")
    public void test2(){
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("Nutella" + Keys.ENTER);
        Assert.assertTrue(driver.getTitle().contains("Nutella"));
    }


    // Eger 3 test veya daha fazlasi brbirine depensOn ile baglandiysa 3.yu calistirmak istedigimizde zincir
    // reaksiyon calisip 1. ye gitmez.
    @Test(dependsOnMethods = "test2")
    public void test3() throws InterruptedException {
        Thread.sleep(2000);
      driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal'])[1]")).click();
      WebElement preis = driver.findElement(By.xpath("//span[@class='a-size-base a-color-price']"));
        System.out.println(preis.getText());
        Assert.assertEquals(preis.getText(),"14,99 $");


    }
}
