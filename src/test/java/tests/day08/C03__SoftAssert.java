package tests.day08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class C03__SoftAssert {
    // 1."https://zero.webappsecurity.com/" adresine gidin
    // 2. Sign in butonuna basin
    // 3. Login kutusuna "username" yazin
    // 4. Password kutusuna "password" yazin.
    // 5. Sign in tusuna basin
    // 6. Pay Bills sayfasina gidin
    // 7. "Purchase Foreign Currency" tusuna basin
    // 8. "Currency" drop down menusunden Eurozone ' u secin
    // 9. Soft assert kullanarak "Eurozone(Euro)" secildigini test edin
    // 1. Soft assert kullanarak DropDown listesinin bu secenekleri oldugunu test edin

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
        SoftAssert softAssert = new SoftAssert();
        driver.get("http://zero.webappsecurity.com/");
        driver.findElement(By.id("signin_button")).click();
        driver.findElement(By.id("user_login")).sendKeys("username");
        driver.findElement(By.id("user_password")).sendKeys("password");
        driver.findElement(By.name("submit")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//strong[text()='Online Banking']")).click();
        driver.findElement(By.id("pay_bills_link")).click();
        driver.findElement(By.linkText("Purchase Foreign Currency")).click();
        WebElement dropDown = driver.findElement(By.id("pc_currency"));
        Select select = new Select(dropDown);
        select.selectByValue("EUR");

        String actualData = select.getFirstSelectedOption().getText();
        String expectedValue = "Eurozone (euro)";
        softAssert.assertEquals(actualData,expectedValue,"secilen option Eurozone degil");

        List<WebElement> options = select.getOptions();
        // option listesi Webelementlerden olusuyor.
        // expected listesi ise String, bunun icin option listesini String yapmaliyiz.

        List<String> actualOptionsString = new ArrayList<>();
        for (WebElement each:options) {
            actualOptionsString.add(each.getText()); // Webelementlerin hepsini alip bunu Stringlerden olusan bir liste yaptik
        }

        List<String> expectedOptionsList = Arrays.asList("Select One","Australia (dollar)","Canada (dollar)","Switzerland (franc)","China (yuan)","Denmark (krone)","Eurozone (euro)","Great Britain (pound)","Hong Kong (dollar)","Japan (yen)","Mexico (peso)","Norway (krone)","New Zealand (dollar)","Sweden (krona)","Singapore (dollar)","Thailand (baht)");
        softAssert.assertEquals(actualOptionsString,expectedOptionsList,"liste farkli");

        softAssert.assertAll();


    }

    @AfterClass
    public void tearDown(){
        driver.close();
    }
}
