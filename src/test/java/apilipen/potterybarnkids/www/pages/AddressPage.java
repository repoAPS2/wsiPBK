package apilipen.potterybarnkids.www.pages;

import apilipen.potterybarnkids.www.model.AddressData;
import apilipen.potterybarnkids.www.template.HelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddressPage extends HelperBase{

    AddressPage (WebDriver driver) {
        super(driver);
    }

    private final By shipToFullName_input = By.name( "shipTo.address.fullName");
    private final By shipToAddress_input = By.name( "shipTo.address.addrLine1");
    private final By shipToCity_input = By.name( "shipTo.address.city");
    private final By shipToState_select = By.name( "shipTo.address.state");
    private final By shipToZip_c_input = By.name(  "shipTo.address.zip");
    private final By shipToPhnum_input = By.name("shipTo.address.dayPhone");

    private final By billToFullName_input = By.name( "address.fullName");
    private final By billToAddress_input = By.name( "address.addrLine1");
    private final By billToCity_input = By.name( "address.city");
    private final By billToState_select = By.name( "address.state");
    private final By billToZip_c_input = By.name(  "address.zip");
    private final By billToPhnum_input = By.name("address.dayPhone");
    private final By emailAddr_input = By.name("address.emailAddr");



    private final By useUsBillAddr_check = By.name("shipTo.billingAddressUpdate");

    private final By Continue_btn = By.xpath("//button[@class=\"btn btn-primary btn-mondo\"]");



    public void enter_Address(By fulln, By addr, By city, By state,
                              By  zip, By phone, AddressData addressData   )  //   final String ph_num  )
    {
        setText(fulln, addressData.getFullName());
        setText(addr, addressData.getAddress());     //   input_Text(address_locator2, address2);
        setText(city, addressData.getCity());
        selectDropDown( state, addressData.getState());
        setText(zip, addressData.getZip());
        setText(phone, addressData.getPhone() );
    }




    public AddressPage createDeliveryOptions (AddressData addressData, boolean useUsBilling ){
        enter_Address(shipToFullName_input, shipToAddress_input, shipToCity_input, shipToState_select,
                shipToZip_c_input , shipToPhnum_input, addressData);

        if (useUsBilling){
                  clickElement(useUsBillAddr_check ); }

        clickElement(Continue_btn);

       return this;
    }

        public AddressPage enterBillingInfo (AddressData addressData){
                enter_Address(billToFullName_input, billToAddress_input, billToCity_input, billToState_select ,
                        billToZip_c_input, billToPhnum_input, addressData);


                return this;
        }

    public AddressPage enter_emailAddress(String email){
        setText(emailAddr_input,  email);
        return  this;
    }



    public AddressPage continueCheckout() {

        clickElement(Continue_btn);
        return  this;
    }
}
