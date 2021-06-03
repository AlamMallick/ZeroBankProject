package com.zeroabank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static com.zeroabank.utilities.BrowserUtils.*;


public class AccountActivityPage extends BasePage {

    @FindBy(xpath = "//a[.='Account Activity']")
    public WebElement accountActivity;

    @FindBy(xpath = "//select[@name='accountId']")
    public WebElement accountDropdown;

    @FindBy(xpath = "//div[@id='all_transactions_for_account']//th")
    public List<WebElement> transactionsTableHeaders;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement findButton;

    @FindBy(xpath = "//div[contains(@id,'filtered_transactions')]")
    public WebElement searchResult;

    @FindBy(xpath = "//input[@name='description']")
    public WebElement descriptionInput;

    @FindBy(xpath = "//div[contains(@id,'filtered_transactions')]//td[1]")
    public List<WebElement> dates;

    @FindBy(xpath = "//div[contains(@id,'filtered_transactions')]//td[2]")
    public List<WebElement> descriptions;

    @FindBy(xpath = "//div[contains(@id,'filtered_transactions')]//td[3]")
    public List<WebElement> deposits;

    @FindBy(xpath = "//div[contains(@id,'filtered_transactions')]//td[4]")
    public List<WebElement> withdrawals;

    @FindBy(name = "type")
    public WebElement typeDropdown;

    public String selectedAccount() {
        return getDropdown(accountDropdown).getFirstSelectedOption().getText().trim();
    }

    public List<String> allAccountOptions() {
        return getTextOfElements(getDropdown(accountDropdown).getOptions());
    }

    public List<String> transactionsTableHeaders() {
        return getTextOfElements(transactionsTableHeaders);
    }


    public void selectTransactionType(String type) {
        getDropdown(typeDropdown).selectByVisibleText(type);
    }

    public void enterDate(String dateType, String date) {
        getElement("//input[@name='%sDate']", dateType).clear();
        getElement("//input[@name='%sDate']", dateType).sendKeys(date);
    }

    public void enterDescription(String description) {
        descriptionInput.clear();
        descriptionInput.sendKeys(description);
    }

    public void submitFind() {
        waitUntilClickable(findButton).click();
    }




}
