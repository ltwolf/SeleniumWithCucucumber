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
    static String chrome114Path = "C:\\\\Libs\\\\chromedriver.EXE";
    static File chrome114File = new File(chrome114Path);
    
    public Hook(BaseUtil base) {
        this.base = base;
    }
   
    @Before
    public void InitializeTest(Scenario scenario) {
        base.scenarioDef = base.features.createNode(scenario.getName());

        //WebDriverManager.chromedriver().setDriverVersion("114").setup();
        //System.setProperty("webdriver.chrome.driver", "c:\\Libs\\chromedriver.exe");
        
        
            //assumeThat(chrome114File).exists();
            String chrome114Command = String.format(
                    "cmd.exe /C wmic datafile where name=\"%s\" get Version /value",
                    chrome114Path);
            WebDriverManager.chromedriver()
                    .detectBrowserVersion(chrome114Command).setup();
        
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        base.Driver = new ChromeDriver(chromeOptions);
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
