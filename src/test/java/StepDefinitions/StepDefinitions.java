package StepDefinitions;

import Functions.SeleniumFunctions;
import cucumber.api.Scenario;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class StepDefinitions {
    WebDriver driver;
    SeleniumFunctions functions = new SeleniumFunctions();

    public static final int EXPLICIT_TIMEOUT = 15;

    public String ElementText = "";

    /******** Log Attribute ********/
    Logger log = Logger.getLogger(StepDefinitions.class);

    public StepDefinitions() {
        driver = Hooks.driver;
    }

    /******** Scenario Attributes ********/
    Scenario scenario = null;
    public void scenario (Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("^I am in App main site")
    public void iAmInAppMainSite() throws IOException {

        String url = functions.readProperties("MainAppUrlBase");
        log.info("Navigate to: " + url);
        driver.get(url);
    }

    @Given("^I navigate to (.*)")
    public void navigateTo(String url) throws InterruptedException {

        log.info("Navigate to: " + url);
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Then("^I maximize the windows")
    public void iMaximizeTheWindows() {
        driver.manage().window().maximize();
    }

    @Then("^I load the DOM Information (.*)")
    public void iLoadTheDOMInformation(String file) throws Exception {
        SeleniumFunctions.FileName = file;
        SeleniumFunctions.readJson();
        log.info("initialize file: " + file );
    }

    @And("^I clic in element (.*)")
    public void iClicInElement(String element) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        driver.findElement(SeleniumElement).click();
        log.info("Click on element by " + element);

    }

    @And("^I double click on element having (.*)")
    public void doubleClick(String element) throws Exception
    {
        Actions action = new Actions(driver);
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        action.moveToElement(driver.findElement(SeleniumElement)).doubleClick().perform();
        log.info("Double click on element: " + element);
    }

    @And("^I set element (.*) with text (.*)")
    public void iSetElementEmailWithTextMytext(String element, String text) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        driver.findElement(SeleniumElement).sendKeys(text);
        log.info(String.format("Set on element %s with text %s", element, text));
    }

    /** Assert Text is present be present*/
    @Then("^Assert if (.*) contains text (.*)$")
    public void checkPartialTextElementPresent(String element,String text) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
        wait.until(ExpectedConditions.presenceOfElementLocated(SeleniumElement));
        log.info(String.format("Esperando el elemento: %s", element));

        ElementText = driver.findElement(SeleniumElement).getText();
        boolean isFound = ElementText.indexOf(text) !=-1? true: false;

        Assert.assertTrue("Text is not present in element: " + element, isFound);

    }

    @Then("^Element (.*) NOT contains text (.*)$")
    public void checkPartialTextElementNotPresent(String element,String text) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
        wait.until(ExpectedConditions.presenceOfElementLocated(SeleniumElement));
        log.info(String.format("Esperando el elemento: %s", element));
        ElementText = driver.findElement(SeleniumElement).getText();
        boolean isFoundFalse = ElementText.indexOf(text) !=-1? true: false;
        Assert.assertFalse("Text is present in element: " + element, isFoundFalse);

    }


    @Then("^I take screenshot: (.*)")
    public void takeScreenshot(String TestCaptura) throws IOException
    {
        functions.ScreenShot(TestCaptura);
    }


    @And("I attach a Screenshot to Report")
    public void AttachAScreenshotToReport() {

        functions.attachScreenShot();

    }
}
