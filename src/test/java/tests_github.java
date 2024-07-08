import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

import java.io.*;
import java.util.Properties;

public class tests_github {

    private WebDriver driver;
    private WebDriverWait wait;
    private Properties properties;

    @Before
    public void setUp() throws IOException {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream("credentials.properties")) {
            properties.load(fis);
        }

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLoginWithWrongUsername() {
        driver.get("https://github.com/login");

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_field")));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.name("commit"));

        username.sendKeys(properties.getProperty("username.incorrect"));
        password.sendKeys(properties.getProperty("password.correct"));
        loginButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".flash-error")));
        Assert.assertTrue(errorMessage.isDisplayed());
    }

    @Test
    public void testLoginWithWrongPassword() {
        driver.get("https://github.com/login");

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_field")));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.name("commit"));

        username.sendKeys(properties.getProperty("username.correct"));
        password.sendKeys(properties.getProperty("password.incorrect"));
        loginButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".flash-error")));
        Assert.assertTrue(errorMessage.isDisplayed());
    }

    @Test
    public void testLoginWithCorrectCredentials() {
        driver.get("https://github.com/login");

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_field")));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.name("commit"));

        username.sendKeys(properties.getProperty("username.correct"));
        password.sendKeys(properties.getProperty("password.correct"));
        loginButton.click();

        // Add your assertions here for successful login
    }
}
