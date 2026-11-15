
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class EventPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ==============================
    // LOCATOR CREATE EVENT
    // ==============================

    private By nameField =
            By.id("event-name");

    private By categoryField =
            By.id("event-category");

    private By descriptionField =
            By.id("event-description");

    private By dateTimeField =
            By.id("event-starts-at");

    private By locationField =
            By.id("event-location");

    // Cover Image: Morning run
    private By coverMorningRun =
            By.id("btn-cover-1787718830568-morning-run.webp");

    private By participantLimitField =
            By.id("event-limit");

    private By visibilityDropdown =
            By.id("event-visibility");

    private By pricingDropdown =
            By.id("event-pricing-type");

    private By createEventButton =
            By.id("btn-create-event-submit");


    // ==============================
    // CONSTRUCTOR
    // ==============================

    public EventPage(WebDriver driver) {
        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );
    }


    // ==============================
    // INPUT NAME
    // ==============================

    public void enterName(String name) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        nameField
                )
        );

        element.clear();
        element.sendKeys(name);
    }


    // ==============================
    // INPUT CATEGORY
    // ==============================

    public void enterCategory(String category) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        categoryField
                )
        );

        element.clear();
        element.sendKeys(category);
    }


    // ==============================
    // INPUT DESCRIPTION
    // ==============================

    public void enterDescription(String description) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        descriptionField
                )
        );

        element.clear();
        element.sendKeys(description);
    }


    // ==============================
    // INPUT DATE & TIME
    // ==============================

    public void enterDateTime(String dateTime) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        dateTimeField
                )
        );

        element.clear();

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));" +
                        "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
                element,
                dateTime
        );
    }


    // ==============================
    // INPUT LOCATION
    // ==============================

    public void enterLocation(String location) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        locationField
                )
        );

        element.clear();
        element.sendKeys(location);
    }


    // ==============================
    // SELECT COVER IMAGE
    // ==============================

    public void selectCoverImage() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        coverMorningRun
                )
        ).click();
    }


    // ==============================
    // INPUT PARTICIPANT LIMIT
    // ==============================

    public void enterParticipantLimit(String limit) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        participantLimitField
                )
        );

        element.clear();
        element.sendKeys(limit);
    }


    // ==============================
    // SELECT VISIBILITY
    // ==============================

    public void selectVisibility(String visibility) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        visibilityDropdown
                )
        );

        Select select = new Select(element);

        select.selectByValue(visibility);
    }


    // ==============================
    // SELECT PRICING
    // ==============================

    public void selectPricing(String pricing) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        pricingDropdown
                )
        );

        Select select = new Select(element);

        select.selectByValue(pricing);
    }


    // ==============================
    // CLICK CREATE EVENT
    // ==============================

    public void clickCreateEvent() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        createEventButton
                )
        ).click();
    }


    // ==============================
    // CREATE EVENT PROCESS
    // ==============================

    public void createEvent(
            String name,
            String category,
            String description,
            String dateTime,
            String location,
            String limit,
            String visibility,
            String pricing
    ) {

        enterName(name);

        enterCategory(category);

        enterDescription(description);

        enterDateTime(dateTime);

        enterLocation(location);

        selectCoverImage();

        enterParticipantLimit(limit);

        selectVisibility(visibility);

        selectPricing(pricing);

        clickCreateEvent();
    }
}
