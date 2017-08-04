package apilipen.potterybarnkids.www.pages;

import apilipen.potterybarnkids.www.template.HelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeliveryOptionPage extends HelperBase {

    DeliveryOptionPage (WebDriver driver) {
        super(driver);
    }


    private final By Continue_btn = By.xpath("//button[@class=\"btn btn-primary btn-mondo\"]");


    public DeliveryOptionPage continueCheckout() {

        clickElement(Continue_btn);
        return this;

    }


    }
