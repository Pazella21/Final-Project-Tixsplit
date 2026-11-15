
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class CommunityPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locator Create Community
    private By nameField = By.id("community-name");
    private By descriptionField = By.id("community-description");
    private By coverHobby = By.id("btn-community-cover-hobby");
    private By joinPolicyDropdown = By.id("community-join-policy");
    private By createCommunityButton = By.id("btn-create-community-submit");

    // Locator Start a Community
    private By startCommunityButton = By.linkText("Start a community");

    // Locator Create Event
    private By createEventButton = By.xpath(
            "//a[contains(@href, '/events/new')]"
    );

    public CommunityPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Jeda agar proses automation terlihat
    private void pause() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Klik Start a Community
    public void clickStartCommunity() {
        wait.until(
                ExpectedConditions.elementToBeClickable(startCommunityButton)
        ).click();
        pause();
    }

    // Mengisi nama community
    public void enterName(String name) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(nameField)
        ).sendKeys(name);
        pause();
    }

    // Mengisi deskripsi community
    public void enterDescription(String description) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(descriptionField)
        ).sendKeys(description);
        pause();
    }

    // Memilih cover Hobby
    public void selectCoverHobby() {
        wait.until(
                ExpectedConditions.elementToBeClickable(coverHobby)
        ).click();
        pause();
    }

    // Memilih join policy
    public void selectJoinPolicy(String policy) {
        Select dropdown = new Select(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(joinPolicyDropdown)
                )
        );

        dropdown.selectByValue(policy);
        pause();
    }

    // Klik Create Community
    public void clickCreateCommunity() {
        pause();

        wait.until(
                ExpectedConditions.elementToBeClickable(createCommunityButton)
        ).click();
    }

    // Proses Create Community
    public void createCommunity(
            String name,
            String description,
            String policy
    ) {
        enterName(name);
        enterDescription(description);
        selectCoverHobby();
        selectJoinPolicy(policy);
        clickCreateCommunity();
    }

    // Klik Create Event pada halaman detail Community
    public void clickCreateEvent() {
        wait.until(
                ExpectedConditions.elementToBeClickable(createEventButton)
        ).click();
        pause();
    }
}
