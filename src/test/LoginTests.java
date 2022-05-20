package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import pages.DashboardPage;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    private static LoginPage loginPage;

    @Before
    public void setUp() {
        homePage.clickOnSignInLink();
        loginPage = new LoginPage(driver, wait);
    }

    @After
    public void tearDown() {
        driver.get("https://staging.boltqr.com/");
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
