package apilipen.potterybarnkids.www.tests;

import apilipen.potterybarnkids.www.model.AddressData;
import apilipen.potterybarnkids.www.model.PaymentData;
import apilipen.potterybarnkids.www.template.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.TestCaseId;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.IOException;

/**
 * Created by anna on 7/19/17.
 */



public class PBKtests extends TestBase {

    @DataProvider(name = "tcID_01_UC")
    public static Object [][] tcID_01_UC() throws IOException {
        return  readInputDataTestID ("tcID_01_UC" ); }  ///./src/test/java/resourses/testData/testData1.csv  dataSource
    @Features({ "Features: Purchasing" })
    @Stories({ "Stories: " })
    @TestCaseId("TestCaseId: TC-06_UC")
    @Description("Unregistered customer performs simple purchase from one department")
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
        return  readInputDataTestID ("tcID_02_UC" ); }  ///./src/test/java/resourses/testData/testData1.csv  dataSource
    @Features({ "Features: Purchasing" })
    @Stories({ "Stories: " })
    @TestCaseId("TestCaseId: TC-07_UC")
    @Description("Unregistered customer performs purchase with multiple quantity \n" +
            "(5 items) from one department to other shipping address different from billing address\n")
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
        return  readInputDataTestID ("tcID_03_UC" ); }  ///./src/test/java/resourses/testData/testData1.csv  dataSource
    @Features({ "Features: Purchasing" })
    @Stories({ "Stories: " })
    @TestCaseId("TestCaseId: TC-08_UC")
    @Description("Unregistered customer performs purchase with multiple quantity \n" +
            "(100 items) from one department \n ")
    @Test (enabled = true, dataProvider = "tcID_03_UC") // true false
    public void test_03_UC ( String ...  param) {

        app.onHomePage().searchItem(param[1]); //"71659"  // 47-8166167
        app.onProdPage().addQuantity(param[2]).addToCartItem();
        String message  = app.onProdPage().errorQTYMessage();
        assertThat(message, equalTo(param[3]));

    }




    @DataProvider(name = "tcID_04_UC")
    public static Object [][] tcID_04_UC() throws IOException {
        return  readInputDataTestID ("tcID_04_UC" ); }   //  , dataSource   //param 0-20
    @Features({ "Features: Purchasing" })
    @Stories({ "Stories: Purchasing" })
    @TestCaseId("TestCaseId: TC-09_UC")
    @Description("Unregistered customer change quantity of items after adding to the Shopping Cart. ")
    @Test (enabled = true, dataProvider = "tcID_04_UC") // true false
    public void test_04_UC ( String ...  param) {

        app.onHomePage().searchItem(param[1]);
        app.onProdPage().addQuantity(param[2]).addToCartItem().checkout();
        app.inShopCart().changeQTY(param[3]).checkout();
        app.onLogin(). checkoutAsGuest();
        app.onAddressPage().createDeliveryOptions (new AddressData()
                .withFullName(param[4])
                .withAddress(param[5])
                .withCity( param[6])
                .withState(param[7])
                .withZip(param[8])
                .withPhone(param[9]), true);
        app.onDelOptPage().continueCheckout();
        app.onPaymentPage().provideCCI(new PaymentData()
                .withCardType(param[10])
                .withCardNumber(param[11])
                .withExpMonth(param[12])
                .withExpYear(param[13])
                .withSecCod(param[14])).enter_emailAddress(param[15]); //.placeOrder();     //   "123"    "mouertu@yopmail.com"


    }




    @DataProvider(name = "tcID_05_UC")
    public static Object [][] tcID_05_UC() throws IOException {
        return  readInputDataTestID ("tcID_05_UC" ); }   //  , dataSource   //param 0-20
    @Features({ "Features: Purchasing" })
    @Stories({ "Stories: " })
    @TestCaseId("TestCaseId: TC-10_UC")
    @Description(" Unregistered customer is able to select size and color of item on the Product page")
    @Test (enabled = true, dataProvider = "tcID_04_UC") // true false
    public void test_05_UC ( String ...  param) {

        app.onHomePage().searchItem(param[1]);


    }

}  //EndOfClass
