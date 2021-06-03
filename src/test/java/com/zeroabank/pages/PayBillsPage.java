package com.zeroabank.pages;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import static com.zeroabank.utilities.BrowserUtils.*;
import static com.zeroabank.utilities.DataUtils.generateSingleNum;


public class PayBillsPage extends BasePage {


    @FindBy(xpath = "//input[@name=\"name\"]")
    public WebElement payeeName;
    @FindBy(xpath = "//textarea[@name='address']")
    public WebElement payeeAddress;
    @FindBy(xpath = "//input[@name=\"account\"]")
    public WebElement payeeAccount;
    @FindBy(xpath = "//input[@name=\"details\"]")
    public WebElement payeeDetails;

    @FindBy(xpath = "//div[@id='alert_content']")
    public WebElement alertMessage;

    @FindBy(id = "pay_saved_payees")
    public WebElement payButton;

    @FindBy(xpath = "//li[contains(@class,'state-active')]/../following-sibling::div[@id='ui-tabs-1']//input[@name='amount']")
    public WebElement payAmount;

    @FindBy(xpath = "//input[@name='date']")
    public WebElement date;

    @FindBy(name = "currency")
    public WebElement currencyDropdown;

    @FindBy(id = "add_new_payee")
    public WebElement addButton;

    @FindBy(xpath = "//li[contains(@class,'state-active')]")
    public WebElement activeTab;

    @FindBy(xpath = "//li[contains(@class,'state-active')]/../following-sibling::div[@id='ui-tabs-3']//input[@name='amount']")
    public WebElement currencyAmount;

    @FindBy(xpath = "//input[@id='pc_calculate_costs']")
    public WebElement calculateCost;

    @FindBy(xpath = "//input[@id='pc_inDollars_true']")
    public WebElement selectedCurrencyRadio;

    @FindBy(xpath = "//a[.='Add New Payee']")
    public WebElement addNewPayee;



    public String alertMessage() {
        String message = "";
        try {
            message = getAlertText();
        } catch (NoAlertPresentException e) {
            message = "";
        }
        try {
            if (alertMessage.isDisplayed()) {
                message = alertMessage.getText().trim();
            }
        } catch (NoSuchElementException e) {
            message = "";
        }
        turnOnImplicitWait();
        if (message.isEmpty()) {
            if (!validPayAmount(payAmount.getAttribute("value"))) {
                message = payAmount.getAttribute("validationMessage");
            } else if (!validPayDate(date.getAttribute("value"))) {
                message = date.getAttribute("validationMessage");
            }
        }
        return message;
    }

    public boolean validPayAmount(String amount) {
        if (amount.isEmpty()) {
            return false;
        }
        for (char eachChar : amount.toCharArray()) {
            if (Character.isLetter(eachChar) || !Character.isLetterOrDigit(eachChar)) {
                return false;
            }
        }
        return true;
    }

    public boolean validPayDate(String date) {
        if (date.isEmpty()) {
            return false;
        }
        for (char eachChar : date.toCharArray()) {
            if (Character.isLetter(eachChar)) {
                return false;
            }
        }
        return true;
    }

    public List<String> availableCurrencies() {
        List<WebElement> options = getDropdown(currencyDropdown).getOptions();
        options.remove(0);
        return getTextOfElements(options);
    }

    public void enterCurrencyAmount(String amount) {
        waitUntilClickable(currencyAmount).sendKeys(amount);
    }

    public void calculateCost() {
        clickElement(calculateCost);
    }

    public void selectCurrency() {
        Select currencies = getDropdown(currencyDropdown);
        currencies.selectByIndex(generateSingleNum(1, (currencies.getOptions().size() - 1)));
        clickElement(selectedCurrencyRadio);
    }
}
