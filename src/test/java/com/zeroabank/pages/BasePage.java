package com.zeroabank.pages;

import com.zeroabank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = ".brand")
    public WebElement homePageButton;

    @FindBy(xpath = "//a[.='Pay Bills']")
    public WebElement payBill;


}


