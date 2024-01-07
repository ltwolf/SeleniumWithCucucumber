package steps;

import Base.BaseUtil;


//import cucumber.api.PickleStepTestStep;
//import cucumber.api.TestCase;
//import gherkin.pickles.PickleStep;
//import io.cucumber.core.api.Scenario;
import java.io.File;

import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;


/**
 * Created by Karthik on 31/01/2019.
 */

public class Hook extends BaseUtil{

    private BaseUtil base;
    static String chromeDriver = "C:\\Libs\\chromedriver.exe";
    static String chrome =  "C:\\Libs\\chrome.exe";
    
    public Hook(BaseUtil base) {
        this.base = base;
    }
   
    @Before
    public void InitializeTest(Scenario scenario) {
        /*
            WebDriverManager manager = WebDriverManager.chromedriver();
            manager = manager.proxy(String.format("%s:%s", proxySettings.proxyHost, proxySettings.proxyPort));
            
            String installedVersion = manager.detectBrowserVersion().orElse(null);
            Stream<String> majorVersions = manager.getDriverVersions().stream()
                    .filter(v -> v.startsWith(installedVersion));
            String latestVersion = majorVersions.reduce((a, b) -> b).orElse(null);
            
            if (latestVersion != null) {
                logger.info("Manually detected driver version to " + latestVersion);
                manager.avoidBrowserDetection();
                manager.setDriverVersion(latestVersion);
            } else {
                logger.info("Automatically detect driver version");
            }
        */
        base.scenarioDef = base.features.createNode(scenario.getName());
        System.setProperty("webdriver.chrome.driver", chromedriver);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary(chrome);
        chromeOptions.addArguments("--headless");
        base.Driver = new WebDriverManager.ChromeDriver(chromeOptions);
    }


    @After
    public void TearDownTest(Scenario scenario) {
        if (scenario.isFailed()) {
            //Take screenshot logic goes here
            System.out.println(scenario.getName());
        }
        System.out.println("Closing the browser : MOCK");
        base.Driver.quit();
    }

    @BeforeStep
    public void BeforeEveryStep(Scenario scenario) {
        System.out.println("Before every step " + scenario.getId());
    }

    @AfterStep
    public void AfterEveryStep(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {
        //System.out.println("Before every step " + stepTestStep.getId());
    }

}
