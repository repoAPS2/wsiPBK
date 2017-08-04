package apilipen.potterybarnkids.www.pages;

import apilipen.potterybarnkids.www.template.HelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage extends HelperBase {



    HomePage(WebDriver driver) {
        //  this.driver = wd;
        super(driver);
    }




    private final By search_field = By.name("words");

    public void searchItem ( String sku){
        setText(search_field, sku);
        findWebElement(search_field).sendKeys(Keys.ENTER);  ;
    }



}  // End Of Class
