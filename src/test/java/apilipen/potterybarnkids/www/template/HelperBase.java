package apilipen.potterybarnkids.www.template;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperBase {

    protected WebDriver driver;
    public WebDriverWait wait;



    public HelperBase(WebDriver wd) {
        // TODO Auto-generated constructor stub
        this.driver = wd;
        wait = new WebDriverWait(this.driver, 50);
    }



    protected WebElement findWebElement( final By locator) {
        return highlight(driver.findElement(locator));
    }

    protected void clickElement(By locator) {
        findWebElement(locator).click();
    }

    protected void setText(By locator, String text) {
        clickElement(locator);
        findWebElement(locator).clear();
        findWebElement(locator).sendKeys(text);
    }





    public void selectDropDown(final By locator, String option_Name  ) {

        new Select(findWebElement(locator)).selectByVisibleText(option_Name);

    }


    public void selectDropDown_Option(final By locator, String option_Name  ) {

        new Select(findWebElement(locator)).selectByValue(option_Name);

    }


    public WebElement highlight (final WebElement element) {
        //final String originalBackgroundColor  = element.getCssValue("backgroundColor");
        final JavascriptExecutor elementTestJS = ((JavascriptExecutor) driver); // java script
        elementTestJS.executeScript("arguments[0].style.backgroundColor = 'rgb(0,200,0)'",  element);

        return element;
    }


         protected String getCurrentUrlText() {
             return driver.getCurrentUrl().toString();
         }

         protected String getElementText (By locator){
            return driver.findElement(locator).getText();


    }
}
