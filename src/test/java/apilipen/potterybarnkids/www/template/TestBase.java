package apilipen.potterybarnkids.www.template;


import apilipen.potterybarnkids.www.pages.ApplicationManager;
import apilipen.potterybarnkids.www.utils.BaseUtils;
import org.openqa.selenium.Cookie;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Created by anna on 7/19/17.
 */
public class TestBase extends  BaseUtils  {


     public static final ApplicationManager app = new ApplicationManager();
     public  static String dataSource = System.getProperty("csv");



    public static String driverType = System.getProperty("browser"); //"FF";


   // String driverType = "Chrome";
     @BeforeClass
     public void setUp() throws Exception {

         app.init(driverType);//  "Firefox"   Chrome

         app.startExecuteTests( driverType );

     }



     @BeforeMethod(alwaysRun = true)
     public void BeforeMethod() {
         app.startExecuteTests( driverType );

         // "https://www.potterybarnkids.com"

     }


     @AfterMethod
     public void afterTest() {
          app.afterEachTest();
     }

     @AfterClass
     public void afterClass() throws IOException {
         app. tearDown()  ;
     }


}
