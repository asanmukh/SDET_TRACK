import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import utilities.GlobalPropertiesReader;

import java.sql.Driver;

public class TestBase {

    @BeforeMethod
    public void setUp() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(GlobalPropertiesReader.getPropertyValue("url"));
    }

}
