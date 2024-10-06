import Utilities.DriverFactory;
import Utilities.ExtentFactory;
import Utilities.GlobalPropertiesReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
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
        extentSparkReporter.config().thumbnailForBase64(true);
        extentSparkReporter.config().setDocumentTitle("Amazon.in Automation Test Report");
        extentSparkReporter.config().setReportName("Amazon.in Application");
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Screenshot", "Yes");
        extentReports.setSystemInfo("Image", "Enabled");
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
    public void tearDown(ITestResult result) {
        ExtentTest extentTest = ExtentFactory.get();
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                // Take a screenshot
                File screenshot = ((TakesScreenshot) DriverFactory.get()).getScreenshotAs(OutputType.FILE);
                // Add the screenshot to the Extent Report
                extentTest.addScreenCaptureFromPath(screenshot.getAbsolutePath(), "Screenshot");
                extentTest.fail("Test failed");
            } catch (Exception e) {
                System.out.println("Error taking screenshot: " + e.getMessage());
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentFactory.get().pass("Test passed");
        }
    }

    @AfterSuite
    public void closeReports() {
        extentReports.flush();
    }


}
