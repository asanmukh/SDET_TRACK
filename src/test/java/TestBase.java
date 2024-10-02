import Utilities.DriverFactory;
import Utilities.ExtentFactory;
import Utilities.GlobalPropertiesReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Objects;


public class TestBase {

    public ExtentReports extentReports;
    public ExtentSparkReporter extentSparkReporter;

    @BeforeSuite
    public void generationOfReports() {
        extentReports = new ExtentReports();
        extentSparkReporter = new ExtentSparkReporter(GlobalPropertiesReader.getPropertyValue("reportLocation"));
        extentReports.attachReporter(extentSparkReporter);
    }

    @BeforeMethod
    public void setUp(Method m) {
        ExtentFactory.set(extentReports.createTest(m.getName()));
        DriverFactory.set(new ChromeDriver());
        DriverFactory.get().manage().window().maximize();
        DriverFactory.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        DriverFactory.get().manage().deleteAllCookies();
        DriverFactory.get().get(GlobalPropertiesReader.getPropertyValue("url"));
        String browser_name = Objects.requireNonNull(GlobalPropertiesReader.getPropertyValue("browser"),"Browser name is not set, please set the browser name in global.properties");
        if (DriverFactory.get() == null) {
            switch (browser_name.toLowerCase()) {
                case "chrome", "firefox", "edge":
                    DriverFactory.get().get(GlobalPropertiesReader.getPropertyValue("url"));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid browser name: " + browser_name);
            }
        }
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.get().quit();
    }

    @AfterSuite
    public void closeReports() {
        extentReports.flush();
    }


}
