import Utilities.DriverFactory;
import Utilities.ExtentFactory;
import Utilities.GlobalPropertiesReader;
import Utilities.WebActions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
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

    /**
     * Initializes the ExtentReports object and its associated
     * {@link ExtentSparkReporter} object.
     * Attaches the reporter to the
     * ExtentReports object and sets various configuration options.
     * This method is annotated with {@link BeforeSuite} and runs once
     * for the entire test suite.
     */
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


    /**
     * Initializes the test environment by creating a new {@link WebDriver} instance
     * according to the browser name specified in the {@code global.properties} file.
     * The browser instance is configured to run in headless mode if the value of
     * the {@code headless} property is {@code yes}.
     * The instance is then stored in
     * the {@link DriverFactory} for use by the test methods.
     * @param m the {@link Method} that is being invoked
     */
    @BeforeMethod
    public void setUp(Method m) {
        ExtentFactory.set(extentReports.createTest(m.getName()));
        String browser_name = Objects.requireNonNull(GlobalPropertiesReader.getPropertyValue("browser"),"Browser name is not set, please set the browser name in global.properties");
        String headless = GlobalPropertiesReader.getPropertyValue("headless");
        WebDriver driver = null;
        switch (browser_name.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if ("yes".equalsIgnoreCase(headless)) {
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--window-size=1920,1080");
                }
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if ("yes".equalsIgnoreCase(headless)) {
                    firefoxOptions.addArguments("--headless");
                    firefoxOptions.addArguments("--disable-gpu");
                    firefoxOptions.addArguments("--window-size=1920,1080");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if ("yes".equalsIgnoreCase(headless)) {
                    edgeOptions.addArguments("--headless");
                    edgeOptions.addArguments("--disable-gpu");
                    edgeOptions.addArguments("--window-size=1920,1080");
                }
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                throw new IllegalArgumentException("Invalid browser name: " + browser_name);
        }
            DriverFactory.set(driver);
            DriverFactory.get().manage().window().maximize();
            DriverFactory.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            DriverFactory.get().manage().deleteAllCookies();
            DriverFactory.get().get(GlobalPropertiesReader.getPropertyValue("url"));
    }

    /**
     * If the test fails, get a screenshot and log it to the report.
     * @param result The test result.
     */
    @AfterMethod
    public void tearDown(ITestResult result) {
       if (!result.isSuccess()) {
           String base64Screenshot = new WebActions(DriverFactory.get()).getBase64Screenshot();
           ExtentFactory.get().fail(result.getThrowable());
           ExtentFactory.get().fail("screenshot", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
       }
       DriverFactory.get().quit();
    }

    /**
     * Closes the Extent report, flushing all data to the file specified when the
     * report was created.
     */
    @AfterSuite
    public void closeReports() {
        extentReports.flush();
    }
}
