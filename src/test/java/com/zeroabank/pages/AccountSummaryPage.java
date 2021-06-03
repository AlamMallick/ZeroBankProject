package com.zeroabank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class AccountSummaryPage extends BasePage {

    @FindBy(xpath = "//h2[@class='board-header']")
    public List<WebElement> accountTypes;

    @FindBy(xpath = "//h2[.='Credit Accounts']/following-sibling::*[1]//th")
    public List<WebElement> creditAccountHeaders;


}
