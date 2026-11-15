
package tests.auth.ui;

import base.ui.BaseUITest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.CommunityPage;
import pages.EventPage;
import utils.ConfigReader;

public class CreateEventUITest extends BaseUITest {

    @Test
    public void testCreateEventSuccess() {

        // 1. Login
        driver.get(
                ConfigReader.getProperty("baseUrl") + "/sign-in"
        );

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                ConfigReader.getProperty("auth006.email"),
                ConfigReader.getProperty("auth006.password")
        );

        // 2. Buka halaman Community
        driver.get(
                ConfigReader.getProperty("baseUrl")
                        + "/communities/ui-ultra-marathon-6581"
        );

        // 3. Klik Create Event
        CommunityPage communityPage = new CommunityPage(driver);
        communityPage.clickCreateEvent();

        System.out.println("Halaman Create Event berhasil dibuka.");

        // 4. Isi form Create Event dari config.properties
        EventPage eventPage = new EventPage(driver);

        eventPage.createEvent(
                ConfigReader.getProperty("event.name"),
                ConfigReader.getProperty("event.category"),
                ConfigReader.getProperty("event.description"),
                ConfigReader.getProperty("event.dateTime"),
                ConfigReader.getProperty("event.location"),
                ConfigReader.getProperty("event.participantLimit"),
                ConfigReader.getProperty("event.visibility"),
                ConfigReader.getProperty("event.pricing")
        );

        System.out.println("EVT-UI-001: Create Event berhasil dikirim.");
        System.out.println("Current URL: " + driver.getCurrentUrl());
    }
}
