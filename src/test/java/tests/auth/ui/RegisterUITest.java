
package tests.auth.ui;

import base.ui.BaseUITest;
import org.testng.annotations.Test;
import org.testng.Assert;
import pages.RegistrationPage;
import utils.ConfigReader;
import utils.Utils;
import java.util.Locale;

public class RegisterUITest extends BaseUITest {

    private RegistrationPage openRegistrationPage() {
        driver.get(
                ConfigReader.getProperty("baseUrl") + "/create-account"
        );

        return new RegistrationPage(driver);
    }

    // TC-AUTH-001: Register dengan data valid
    @Test
    public void testRegisterValidData() {

        RegistrationPage page = openRegistrationPage();

        page.register(
                ConfigReader.getProperty("auth001.name"),
                Utils.generateRandomEmail(),
                ConfigReader.getProperty("auth001.password")
        );

        System.out.println("AUTH-001: " + page.getPageMessage());
    }

    // TC-AUTH-002: Password tepat 8 karakter
    @Test
    public void testRegisterPasswordExactly8Characters() {

        RegistrationPage page = openRegistrationPage();

        page.register(
                ConfigReader.getProperty("auth002.name"),
                Utils.generateRandomEmail(),
                ConfigReader.getProperty("auth002.password")
        );

        System.out.println("AUTH-002: " + page.getPageMessage());
    }

    // TC-AUTH-003: Email huruf kapital
    @Test
    public void testRegisterUppercaseEmail() {

        RegistrationPage page = openRegistrationPage();

        String email = Utils.generateRandomEmail()
                .toUpperCase(Locale.ROOT);

        page.register(
                ConfigReader.getProperty("auth003.name"),
                email,
                ConfigReader.getProperty("auth003.password")
        );

        System.out.println("AUTH-003: " + page.getPageMessage());
    }

    // TC-AUTH-004: Password kurang dari 8 karakter
    @Test
    public void testRegisterPasswordLessThan8Characters() {

        RegistrationPage page = openRegistrationPage();

        page.register(
                ConfigReader.getProperty("auth004.name"),
                Utils.generateRandomEmail(),
                ConfigReader.getProperty("auth004.password")
        );

        System.out.println("AUTH-004: " + page.getPageMessage());

        Assert.assertTrue(
                driver.getCurrentUrl().contains("create-account"),
                "Registrasi dengan password kurang dari 8 karakter seharusnya ditolak"
        );
    }

    // TC-AUTH-005: Email sudah terdaftar
    @Test
    public void testRegisterExistingEmail() {

        RegistrationPage page = openRegistrationPage();

        page.register(
                ConfigReader.getProperty("auth005.name"),
                ConfigReader.getProperty("auth005.email"),
                ConfigReader.getProperty("auth005.password")
        );

        System.out.println("AUTH-005: " + page.getPageMessage());
    }
}
