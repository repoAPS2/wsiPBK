package apilipen.potterybarnkids.www.pages;

import apilipen.potterybarnkids.www.template.HelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage  extends HelperBase{



    ProductPage (WebDriver driver) {
       super(driver);
    }



    private final By qty_input  = By.xpath("//input[@class=\"qty\"]");
    private final By addToCart_btn = By.xpath("//fieldset[@class=\"add-to-basket\"]/button");
    private final By checkout_PopUpbtn = By.xpath("//*[@id=\"btn-checkout\"]");
    private final By wrongQTY_mess = By.xpath("//div[@class=\"specific-error\"]");

    //div id="btn-checkout"  id="anchor-btn-checkout"


    public  void addToCartItem (String qty){
          addQuantity(qty);
          clickElement(addToCart_btn);
           checkout ();

    }


    public  ProductPage addToCartItem(){
        clickElement(addToCart_btn);
        return this;
    }


    public ProductPage addQuantity(String qty) {
        setText( qty_input, qty);

        return this;
    }


    public ProductPage checkout (){
        clickElement(checkout_PopUpbtn);
        return this;
    }


    public String errorQTYMessage(){
        return  getElementText(wrongQTY_mess);
    }
}
