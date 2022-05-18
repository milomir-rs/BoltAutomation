import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Signin {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "/home/milomir/Desktop/workspace/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://staging.boltqr.com/");
        
        WebElement signInLink = driver.findElement(By.linkText("Sign in"));
        signInLink.click();

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("example@goolge.com");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("admin");

        WebElement loginButton = driver.findElement(By.name("submit"));
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500));
        WebElement alertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-danger")));

        String expectedUrl = "https://staging.boltqr.com/login";
        String actualUrl = driver.getCurrentUrl();

        String expectedErrorMessage = "Your login combination is invalid!";
        String actualErrorMessage = alertMessage.getText();
        
        if (expectedUrl.equals(actualUrl) && expectedErrorMessage.equals(actualErrorMessage)) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
        }

        driver.quit();
        
    }
}
