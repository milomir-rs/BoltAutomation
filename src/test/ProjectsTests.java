package test;
import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.DashboardPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProjectsPage;

public class ProjectsTests extends BaseTest {
    private static ProjectsPage projectsPage;
    private static DashboardPage dashboardPage;

    @Before
    public void setUp() {
        homePage.clickOnSignInLink();
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterEmail("admin");
        loginPage.enterPassword("admin");
        loginPage.clickOnLoginButton();   
        dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.clickOnProjectsLink();
        projectsPage = new ProjectsPage(driver, wait);
    }

    @After
    public void tearDown() {
        dashboardPage.logout();
    }

    @Test
    public void testCreatingProjectWithNameAndDefaultColor() {
        ProjectsPage projectsPage = new ProjectsPage(driver, wait);
        
        projectsPage.clickOnCreateAProjectLink();
        projectsPage.enterNameToProject("Omar");
        projectsPage.clickOnCreateButton();

        assertEquals("Urls don't match", "https://staging.boltqr.com/projects", driver.getCurrentUrl());
        assertEquals("Alert messages don't match", "Omar has been successfully created.", projectsPage.getAlertMessageText());
    }


    @Test
    public void testFilteringProjectsTableBySearchInputField() {
        
        projectsPage.clickOnCreateAProjectLink();
        projectsPage.enterNameToProject("Omar");
        projectsPage.clickOnCreateButton();

        projectsPage.clickOnCreateAProjectLink();
        projectsPage.enterNameToProject("Milomir");
        projectsPage.clickOnCreateButton();

        projectsPage.clickOnCreateAProjectLink();
        projectsPage.enterNameToProject("Sonja");
        projectsPage.clickOnCreateButton();

        projectsPage.clickOnCreateAProjectLink();
        projectsPage.enterNameToProject("Jelena");
        projectsPage.clickOnCreateButton();

        projectsPage.clickOnFilterButton();
        projectsPage.enterSearchInput("Jelena");
        projectsPage.clickOnSubmitButton();

        assertEquals("Names don't match", "Jelena", projectsPage.getProjectName());

        projectsPage.clickOnProjectsLink();

        assertEquals("Urls don't match", "https://staging.boltqr.com/projects", driver.getCurrentUrl());

        projectsPage.clickOnKebabDropDownMenu();
        projectsPage.clickOnDeleteOption();
        projectsPage.clickOnDeleteProject();

        projectsPage.clickOnKebabDropDownMenu();
        projectsPage.clickOnDeleteOption();
        projectsPage.clickOnDeleteProject();

        projectsPage.clickOnKebabDropDownMenu();
        projectsPage.clickOnDeleteOption();
        projectsPage.clickOnDeleteProject();

        projectsPage.clickOnKebabDropDownMenu();
        projectsPage.clickOnDeleteOption();
        projectsPage.clickOnDeleteProject();
    }
}


    // public static void main(String[] args) throws Exception {
    //     System.setProperty("webdriver.chrome.driver", "C:\\Users\\omar_\\Desktop\\Omar\\QA course\\Automation testing\\Workspace\\chromedriver.exe");
    //     WebDriver driver = new ChromeDriver();
    //     driver.manage().window().maximize();

    //     driver.get("https://staging.boltqr.com/");

    //     WebElement singinButton = driver.findElement(By.linkText("Sign in"));
    //     singinButton.click();

    //     WebElement emailField = driver.findElement(By.id("email"));
    //     emailField.sendKeys("admin");

    //     WebElement passwordField = driver.findElement(By.id("password"));
    //     passwordField.sendKeys("admin");

    //     WebElement loginButton = driver.findElement(By.name("submit"));
    //     loginButton.click();

    //     WebElement projectsLink = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/ul/li[5]/a"));
    //     projectsLink.click();

    //     WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(600));
    //     WebElement createProjectButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Create project")));
    //     createProjectButton.click();

    //     WebElement projectNameInput = driver.findElement(By.id("name"));
    //     projectNameInput.sendKeys("QA test");

    //     WebElement createButton = driver.findElement(By.name("submit"));
    //     createButton.click();

    //     WebElement alertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-success")));

    //     WebElement projectName = driver.findElement(By.linkText("QA test"));

    //     String expectedUrl = "https://staging.boltqr.com/projects";
    //     String actualUrl = driver.getCurrentUrl();

    //     String expectedAlertMessage = "QA test has been successfully created.";
    //     String actualAlertMessage = alertMessage.getText();

    //     String expectedProjectName = "QA test";
    //     String actualProjectName = projectName.getText();

    //     if (expectedUrl.equals(actualUrl) && expectedAlertMessage.equals(actualAlertMessage) && expectedProjectName.equals(actualProjectName)) {
    //         System.out.println("Test passed.");
    //     } else {
    //         System.out.println("Test failed.");
    //     }

    //     driver.quit();

    // }
//}
