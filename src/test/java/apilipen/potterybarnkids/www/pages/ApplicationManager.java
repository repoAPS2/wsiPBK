package apilipen.potterybarnkids.www.pages;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;


import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 * Created by anna on 7/19/17.
 */
public class ApplicationManager {

     private static WebDriver driver ;
     private HomePage homePage ;
     private ProductPage productPage;
     private LoginPage login;
     private ShoppingCartPage shopcart;
     private DeliveryOptionPage delivop ;
     private AddressPage addressPage;
     private  PaymentPage paymentPage;


// getters of pages classes

     public HomePage onHomePage() {
          return homePage;
     }
     public ProductPage onProdPage() {
          return productPage;
     }
     public ShoppingCartPage inShopCart() {
          return shopcart;
     }
     public LoginPage onLogin() {
          return login;
     }
     public AddressPage onAddressPage() {
          return addressPage;
     }
     public DeliveryOptionPage onDelOptPage() {
          return delivop;
     }
     public PaymentPage onPaymentPage() {
          return paymentPage;
     }
//==================================================================


//     private String url = Config.getStoreURL();
//     private String secureUrlQA = Config.getSecureURL();
//     private String env = Config.getEnv();

//     static String usernamePBK = "pbqauser";
//     static String passwordPBK = "hm3Furn";



     public void init(String browser) throws IOException {
          //	           String in_browser = System.getProperty("browser");
          //    String in_browser = "Chrome"; // Firefox or Chrome or Safari or IE or Edge



          setWebDriver(browser);

          homePage = new HomePage( driver);
          productPage = new ProductPage(driver);
          shopcart = new ShoppingCartPage(driver);
          login = new LoginPage(driver);
          addressPage = new AddressPage(driver);
          delivop = new DeliveryOptionPage(driver);
          paymentPage = new PaymentPage(driver);

          System.out.println("Init method ends");



      //    sessionHelper = new SessionHelper(driver);
//          sessionHelper.  login("admin", "secret");
     }


     public void startExecuteTests ( String baseUrl ){
        openHomePage(baseUrl);



//          System.out.println("BeforeMethod()");
//          if (driver == null) {System.out.println("BeforeMethod: driver is null!");}
//
//          try {
//               if (env.equalsIgnoreCase("qa")) {
//                    System.out.println("OpenQAHomePage started.");
//                    openQAHomePage(url, secureUrlQA);
//               } else if (env.equalsIgnoreCase("prod")) {
//                    openHomePage(url);
//               }
//          } catch (Exception ex) {
//               System.out.println (ex.getStackTrace());
//          }

     }





     public void openHomePage(String baseUrl) {
          Cookie mck = new Cookie("stickyOverlayLoaded","1","www.potterybarnkids.com","/",null,false);

          driver.get("http://www.potterybarnkids.com/404.hml"); //"http://www.potterybarnkids.com/404.hml" //
          driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
         // driver.manage().window().maximize();


          driver.manage().deleteAllCookies();
          driver.manage().addCookie(mck);
          System.out.println("Cookies was loaded to the site...");

          driver.get(baseUrl);
       //   driver.manage().window().maximize();
          System.out.println(" ......Driver and website is running ");
        //  driver.manage().window().maximize();



     }






     public void afterEachTest() {
          driver.manage().deleteAllCookies();

     }

     public void stop() {
          driver.quit();
     }

     public void tearDown()  throws IOException {


          driver.close();
       //
     }



     public void titleIs (){
          String title =  driver.getTitle();
          System.out.println("Page Title is : "  + title);
     }





     //"FF";

