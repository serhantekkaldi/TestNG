package tests.day11;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.nio.file.Files;
import java.nio.file.Paths;

public class C04_FileDownLoad extends TestBase {

    @Test
    public void downLoadTest() throws InterruptedException {
        // iki Tane method olusturun : isExist() ve downLoadTest()
        // downloadTest() methodunun icinde asagidaki testi yapalim :
        //    - https://the-internet.herokuapp.com/download adresine gidelim.
        driver.get("https://the-internet.herokuapp.com/download");
        //    - sample.png dosyasini indirelim.
        driver.findElement(By.linkText("sample.png")).click();
        Thread.sleep(5000); // dosyanin buyklugune göre indirebilmesi icin süre vermemiz gerekir.

    }
    @Test
    public void isExist(){
        // Ardindan isExist() methodunda dosyanin basariyla indirilip indirilmedigini test edelim.

        String dosyaYolu = System.getProperty("user home") + "\\Student\\Downloads\\sample.png";
        Assert.assertTrue(Files.exists(Paths.get(dosyaYolu)));

    }
}
