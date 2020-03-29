package Functions;

import StepDefinitions.Hooks;
import cucumber.api.Scenario;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Properties;


public class SeleniumFunctions {
    static WebDriver driver;
    public static Properties prop = new Properties();
    public static InputStream in = SeleniumFunctions.class.getResourceAsStream("../test.properties");

    public SeleniumFunctions() {
        driver = Hooks.driver;
    }

    public static final int EXPLICIT_TIMEOUT = 5;

    /******** Scenario Attributes ********/
    Scenario scenario = null;
    public void scenario (Scenario scenario) {
        this.scenario = scenario;
    }

    public String readProperties(String property) throws IOException {
        prop.load(in);
        return prop.getProperty(property);
    }

    /******** Log Attribute ********/
    private static Logger log = Logger.getLogger(SeleniumFunctions.class);
    public static String FileName = "";
    public static String PagesFilePath = "src/test/resources/Pages/";

    public static String GetFieldBy = "";
    public static String ValueToFind = "";

    public static Object readJson() throws Exception {
        FileReader reader = new FileReader(PagesFilePath + FileName);
        try {

            if (reader != null) {
                JSONParser jsonParser = new JSONParser();
                return jsonParser.parse(reader);
            } else {
                return null;
            }
        } catch (FileNotFoundException e) {
            log.error("ReadEntity: No existe el archivo " + FileName);
            return null;
        } catch (NullPointerException e) {

            log.error("ReadEntity: No existe el archivo " + FileName);
            throw new IllegalStateException("ReadEntity: No existe el archivo " + FileName, e);

        }

    }

    public static JSONObject ReadEntity(String element) throws Exception {
        JSONObject Entity = null;

        JSONObject jsonObject = (JSONObject) readJson();
        Entity = (JSONObject) jsonObject.get(element);
        log.info(Entity.toJSONString());
        return Entity;

    }

    public void ScreenShot(String TestCaptura) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmm");
        String screenShotName = readProperties("ScreenShotPath") + "\\" + readProperties("browser") + "\\" + TestCaptura + "_(" + dateFormat.format(GregorianCalendar.getInstance().getTime()) + ")";
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        log.info("Screenshot saved as:" + screenShotName);
        FileUtils.copyFile(scrFile, new File(String.format("%s.png", screenShotName)));
    }

    public byte[] attachScreenShot(){

        log.info("Attaching Screenshot");
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        return screenshot;

    }

    public boolean isElementDisplayed(String element) throws Exception {
        boolean isDisplayed = Boolean.parseBoolean(null);
        try {
            By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
            log.info(String.format("Waiting Element: %s", element));
            WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
            isDisplayed = wait.until(ExpectedConditions.presenceOfElementLocated(SeleniumElement)).isDisplayed();
        }catch (NoSuchElementException | TimeoutException e){
            isDisplayed = false;
            log.info(e);
        }
        log.info(String.format("%s visibility is: %s", element, isDisplayed));
        return isDisplayed;
    }

    public void AcceptAlert()
    {
        try{
            WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
            log.info("The alert was accepted successfully.");
        }catch(Throwable e){
            log.error("Error came while waiting for the alert popup. "+e.getMessage());
        }
    }

    public void dismissAlert()
    {
        try{
            WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.dismiss();
            log.info("The alert was dismissed successfully.");
        }catch(Throwable e){
            log.error("Error came while waiting for the alert popup. "+e.getMessage());
        }
    }

    public static By getCompleteElement(String element) throws Exception {
        By result = null;
        JSONObject Entity = ReadEntity(element);

        GetFieldBy = (String) Entity.get("GetFieldBy");
        ValueToFind = (String) Entity.get("ValueToFind");

        if ("className".equalsIgnoreCase(GetFieldBy)) {
            result = By.className(ValueToFind);
        } else if ("cssSelector".equalsIgnoreCase(GetFieldBy)) {
            result = By.cssSelector(ValueToFind);
        } else if ("id".equalsIgnoreCase(GetFieldBy)) {
            result = By.id(ValueToFind);
        } else if ("linkText".equalsIgnoreCase(GetFieldBy)) {
            result = By.linkText(ValueToFind);
        } else if ("name".equalsIgnoreCase(GetFieldBy)) {
            result = By.name(ValueToFind);
        } else if ("partialLinkText".equalsIgnoreCase(GetFieldBy)) {
            result = By.partialLinkText(ValueToFind);
        } else if ("tagName".equalsIgnoreCase(GetFieldBy)) {
            result = By.tagName(ValueToFind);
        } else if ("xpath".equalsIgnoreCase(GetFieldBy)) {
            result = By.xpath(ValueToFind);
        }
        return result;
    }

}