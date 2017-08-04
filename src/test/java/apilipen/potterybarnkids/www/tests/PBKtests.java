package apilipen.potterybarnkids.www.tests;

import apilipen.potterybarnkids.www.model.AddressData;
import apilipen.potterybarnkids.www.model.PaymentData;
import apilipen.potterybarnkids.www.template.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.IOException;

/**
 * Created by anna on 7/19/17.
 */



public class PBKtests extends TestBase {

    @DataProvider(name = "tcID_01_UC")
    public static Object [][] tcID_01_UC() throws IOException {
        return  readInputDataTestID ("tcID_01_UC", "src/test/java/resourses/testData/testData1.csv"); }  ///./src/test/java/resourses/testData/testData1.csv  dataSource

     @Test (enabled = true, dataProvider = "tcID_01_UC") // true false
     public void test_01_UC ( String ...  param){

            app.onHomePage().searchItem(param[1]); //"71659"  // 47-8166167
            app.onProdPage().addQuantity(param[2]).addToCartItem().checkout();
            app.inShopCart().checkout();
            app.onLogin(). checkoutAsGuest();
            app.onAddressPage().createDeliveryOptions (new AddressData()
                                      .withFullName(param[3])           //"Ann Foo"
                                      .withAddress(param[4])            //"1338 Bonita Avenue"
                                      .withCity( param[5])              //"Medford"
                                      .withState(param[6])               //   "Oregon"
                                      .withZip(param[7])                    //"97504"
                                      .withPhone(param[8]), true);                //"6509664717"
            app.onDelOptPage().continueCheckout();
            app.onPaymentPage().provideCCI(new PaymentData()
                                      .withCardType(param[9])               //"Visa"
                                      .withCardNumber(param[10])                 //"4788250000028291"
                                      .withExpMonth(param[11])                   //  "12"
                                      .withExpYear(param[12])                    //    "2020"
                                      .withSecCod(param[13])).enter_emailAddress(param[14]); //.placeOrder();     //   "123"    "mouertu@yopmail.com"

     }




    @DataProvider(name = "tcID_02_UC")
    public static Object [][] tcID_02_UC() throws IOException {
        return  readInputDataTestID ("tcID_02_UC", "src/test/java/resourses/testData/testData1.csv"); }  ///./src/test/java/resourses/testData/testData1.csv  dataSource

    @Test (enabled = true, dataProvider = "tcID_02_UC") // true false
    public void test_02_UC ( String ...  param) {

        app.onHomePage().searchItem(param[1]); //"71659"  // 47-8166167
        app.onProdPage().addQuantity(param[2]).addToCartItem().checkout();
        app.inShopCart().checkout();
        app.onLogin(). checkoutAsGuest();
        app.onAddressPage().createDeliveryOptions (new AddressData()
                .withFullName(param[3])           //"Ann Foo"
                .withAddress(param[4])            //"1338 Bonita Avenue"
                .withCity( param[5])              //"Medford"
                .withState(param[6])               //   "Oregon"
                .withZip(param[7])                    //"97504"
                .withPhone(param[8]) , false);
        app.onDelOptPage().continueCheckout();
        app.onAddressPage().enterBillingInfo(new AddressData()
                .withFullName(param[9])
                .withAddress(param[10])
                .withCity(param[11])
                .withState(param[12])
                .withZip(param[13]).withPhone(param[14])).enter_emailAddress(param[20]).continueCheckout();
        app.onPaymentPage().provideCCI(new PaymentData()
                .withCardType(param[15])               //"Visa"
                .withCardNumber(param[16])                 //"4788250000028291"
                .withExpMonth(param[17])                   //  "12"
                .withExpYear(param[18])                    //    "2020"
                .withSecCod(param[19]) );
    }



    @DataProvider(name = "tcID_03_UC")
    public static Object [][] tcID_03_UC() throws IOException {
        return  readInputDataTestID ("tcID_03_UC", "src/test/java/resourses/testData/testData1.csv"); }  ///./src/test/java/resourses/testData/testData1.csv  dataSource

    @Test (enabled = true, dataProvider = "tcID_03_UC") // true false
    public void test_03_UC ( String ...  param) {

        app.onHomePage().searchItem(param[1]); //"71659"  // 47-8166167
        app.onProdPage().addQuantity(param[2]).addToCartItem();
        String message  = app.onProdPage().errorQTYMessage();
        assertThat(message, equalTo(param[3]));

    }

}
