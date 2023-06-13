package tests.day07;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class C02_DropDown {
    // Bir class olusturun : DropDown
    // https://the-internet.herokuapp.com/dropdown adresine gidin
    // 1. Index kullanarak option 1 i secin yazdirin
    // 2. Value kullanarak option 2 yi secin ve yazdirin
    // 3. Visible Tex(görünen metin)kullarak option 1 i secin ve yazdirin
    // 4. Tüm dropdown degerleri yazdirin
    // 5. Dropdown un boyutunu bulun, Dropdown da 4 öge varsa konsolda True, degilse False yazdirin::

    // dropDown secilirken  select ile 3 farkli yöntem kullanilir:
    // 1. index ile
    // 2. value ile
    // 3. visibileText ile

    WebDriver driver;

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void test() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        Thread.sleep(2000);
        // 1.adim dropDown locate edilir
        WebElement dropDown = driver.findElement(By.id("dropdown"));
        // 2.adim Select Class ini kullanarak bir obje olustur ve
        // parametre olarak locate ettigimiz webelementi yaz
        Select select = new Select(dropDown);
        select.selectByIndex(1);
        System.out.println(select.getFirstSelectedOption().getText());

        Thread.sleep(2000);
        select.selectByValue("2");
        System.out.println(select.getFirstSelectedOption().getText());

        select.selectByVisibleText("Option 1");
        System.out.println(select.getFirstSelectedOption().getText());

        List<WebElement> tümOptionlar = select.getOptions();
        System.out.println("Tüm optionlar listesi:");
        for (WebElement each:tümOptionlar) {
            System.out.println(each.getText());
        }
        System.out.println(tümOptionlar.size()); // 3

       // Assert.assertTrue(tümOptionlar.size()==4);
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}

