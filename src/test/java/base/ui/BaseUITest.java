
package base.ui;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

public class BaseUITest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = DriverManager.getDriver();

        driver.get(
                ConfigReader.getProperty("baseUrl")
        );
    }

    @AfterMethod
    public void tearDown() {
        // Browser tidak ditutup sementara untuk debugging.
    }
}