     public static void setWebDriver(String browser) throws IOException {  // String browser
         // String browser = driverType ;//System.getProperty("browser");
          System.out.println("Driver type is: ["  +   browser   + "]");
          Logger logger = Logger.getLogger("");
          logger.setLevel(Level.OFF);
          String driverPath = "";

          if ((browser.toLowerCase().contains("firefox")) && (System.getProperty("os.name").toUpperCase().contains("MAC")))
          {driverPath = "./src/test/java/resourses/webdrivers/mac/geckodriver";}  // geckodriver.sh"

          else if ((browser.toLowerCase().contains("firefox")) && (System.getProperty("os.name").toUpperCase().contains("WINDOWS")))
          {driverPath = "./src/test/java/resourses/webdrivers/pc/geckodriver.exe";}


          else if ((browser.toLowerCase().contains("chrome")) && (System.getProperty("os.name").toUpperCase().contains("MAC")))
          {driverPath = "./src/test/java/resourses/webdrivers/mac/chromedriver";}



          else if ((browser.toLowerCase().contains("chrome")) && (System.getProperty("os.name").toUpperCase().contains("WINDOWS")))

          {driverPath = "./src/main/resources/webdrivers/pc/chromedriver.exe";}



          else if ((browser.toLowerCase().contains("safari")) && (System.getProperty("os.name").toUpperCase().contains("MAC")))
          {}
          else if ((browser.toLowerCase().contains("safari")) && (System.getProperty("os.name").toUpperCase().contains("WINDOWS")))
          {throw new IllegalArgumentException("Safari is not available for Windows");}



          else if ((browser == "IE") && (System.getProperty("os.name").toUpperCase().contains("MAC")))

          {throw new IllegalArgumentException("Internet Explorer is not available for macOS");}

          else if ((browser == "IE") && (System.getProperty("os.name").toUpperCase().contains("WINDOWS")))

          {driverPath = "./src/main/resources/webdrivers/pc/IEDriverServer.exe";}

          else if ((browser == "Edge") && (System.getProperty("os.name").toUpperCase().contains("MAC")))

          {throw new IllegalArgumentException("Microsoft Edge is not available for macOS");}

          else if ((browser == "Edge") && (System.getProperty("os.name").toUpperCase().contains("WINDOWS")))

          {driverPath = "./src/main/resources/webdrivers/pc/MicrosoftWebDriver.exe";}

          else if (browser == "HtmlUnit") {}

          else   {throw new IllegalArgumentException("Unknown OS");}




          if (browser.equals("Firefox")) {
               System.setProperty("webdriver.gecko.driver", driverPath);  // String binaryPath = "/Applications/Firefox54.app/Contents/MacOS/firefox-bin";

               String binaryPath = "/Applications/Firefox54.app/Contents/MacOS/firefox-bin";
               FirefoxOptions options = new FirefoxOptions();;
               options.setBinary(binaryPath); //This is the location where you have installed Firefox on your machine


               DesiredCapabilities capabilities = DesiredCapabilities.firefox();
               capabilities.setCapability("moz:firefoxOptions", options);
               capabilities.setCapability(FirefoxDriver.MARIONETTE, true);

               driver = new FirefoxDriver(capabilities);
               driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
               driver.manage().window().maximize();

          } else if (browser.equals("Chrome")) {
               System.setProperty("webdriver.chrome.driver", driverPath);
               System.setProperty("webdriver.chrome.silentOutput", "true");

               ChromeOptions option = new ChromeOptions();

               if (System.getProperty("os.name").toUpperCase().contains("MAC")) {
                    option.addArguments("-start-fullscreen");

               } else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))


               {
                    option.addArguments("--start-maximized");
               } else {
                    throw new IllegalArgumentException("Unknown OS");
               }
               driver = new ChromeDriver(option);
               driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


          } else if (browser.equals("Safari")) {
               SafariOptions options = new SafariOptions();
               options.setUseCleanSession(true);
               options.setPort(55555);
               driver = new SafariDriver(options);
               driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
               driver.manage().window().maximize();

          } else if (browser.equals("IE")) {
               DesiredCapabilities IEDesiredCapabilities = DesiredCapabilities.internetExplorer();
               IEDesiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
               IEDesiredCapabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");

               IEDesiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
               IEDesiredCapabilities.setJavascriptEnabled(true);
               IEDesiredCapabilities.setCapability("enablePersistentHover", false);

               System.setProperty("webdriver.ie.driver", driverPath);
               driver = new InternetExplorerDriver(IEDesiredCapabilities);
               driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
               driver.manage().window().maximize();

          } else if (browser.equals("Edge")) {
               System.setProperty("webdriver.edge.driver", driverPath);
               driver = new EdgeDriver();
               driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
               driver.manage().window().maximize();


          } else if (browser.equals("HtmlUnit")) {
               driver = new HtmlUnitDriver();
               ((HtmlUnitDriver) driver).setJavascriptEnabled(true);

          } else {
               throw new IllegalArgumentException("Unknown Broweser");
          }
     }

}
