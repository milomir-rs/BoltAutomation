package test;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.DashboardPage;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static LoginPage loginPage;

    @BeforeClass
    public static void setUpClass() {
        System.setProperty("webdriver.chrome.driver", "/home/milomir/Desktop/workspace/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(1000));
        driver.manage().window().maximize();

        driver.get("https://staging.boltqr.com/");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnSignInLink();
        loginPage = new LoginPage(driver, wait);
    }

    @AfterClass
    public static void tearDownClass() {
        driver.quit();
    }

    @Before
    public void setUp() {
        
    }

    @Test
    public void testLoginWithIncorrectData() {
        loginPage.enterEmail("example@goolge.com");
        loginPage.enterPassword("pass");
        loginPage.clickOnLoginButton();

        assertEquals("Url's doesn't match.", "https://staging.boltqr.com/login", driver.getCurrentUrl());
        assertEquals("Error message doesn't match", "Your login combination is invalid!", loginPage.getAlertMessageText());
    }

    @Test
    public void testLoginWithValidData() {
        loginPage.enterEmail("admin");
        loginPage.enterPassword("admin");
        loginPage.clickOnLoginButton();
        DashboardPage dashboardPage = new DashboardPage(driver, wait);

        assertEquals("Url's doesn't match.", "https://staging.boltqr.com/dashboard", driver.getCurrentUrl());
        assertEquals("Alert message doens't match", "Welcome back, Milomir! You've successfully logged in.", dashboardPage.getAlertMessageText());
        assertEquals("Page headings doesn't match.", "Dashboard", dashboardPage.getPageHeadingText());

        dashboardPage.logout();
    }

    @Test
    public void testLoginWithEmptyFields() {
        loginPage.clickOnLoginButton();

        assertEquals("Url's doesn't match.", "https://staging.boltqr.com/login", driver.getCurrentUrl());
        assertEquals("Validation Message doesn't match", "Please fill out this field.", loginPage.getEmailFieldValidationMessage());
    }

    @Test
    public void testLoginWithValidEmailEmptyPassword() {
        loginPage.enterEmail("admin");
        loginPage.clickOnLoginButton();
        assertEquals("Url's doesn't match.", "https://staging.boltqr.com/login", driver.getCurrentUrl());
        assertEquals("Validation Message doesn't match", "Please fill out this field.", loginPage.getPasswordFieldValidationMessage());
    }
}
