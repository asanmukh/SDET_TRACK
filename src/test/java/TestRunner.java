import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;
public class TestRunner {

        /**
         * Entry point for the test runner.
         * This method is called by the JVM when the JAR is executed.
         * It instantiates a TestNG object, configures it to run the test suite defined in
         * src/main/resources/testng.xml, and then runs the test suite.
         *
         * @param args the command line arguments passed to the JAR.  These are ignored.
         */
        public static void main(String[] args) {
            TestNG testng = new TestNG();
            List<String> suites = new ArrayList<>();
            suites.add("src/main/resources/testng.xml");
            testng.setTestSuites(suites);
            testng.run();
        }
    }
