import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class Checkboxes {

    WebDriver driver;

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void checkboxesTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://the-internet.herokuapp.com/checkboxes");

        List <WebElement> checkboxes = driver.findElements(By.cssSelector("[type=checkbox]"));

        Assert.assertEquals(checkboxes.get(0).isSelected(), false);
        checkboxes.get(0).click();

        Assert.assertEquals(checkboxes.get(1).isSelected(), true);
        checkboxes.get(1).click();


    }
}