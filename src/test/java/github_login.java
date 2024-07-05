import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class github_login {
    public static void main(String[] args) {
        // Load properties from file
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("credentials.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Get credentials from properties file
        String correctUsername = properties.getProperty("username.correct");
        String correctPassword = properties.getProperty("password.correct");
        String incorrectUsername = properties.getProperty("username.incorrect");
        String incorrectPassword = properties.getProperty("password.incorrect");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Test case 1: Login with wrong username and correct password
            driver.get("https://github.com/login?return_to=https%3A%2F%2Fgithub.com%2Ftopics%2Flogin");
            WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_field")));
            WebElement password = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]/div[4]/form/div/input[13]"));

            username.clear();
            username.sendKeys(incorrectUsername);
            password.clear();
            password.sendKeys(correctPassword);
            loginButton.click();
        } catch (Exception e) {
            captureScreenshot(driver, "Test1_LoginWithWrongUsername");
            e.printStackTrace();
        }

        try {
            // Test case 2: Login with correct username and wrong password
            driver.get("https://github.com/login?return_to=https%3A%2F%2Fgithub.com%2Ftopics%2Flogin");
            WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_field")));
            WebElement password = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]/div[4]/form/div/input[13]"));

            username.clear();
            username.sendKeys(correctUsername);
            password.clear();
            password.sendKeys(incorrectPassword);
            loginButton.click();
        } catch (Exception e) {
            captureScreenshot(driver, "Test2_LoginWithWrongPassword");
            e.printStackTrace();
        }

        try {
            // Test case 3: Login with correct username and correct password
            driver.get("https://github.com/login?return_to=https%3A%2F%2Fgithub.com%2Ftopics%2Flogin");
            WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_field")));
            WebElement password = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]/div[4]/form/div/input[13]"));

            username.clear();
            username.sendKeys(correctUsername);
            password.clear();
            password.sendKeys(correctPassword);
            loginButton.click();
        } catch (Exception e) {
            captureScreenshot(driver, "Test3_LoginWithCorrectCredentials");
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }

    private static void captureScreenshot(WebDriver driver, String screenshotName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            // Ensure the path points to the 'screenshots' directory
            FileHandler.copy(screenshot, new File("./screenshots/" + screenshotName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

