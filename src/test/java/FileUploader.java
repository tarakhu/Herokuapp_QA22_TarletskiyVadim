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

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class FileUploader {

    WebDriver driver;

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    public void fileUploaderTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://the-internet.herokuapp.com/upload");

        WebElement fileUploadInput = driver.findElement(By.xpath("//input[@type='file']"));

        fileUploadInput.sendKeys(System.getProperty("user.dir") + "/src/test/resources/CollectionsHierarchy.png");
        File file = new File("src/test/resources/CollectionsHierarchy.png");

        driver.findElement(By.id("file-submit")).click();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'File Uploaded!')]")));

        Assert.assertEquals(driver.findElement(By.id("uploaded-files")).getText(), "CollectionsHierarchy.png");



    }
}