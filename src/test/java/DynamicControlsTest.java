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

public class DynamicControlsTest {

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

        driver.get("http://the-internet.herokuapp.com/dynamic_controls");

        WebElement checkbox = driver.findElement(By.xpath("//div[@id='checkbox']/input"));
        WebElement removeButton = driver.findElement(By.xpath("//form[@id='checkbox-example']/descendant::button"));

        removeButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        int numberOfCheckboxes = driver.findElements(By.xpath("//div[@id='checkbox']/input")).size();
        Assert.assertEquals(numberOfCheckboxes, 0, "+");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement inputTextField = driver.findElement(By.xpath("//form[@id='input-example']/input[@type='text']"));
        Assert.assertEquals(inputTextField.isEnabled(), false);

        WebElement enableButton = driver.findElement(By.xpath("//form[@id='input-example']/button"));
        enableButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        Assert.assertEquals(inputTextField.isEnabled(), true);



    }

}