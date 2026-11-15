
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locator halaman Login
    private By emailField = By.id("sign-in-email");
    private By passwordField = By.id("sign-in-password");
    private By loginButton = By.id("btn-sign-in-submit");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );
    }

    // Mengisi email
    public void enterEmail(String email) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(emailField)
        ).sendKeys(email);
    }

    // Mengisi password
    public void enterPassword(String password) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordField)
        ).sendKeys(password);
    }

    // Klik tombol Login
    public void clickLogin() {
        wait.until(
                ExpectedConditions.elementToBeClickable(loginButton)
        ).click();
    }

    // Proses Login
    public void login(String email, String password) {

        // Menunggu halaman Login siap
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(emailField)
        );

        // Input email
        enterEmail(email);

        // Input password
        enterPassword(password);

        // Klik tombol Login
        clickLogin();

        // Menunggu proses login selesai
        wait.until(
                ExpectedConditions.or(
                        ExpectedConditions.urlContains("/events"),
                        ExpectedConditions.urlContains("/communities")
                )
        );
    }
}
