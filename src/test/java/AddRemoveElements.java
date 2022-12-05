import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddRemoveElements {

    WebDriver driver;

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void addRemoveTest () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement addElement = driver.findElement(By.xpath("//button[text()='Add Element']"));
        addElement.click();
        addElement.click();


        WebElement deleteElement = driver.findElement(By.xpath("//button[text()='Delete']"));
        deleteElement.click();

        List <WebElement> deleteElementList = driver.findElements(By.xpath("//button[text()='Delete']"));
        Assert.assertEquals(deleteElementList.size(), 1);


    }

}
