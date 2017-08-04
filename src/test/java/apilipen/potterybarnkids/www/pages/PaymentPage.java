package apilipen.potterybarnkids.www.pages;

import apilipen.potterybarnkids.www.model.PaymentData;
import apilipen.potterybarnkids.www.template.HelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage   extends HelperBase {

    PaymentPage (WebDriver driver) {
        super(driver);
    }

    private final By Continue_btn = By.xpath("//*[@id=\"order-difficulties\"]/main/div/form/div/button");


    private final By ccType_select = By.xpath("//select[@id=\"creditCard.payType\"]");
    private final By ccNumber_input = By.xpath("//input[@id=\"creditCard.cardNumber\"]");
    private final By ccExpMonth_select = By.xpath( "//select[@id=\"creditCard.cardExpMonth\"]");
    private final By ccExpYr_select = By.xpath("//select[@id=\"creditCard.cardExpYear\"]");
    private final By ccsecCod_input = By.xpath(" //input[@id=\"creditCard.cardVerificationNumber\"]");

    private final By emailAddr_input = By.xpath("//input[@id=\"emailAddr\"]");

    private final By PLACE_ORDER_btn = By.xpath("//form[@id=\"paymentInformation\"]/div[6]/button"); // //form[@id="paymentInformation"]/div[6]/button




    public PaymentPage provideCCI (PaymentData paymentData){
        checkOrderIssue ();
        selectDropDown( ccType_select, paymentData.getCardType());
        setText(ccNumber_input, paymentData.getCardNumber() );
        selectDropDown( ccExpMonth_select,paymentData.getExpMonth() );
        selectDropDown( ccExpYr_select, paymentData.getExpYear() );
        setText( ccsecCod_input,  paymentData.getSecCod() );

        return  this;
    }


    public PaymentPage enter_emailAddress(String email){
        setText(emailAddr_input,  email);
        return  this;
    }

    public PaymentPage placeOrder (){
        clickElement(PLACE_ORDER_btn);
        return this;
       }


    public void checkOrderIssue (){
        if ( ! getCurrentUrlText().contains("ordererror")){

        }

        else{
            clickElement(Continue_btn);
        }
    }

}
