package test;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverInfo;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.HomePage;

public class BaseTest {

    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static HomePage homePage;

    @BeforeClass
    public static void setUpClass() {
        System.setProperty("webdriver.chrome.driver", "/home/milomir/Desktop/workspace/chromedriver");
        // driver = new ChromeDriver();

        System.setProperty("webdriver.gecko.driver", "/home/milomir/Desktop/workspace/geckodriver");
        driver = new FirefoxDriver();
        
        wait = new WebDriverWait(driver, Duration.ofMillis(1000));
        Dimension tablet = new Dimension(1024, 600);
        Dimension desktop = new Dimension(1920, 1080);
        driver.manage().window().setSize(desktop);

        driver.get("https://staging.boltqr.com/");
        homePage = new HomePage(driver);
    }

    @AfterClass
    public static void tearDownClass() {
        driver.quit();
    }

}
