package tests.day07;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class C01_Priority {

    // 3 Test methodu olusturun
    // 1. amazon ana sayfasina
    // 2. techproeducation ana sayfasina
    // 3. facebook na sayfasina gitsin
    // ve sayfa Title larini yazdirsin


    // priority yazmadigimiz methodlari defaul yani 0 kabul eder
    // priority olmayanlari kendi icinde siralayip olanlari da priority degerlerine uygun siralar.
    // priority degeri olmayanlari method isminin alfabetik siralamasina uygun siralar.
    // Before ve after methodlari her testten önce ve sonra calisir.
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }

    @Test (priority = 10)
    public void amazonTest(){
        driver.get("https://www.amazon.com/");
        System.out.println(driver.getTitle());
    }

    @Test (priority = 1)
    public void techproTest(){
        driver.get("https://www.techproeducation.com/");
        System.out.println(driver.getTitle());
    }

    @Test (priority = 100) // eger priorty hic yazmazsak o zaman 0 kabul eder, ona göre sirayi alir
    public void facebookTest(){
        driver.get("https://www.facebook.com/");
        System.out.println(driver.getTitle());
    }


    @AfterMethod
    public  void tearDown(){
        driver.close();
    }


}
