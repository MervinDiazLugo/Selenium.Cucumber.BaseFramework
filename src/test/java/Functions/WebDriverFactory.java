package Functions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class WebDriverFactory {

	private static Properties prop = new Properties();
	private static InputStream in = CreateDriver.class.getResourceAsStream("../test.properties");
	private static String resourceFolder;

    /******** Log Attribute ********/
    private static Logger log = Logger.getLogger(WebDriverFactory.class);
    
	private static WebDriverFactory instance = null;
	    
    private WebDriverFactory() {

    }
    
    /**
     * Singleton pattern
     * @return a single instance
     */
    public static WebDriverFactory getInstance() {
        if (instance == null) {
            instance = new WebDriverFactory();
        }
        return instance;
    }    
	
		
	 public static WebDriver createNewWebDriver(String browser, String os) throws IOException {
		 WebDriver driver;
		 prop.load(in);
		 resourceFolder = prop.getProperty("resourceFolder");

		 if ("FIREFOX".equalsIgnoreCase(browser)) {
			 if("WINDOWS".equalsIgnoreCase(os)){
				 System.setProperty("webdriver.gecko.driver", resourceFolder + os + "/geckodriver.exe");
			 }
			 else{
				 System.setProperty("webdriver.gecko.driver", resourceFolder + os + "/geckodriver");
			 }
		     driver = new FirefoxDriver();
		 }
					        
		 /******** The driver selected is Chrome  ********/
					   
	     else if ("CHROME".equalsIgnoreCase(browser)) {
	    	 if("WINDOWS".equalsIgnoreCase(os)){
	    		 System.setProperty("webdriver.chrome.driver", resourceFolder+os+"/chromedriver.exe");            
	    	 }
	    	 else{
	    		 System.setProperty("webdriver.chrome.driver", resourceFolder+os+"/chromedriver");        		 
	    	 }
	         driver = new ChromeDriver();

	     }  
					        
		 /******** The driver selected is Internet Explorer ********/        
	     else if ("INTERNET EXPLORER".equalsIgnoreCase(browser)) {
	    	 System.setProperty("webdriver.ie.driver", resourceFolder+os+"/IEDriverServer.exe");
			 driver = new InternetExplorerDriver();

	     } 
		 /******** The driver is not selected  ********/
	     else {
	    	 log.error("The Driver is not selected properly, invalid name: " + browser + ", " + os);
			 return null;
		 }
		 driver.manage().window().maximize();
	     return driver;

        }
	}
