package apilipen.potterybarnkids.www.pages;

import apilipen.potterybarnkids.www.template.HelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage  extends HelperBase{

    ShoppingCartPage(WebDriver driver) {
        super(driver);
    }


    /// button class="btn btn-primary btn-mondo checkoutButton">
    private final By CHECKOUT_btn = By.xpath("//button[@class=\"btn btn-primary btn-mondo checkoutButton\"]");


    public void checkout (){
        clickElement(CHECKOUT_btn);
    }


}
