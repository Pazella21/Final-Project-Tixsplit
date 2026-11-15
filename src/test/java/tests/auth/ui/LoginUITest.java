package tests.auth.ui;

import base.ui.BaseUITest;
import pages.LoginPage;
import org.testng.annotations.Test;
import utils.ConfigReader;

public class LoginUITest extends BaseUITest {

    @Test
    public void testLoginSuccess() {

        // Buka halaman Sign In
        driver.get("https://tixsplit.dibimbing.id/sign-in");

        // Membuat object LoginPage
        LoginPage loginPage = new LoginPage(driver);

        // Melakukan login
        loginPage.login(
                ConfigReader.getProperty("auth006.email"),
                ConfigReader.getProperty("auth006.password")
        );

        // Menunggu sementara untuk melihat hasil
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}