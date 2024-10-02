import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import Utilities.GlobalPropertiesReader;
import java.lang.reflect.Method;


public class TestBase {

    public ExtentReports extentReports;
    public ExtentSparkReporter extentSparkReporter;


    @BeforeMethod
    public void setUp(Method m) {
        extentReports.createTest(m.getName());
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(GlobalPropertiesReader.getPropertyValue("url"));
    }

    @BeforeSuite
    public void generationOfReports() {
        extentReports = new ExtentReports();
        extentSparkReporter = new ExtentSparkReporter(GlobalPropertiesReader.getPropertyValue("reportLocation"));
        extentReports.attachReporter(extentSparkReporter);
    }

    @AfterSuite
    public void closeReports() {
        extentReports.flush();
    }

}
