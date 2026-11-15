
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class RegistrationPage {

    private WebDriver driver;

    private By nameField = By.id("create-name");
    private By emailField = By.id("create-email");
    private By passwordField = By.id("create-password");
    private By registerButton = By.id("btn-create-account-submit");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickRegister() {
        driver.findElement(registerButton).click();
    }

    public void register(String name, String email, String password) {

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10)
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(nameField)
        );

        // Input nama
        enterName(name);
        sleep(1000);

        // Input email
        enterEmail(email);
        sleep(1000);

        // Input password
        enterPassword(password);
        sleep(1000);

        // Sebelum klik tombol
        sleep(1500);
        clickRegister();

        // Tunggu agar hasil terlihat
        sleep(3000);
    }

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public String getPageMessage() {
        return driver.findElement(By.tagName("body")).getText();
    }
}
