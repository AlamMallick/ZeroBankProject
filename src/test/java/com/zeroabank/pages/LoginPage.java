package com.zeroabank.pages;

import com.zeroabank.utilities.ConfigurationReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.zeroabank.utilities.BrowserUtils.clickElement;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@id='user_login']")
    public WebElement usernameInput;

    @FindBy(xpath = "//input[@id='user_password']")
    public WebElement passwordInput;

    @FindBy(css = "input[name='submit']")
    public WebElement submitButton;

    @FindBy(xpath = "//div[contains(@class,'alert-error')]")
    public WebElement errorMessage;

    public void login() {
        String username = ConfigurationReader.getProperty("username");
        String password = ConfigurationReader.getProperty("password");

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        clickElement(submitButton);
    }

    public void login(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        clickElement(submitButton);
    }

    public String getErrorMessage() {
        return errorMessage.getText().trim();
    }

}


