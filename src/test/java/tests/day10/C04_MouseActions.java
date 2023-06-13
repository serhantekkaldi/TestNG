package tests.day10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C04_MouseActions extends TestBase {

    @Test
    public void test(){
        // https://demoqa.com/droppable adresine gidelim
        driver.get("https://demoqa.com/droppable");
        // "Drag me" butonunu tutup "Drop here" kutusunun ustune birakalim
        Actions actions = new Actions(driver);
        WebElement dragElement = driver.findElement(By.id("draggable"));
        WebElement dropAlani = driver.findElement(By.xpath("(//div[@id='droppable'])[1]"));
        actions.dragAndDrop(dragElement,dropAlani).perform();

        // "Drop here" yazisi yerine "Dropped!" oldugunu test edelim

        WebElement droppedYazisi = driver.findElement(By.xpath("//*[text()='Dropped!']"));

        String  actualDroppedYazisi = droppedYazisi.getText();
        String expectedDroppedYazisi = "Dropped!";

        Assert.assertEquals(actualDroppedYazisi,expectedDroppedYazisi,"dropped yazisii beklenenden farklii");


    }

}
