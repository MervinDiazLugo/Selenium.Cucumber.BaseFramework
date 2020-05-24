package Functions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class CreateDriver {
    private static String browser;
    private static String os;
    private static String logLevel;

    private static Properties prop = new Properties();
    private static InputStream in = CreateDriver.class.getResourceAsStream("../test.properties");
    private static CreateDriver instance = null;

    /******** Log Attribute ********/
    private static Logger log = Logger.getLogger(CreateDriver.class);
    
    /******** Initialize Driver Configuration when the class is instanced ********/
    private CreateDriver() throws IOException {
    	CreateDriver.initConfig();
    }

    /**
     * Singleton pattern
     * @return a single instance

    public static CreateDriver getInstance() throws IOException {
        if (instance == null) {
            instance = new CreateDriver();
        }
        return instance;
    }

    /**
     * Get the Browser from the POM
     */
     public static WebDriver initConfig() throws IOException {
    	WebDriver driver; 
    	
        try {
        	log.info("***********************************************************************************************************");
        	log.info("[ POM Configuration ] - Read the basic properties configuration from: ../test.properties");
            prop.load(in);
            browser = prop.getProperty("browser");
            os = prop.getProperty("os");
            logLevel = prop.getProperty("logLevel");
            
        } catch (IOException e) {
        	log.error("initConfig Error", e);
        }
        
        /******** POM Information ********/
        log.info("[ POM Configuration ] - OS: " + os + " | Browser: " + browser + " |");
        log.info("[ POM Configuration ] - Logger Level: " + logLevel);
        log.info("***********************************************************************************************************");
        
        /****** Load the driver *******/
        driver = WebDriverFactory.createNewWebDriver(browser, os);

        return driver;
    }

}
