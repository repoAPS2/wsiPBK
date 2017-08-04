package apilipen.potterybarnkids.www.pages;

import apilipen.potterybarnkids.www.template.HelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends HelperBase{






    LoginPage (WebDriver driver) {
        super(driver);
    }


    private final By checkoutAsGuest_btn = By.xpath( "//div[@id=\"login\"]/main/section/section[2]/form/div/button");

    public void checkoutAsGuest(){
        clickElement(checkoutAsGuest_btn);

    }

}
