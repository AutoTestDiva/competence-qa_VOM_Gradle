package de.aittr.lms.fwUI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderHelperUI extends BaseHelperUI{
    public HeaderHelperUI(WebDriver driver) {
        super(driver);
    }

    public boolean isSignInLinkPresent() {
        if(isElementPresent(By.cssSelector("[label='Sign in']"))){
            return true;
        }
        return false;
    }



}
