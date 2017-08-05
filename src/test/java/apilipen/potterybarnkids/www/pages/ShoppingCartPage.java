package apilipen.potterybarnkids.www.pages;

import apilipen.potterybarnkids.www.template.HelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage  extends HelperBase{

    ShoppingCartPage(WebDriver driver) {
        super(driver);
    }



    private final By CHECKOUT_btn = By.xpath("//button[@class=\"btn btn-primary btn-mondo checkoutButton\"]");
    private final By QTY_input = By.cssSelector("input.quantity-box");
    private final By UPDATE_link = By.className("update-item updateItemBtn"); //cssSelector("a.update-item updateItemBtn"); "//a[@class="update-item updateItemBtn"]"

    public void checkout (){
        clickElement(CHECKOUT_btn);
    }


    public ShoppingCartPage changeQTY (String  textqty){

        setText(QTY_input, textqty);
        findWebElement(QTY_input).sendKeys(Keys.ENTER);
       return this;
    }



}
