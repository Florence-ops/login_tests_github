import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.assertTrue;

public class tests_github {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static Properties properties;

    @BeforeClass
    public static void setUp() throws IOException {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream("credentials.properties")) {
            properties.load(fis);
        }

        // Use WebDriverManager to set up ChromeDriver
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            captureScreenshot(description.getMethodName());
        }
    };

    private void captureScreenshot(String screenshotName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File destFile = new File("./screenshots/" + screenshotName + ".png");
            FileUtils.copyFile(screenshot, destFile);
            System.out.println("Screenshot captured: " + destFile.getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Test case 1: Login with wrong username and correct password
    @Test
    public void testLoginWithWrongUsername() {
        driver.get("https://github.com/login?return_to=https%3A%2F%2Fgithub.com%2Ftopics%2Flogin");
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_field")));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]/div[4]/form/div/input[13]"));

        username.clear();
        username.sendKeys(properties.getProperty("username.incorrect"));
        password.clear();
        password.sendKeys(properties.getProperty("password.correct"));
        loginButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".flash-error")));
        assertTrue(errorMessage.isDisplayed());
    }

    // Test case 2: Login with correct username and wrong password
    @Test
    public void testLoginWithWrongPassword() {
        driver.get("https://github.com/login?return_to=https%3A%2F%2Fgithub.com%2Ftopics%2Flogin");
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_field")));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]/div[4]/form/div/input[13]"));

        username.clear();
        username.sendKeys(properties.getProperty("username.correct"));
        password.clear();
        password.sendKeys(properties.getProperty("password.incorrect"));
        loginButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".flash-error")));
        wait.until(ExpectedConditions.textToBePresentInElement(errorMessage, "Incorrect username or password."));
        assertTrue(errorMessage.isDisplayed());
    }

    // Test case 3: Login with correct username and correct password
    @Test
    public void testLoginWithCorrectCredentials() {
        driver.get("https://github.com/login?return_to=https%3A%2F%2Fgithub.com%2Ftopics%2Flogin");
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_field")));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]/div[4]/form/div/input[13]"));

        username.clear();
        username.sendKeys(properties.getProperty("username.correct"));
        password.clear();
        password.sendKeys(properties.getProperty("password.correct"));
        loginButton.click();

        wait.until(ExpectedConditions.titleContains("GitHub"));
        assertTrue(driver.getTitle().contains("GitHub"));
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Main method to run the tests
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(tests_github.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
