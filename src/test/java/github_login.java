import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.devtools.v124.page.Page.captureScreenshot;

public class github_login2 {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Test case 1: Login with wrong username and correct password
        driver.get("https://github.com/login?return_to=https%3A%2F%2Fgithub.com%2Ftopics%2Flogin");
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_field")));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]/div[4]/form/div/input[13]"));

        username.clear();
        username.sendKeys("w.muturiflorencetest@gmail.com");
        password.clear();
        password.sendKeys("Puckerman.1flo");
        loginButton.click();

        // Test case 2: Login with correct username and wrong password
        driver.get("https://github.com/login?return_to=https%3A%2F%2Fgithub.com%2Ftopics%2Flogin");
        username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_field")));
        password = driver.findElement(By.id("password"));
        loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]/div[4]/form/div/input[13]"));

        username.clear();
        username.sendKeys("w.muturiflorence@gmail.com");
        password.clear();
        password.sendKeys("Puckerman.2flo");
        loginButton.click();

        // Test case 3: Login with correct username and correct password
        driver.get("https://github.com/login?return_to=https%3A%2F%2Fgithub.com%2Ftopics%2Flogin");
        username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_field")));
        password = driver.findElement(By.id("password"));
        loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]/div[4]/form/div/input[13]"));

        username.clear();
        username.sendKeys("w.muturiflorence@gmail.com");
        password.clear();
        password.sendKeys("Puckerman.1flo");
        loginButton.click();

        // Close the browser
        driver.quit();
    }
}
