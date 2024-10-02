import Utilities.DriverFactory;
import Utilities.ExtentFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import Utilities.GlobalPropertiesReader;
import java.lang.reflect.Method;
import java.time.Duration;


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
        DriverFactory.getDriver().manage().window().maximize();
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        DriverFactory.getDriver().manage().deleteAllCookies();
        DriverFactory.getDriver().get(GlobalPropertiesReader.getPropertyValue("url"));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.getDriver().quit();
    }

    @AfterSuite
    public void closeReports() {
        extentReports.flush();
    }


}
