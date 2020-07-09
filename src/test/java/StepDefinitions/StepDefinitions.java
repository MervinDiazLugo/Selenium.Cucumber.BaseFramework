package StepDefinitions;

import Functions.SeleniumFunctions;
import cucumber.api.Scenario;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class StepDefinitions{
    WebDriver driver;
    SeleniumFunctions functions = new SeleniumFunctions();

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
        functions.HandleMyWindows.put("Principal", driver.getWindowHandle());
        functions.page_has_loaded();

    }

    @Given("^I navigate to (.*)")
    public void navigateTo(String url){

        log.info("Navigate to: " + url);
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        functions.HandleMyWindows.put("Principal", driver.getWindowHandle());
        functions.page_has_loaded();
    }

    @Given("^I open new tab with URL (.*)")
    public void OpenNewTabWithURL(String url){
        functions.OpenNewTabWithURL(url);
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

    @And("^I click in element (.*)")
    public void iClicInElement(String element) throws Exception {

        functions.iClicInElement(element);

    }

    /** I click in JS element. */
    @And("^I click in JS element (.+)$")
    public void ClickJSElement(String element) throws Exception
    {
        functions.ClickJSElement(element);
    }

    @And("^I double click on element having (.*)")
    public void doubleClick(String element) throws Exception
    {
        functions.doubleClick(element);
    }

    @And("^I set element (.*) with text (.*)")
    public void iSetElementWithText(String element, String text) throws Exception {

        functions.iSetElementWithText(element, text);

    }

    @And("^I set (.*?) with key value (.*?)$")
    public void iSetElementWithKeyValue(String element, String text) throws Exception {

        functions.iSetElementWithKeyValue(element, text);

    }

    /** Assert Text is present be present*/
    @Then("^Assert if (.*?) contains text (.*?)$")
    public void checkPartialTextElementPresent(String element,String text) throws Exception {

        functions.checkPartialTextElementPresent(element, text);

    }

    @Then("^Check if (.*?) NOT contains text (.*?)$")
    public void checkPartialTextElementNotPresent(String element,String text) throws Exception {

        functions.checkPartialTextElementNotPresent(element, text);

    }


    @And("^I take screenshot: (.*)")
    public void takeScreenshot(String TestCaptura) throws IOException
    {
        functions.ScreenShot(TestCaptura);
    }


    @And("I attach a Screenshot to Report")
    public void AttachAScreenshotToReport() {

        functions.attachScreenShot();

    }

    /** Assert if element is present*/
    @Then("^Assert if (.*?) is Displayed$")
    public void checkIfElementIsPresent(String element) throws Exception {

        boolean isDisplayed = functions.isElementDisplayed(element);
        Assert.assertTrue("Element is not present: " + element, isDisplayed);

    }

    /** Assert if element is present*/
    @Then("^Check if (.*?) is NOT Displayed$")
    public void checkIfElementIsNotPresent(String element) throws Exception {

        boolean isDisplayed = functions.isElementDisplayed(element);
        Assert.assertFalse("Element is present: " + element, isDisplayed);
    }

    /** Handle and accept a alert */
    @Then("^I accept alert$")
    public void AcceptAlert()
    {
        functions.AcceptAlert();
    }

    /** Handle and dismiss a alert */
    @Then("^I dismiss alert$")
    public void dismissAlert()
    {
        functions.dismissAlert();
    }

    /** Handle dropdown element by visible text */
    @And("I set text (.*?) in dropdown (.*?)$")
    public void iSetTextInDropdown(String option, String element) throws Exception {

        functions.selectOptionDropdownByText(element, option);
    }

    /** Handle dropdown element by index */
    @And("I set index (.*?) in dropdown (.*?)$")
    public void selectOptionDropdownByIndex(int option, String element) throws Exception {

        functions.selectOptionDropdownByIndex(element, option);

    }

    /** Handle dropdown element by index */
    @And("I set value (.*?) in dropdown (.*?)$")
    public void selectOptionDropdownByValue(String option, String element) throws Exception {

        functions.selectOptionDropdownByValue(element, option);

    }

    /** Check an option from a checkbox */
    @When("^I check the checkbox having (.*?)$")
    public void checkCheckbox(String element) throws Exception
    {
        functions.checkCheckbox(element);
    }

    /** Check an option from a checkbox */
    @When("^I Uncheck the checkbox having (.*?)$")
    public void UncheckCheckbox(String element) throws Exception
    {
        functions.UncheckCheckbox(element);
    }

    /** Refresh current page */
    @And("^I refresh page$")
    public void refreshPage()
    {
        log.info("Reflesh current page");
        driver.navigate().refresh();
    }

    /** Switch to a new windows */
    @When("^I switch to new window$")
    public void switchNewWindow()
    {
        System.out.println(driver.getWindowHandles());
        for(String winHandle : driver.getWindowHandles()){
            System.out.println(winHandle);
            log.info("Switching to new windows");
            driver.switchTo().window(winHandle);
        }
    }

    /** Switch to a new windows */
    @When("^I go to (.*?) window$")
    public void switchNewNamedWindow(String WindowsName)
    {
        functions.WindowsHandle(WindowsName);
    }

    /** Switch to the previous windows */
    @When("^I switch to previous window$")
    public void switchPreviousWindows()
    {
        log.info("Switching of previous windows");
        driver.switchTo().defaultContent();

    }

    /** I switch to Frame */
    @When("^I switch to Frame: (.*?)$")
    public void switchToFrame(String Frame) throws Exception {
        functions.switchToFrame(Frame);

    }

    /** Switch to a new windows by windows title */
    @When("^I switch to window having title \"(.*?)\"$")
    public void switchToNewWindowsByTitle(String windowTitle) throws Exception
    {
        log.info("Switching to the windows by title: " + windowTitle);
        driver.switchTo().window(windowTitle);
    }

    /** Close a windows by title */
    @And("^I close window \"(.*?)\"$")
    public void closeNewWindows(String windowTitle)
    {
        log.info("Switching to the windows by title: " + windowTitle);
        driver.switchTo().window(windowTitle);
        log.info("Closing windows: "+ windowTitle);
        driver.close();
    }

    /** Zoom out until the element is displayed  */
    @And("^I zoom out page till I see element \"(.*?)\"$")
    public void zoomTillElementDisplay(String element) throws Exception
    {
        functions.zoomTillElementDisplay(element);
    }

    /** Scroll to the (top/end) of the page. */
    @And("^I scroll to (top|end) of page$")
    public void scrollPage(String to) throws Exception
    {
        functions.scrollPage(to);
    }

    /** Scroll to an element. */
    @And("^I scroll to element (.+)$")
    public void scrollToElement(String element) throws Exception
    {
        functions.scrollToElement(element);
    }


    @And("I switch to parent frame")
    public void iSwitchToParentFrame() {

        functions.switchToParentFrame();
    }


    /** Navigate forward */
    @And("^I navigate forward")
    public void navigateForward()
    {
        log.info("Navigate forward");
        driver.navigate().forward();
    }

    /** Navigate backward */
    @And("^I navigate back")
    public void navigateBack()
    {
        log.info("Navigate backward");
        driver.navigate().back();
    }


    /** Close the driver instance */
    @And("^I close browser$")
    public void close()
    {
        log.info("Closing browsers");
        driver.close();
    }


    /** Wait for an element to be present for a specific period of time */
    @Then("^I wait for element (.*) to be present$")
    public void waitForElementPresent(String element) throws Exception
    {
        functions.waitForElementPresent(element);
    }

    /** Wait for an element to be visible for a specific period of time */
    @Then("^I wait element (.*?) to be visible$")
    public void waitForElementVisible(String element) throws Exception
    {
        functions.waitForElementVisible(element);
    }


    @Then("^Save as Scenario Context key (.*?) with value (.*?)$")
    public void SaveInScenarioData(String key,String text) throws NoSuchFieldException {

        functions.SaveInScenario(key, text);

    }

    @And("^I Save text of (.*?) as Scenario Context$")
    public void iSaveTextOfElementAsScenarioContext(String element) throws Exception {

        String ScenarioElementText = functions.GetTextElement(element);
        functions.SaveInScenario(element+".text", ScenarioElementText);

    }

    @Given("I set (.*) value in Data Scenario")
    public void iSetEmailValueInDataScenario(String DataScenario) throws IOException {
        functions.RetriveTestData(DataScenario);
    }

    @And("I wait (.*) seconds")
    public void iWaitSeconds(int seconds) throws InterruptedException {
        int secs = seconds * 1000;
        Thread.sleep(secs);
    }

    @Then("Assert if (.*) is equal to (.*)")
    public void assertIfEqualTo(String element, String text) throws Exception {

        functions.checkTextElementEqualTo(element, text);

    }
}
