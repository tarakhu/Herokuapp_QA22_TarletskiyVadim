import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Frames {
    WebDriver driver;

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    public void typosTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://the-internet.herokuapp.com/frames");

        WebElement iFrame = driver.findElement(By.xpath("//a[contains(text(),'iFrame')]"));
        iFrame.click();

        driver.switchTo().frame("mce_0_ifr");

        Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Your content goes here.')]")).getText(), "" +
                "Your content goes here.");
    }
}