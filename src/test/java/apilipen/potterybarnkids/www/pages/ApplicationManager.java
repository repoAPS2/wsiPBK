package apilipen.potterybarnkids.www.pages;

import apilipen.potterybarnkids.www.utils.AuthenticationQA;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
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
import org.openqa.selenium.logging.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;

import org.openqa.selenium.JavascriptExecutor;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
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

     public final String ENV = System.getProperty("env");

     public  String prodUrl = null;
     public  String urlQA = null;
     public  String sUrlQA = null;

     static String usernamePBK = "pbqauser";
     static String passwordPBK = "hm3Furn";

   //  public WebDriverWait wait;

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

          readProperties ();

          System.out.println("Init method ends");



      //    sessionHelper = new SessionHelper(driver);
//          sessionHelper.  login("admin", "secret");
     }


     public void startExecuteTests (  String driverType){
      //  openHomePage(prodUrl);



          System.out.println("BeforeMethod()");
          if (driver == null) {System.out.println("BeforeMethod: driver is null!");}

          try {
               if (ENV.equalsIgnoreCase("qa")) {
                    System.out.println("OpenQAHomePage started.");
                    openQAHomePage(urlQA, sUrlQA, driverType);
               } else if (ENV.equalsIgnoreCase("prod")) {
                    System.out.println("Open Production HomePage started.");
                    openHomePage();


               }
          } catch (Exception ex) {
               System.out.println (ex.getStackTrace());
          }

     }



     public void openQAHomePage(String baseUrl, String secureUrl, String driverType) throws FindFailed, InterruptedException {

//          ((JavascriptExecutor) this.driver).executeScript("alert('Test')");
//          this.driver.switchTo().alert().accept();

          if (driverType.equalsIgnoreCase("Firefox")){
               System.out.println("Login-FF");
               driver.get(secureUrl);
               (new Thread(new AuthenticationQA(driverType,usernamePBK,passwordPBK))).start();  // , logBtn
               System.out.println("Loading " + secureUrl);

               driver.get(baseUrl);
               (new Thread(new AuthenticationQA(driverType,usernamePBK,passwordPBK))).start(); //  , logBtn
               System.out.println("Loading " + baseUrl);

          }


          else if(driverType.equalsIgnoreCase("CHROME")){
               System.out.println("Loading " + secureUrl + ",  Current driverType:" +  driverType);
               driver.get(secureUrl);
                 AuthenticationQA.login(driverType,usernamePBK,passwordPBK); // , logBtn
               // (new Thread(new AuthenticationQA(driverType,usernamePBK,passwordPBK))).start();
               Thread.sleep(1_000L);
               driver.get(baseUrl);
               // (new Thread(new AuthenticationQA(driverType,usernamePBK,passwordPBK))).start();
               AuthenticationQA.login(driverType,usernamePBK,passwordPBK);// , logBtn

               System.out.println("Loading " + baseUrl);
               Thread.sleep(1_000L);
               driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
               driver.manage().window().maximize();

          }

          System.out.println(driverType +  " ......Driver and QA website is running ");
     }






     public void openHomePage() {
          Cookie mck = new Cookie("stickyOverlayLoaded","1","www.potterybarnkids.com","/",null,false);

          driver.get("http://www.potterybarnkids.com/404.hml"); //"http://www.potterybarnkids.com/404.hml" //
          driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
         // driver.manage().window().maximize();


          driver.manage().deleteAllCookies();
          driver.manage().addCookie(mck);
          System.out.println("Cookies was loaded to the site...");

          driver.get("http://" + prodUrl); // prodUrl  "http://www.potterybarnkids.com"
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






     public void readProperties () {
          // String	file_path = "./src/main/resources/DataSource.properties";
          try {
               Properties property = new Properties();

            if    (ENV.equalsIgnoreCase("prod"))
               { property.load(new FileInputStream("./src/test/java/resourses/config/prod.properties"));
                    prodUrl = property.getProperty("store.prod.ip"); }


              else if (ENV.equalsIgnoreCase("qa")) {
                    property.load(new FileInputStream("./src/test/java/resourses/config/qa.properties")) ;

                 urlQA  = "http://" +  property.getProperty("store.prod.ip");
                 sUrlQA=  "http://" +  property.getProperty("surl");
               }


               System.out.println("Propertirs file is set");

          } catch (FileNotFoundException e) {
               System.out.println("Method 'readProperties()' - BLOCK");
               System.out.println();
               System.out.println(e.getMessage());
          } catch (IOException e) {
               System.out.println("Method 'readProperties()' - BLOCK");
               System.out.println();
               System.out.println(e.getMessage());
          }

     }//END readProperties





     //"FF";

     public  void setWebDriver(String browser) throws IOException {  // String browser
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




          if (browser.equalsIgnoreCase("Firefox")) {
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



          } else if (browser.equalsIgnoreCase("Chrome")) {
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
