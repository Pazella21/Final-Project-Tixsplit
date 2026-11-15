
package tests.auth.ui;

import base.ui.BaseUITest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.CommunityPage;
import utils.ConfigReader;

public class CreateCommunityUITest extends BaseUITest {

    @Test
    public void testCreateCommunitySuccess() {

        // 1. Membuka halaman Sign In
        driver.get(
                ConfigReader.getProperty("baseUrl") + "/sign-in"
        );

        // 2. Login menggunakan akun dari config.properties
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                ConfigReader.getProperty("auth006.email"),
                ConfigReader.getProperty("auth006.password")
        );

        System.out.println("Login berhasil diproses.");
        System.out.println("Current URL: " + driver.getCurrentUrl());

        // 3. Membuka halaman Events
        driver.get(
                ConfigReader.getProperty("baseUrl") + "/events"
        );

        // 4. Membuat object CommunityPage
        CommunityPage communityPage = new CommunityPage(driver);

        // 5. Klik Start a community di bagian footer Events
        communityPage.clickStartCommunity();

        System.out.println("Berhasil membuka halaman Create Community.");
        System.out.println("Current URL: " + driver.getCurrentUrl());

        // 6. Mengisi form menggunakan data config.properties
        communityPage.createCommunity(
                ConfigReader.getProperty("community.name"),
                ConfigReader.getProperty("community.description"),
                ConfigReader.getProperty("community.joinPolicy")
        );

        // 7. Menampilkan hasil
        System.out.println("COM-001: Create Community submitted.");
        System.out.println("Current URL: " + driver.getCurrentUrl());
    }
}
