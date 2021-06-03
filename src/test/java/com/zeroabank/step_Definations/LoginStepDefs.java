package com.zeroabank.step_Definations;

import com.zeroabank.pages.LoginPage;
import com.zeroabank.utilities.ConfigurationReader;
import com.zeroabank.utilities.Driver;
import io.cucumber.java.en.*;

import static com.zeroabank.utilities.BrowserUtils.*;
import static org.junit.Assert.*;

public class LoginStepDefs {
    LoginPage loginPage=new LoginPage();

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url2"));
    }

    @When("user logs in with valid credentials")
    public void user_logs_in_with_valid_credentials() {
     loginPage.login();

    }

    @Then("the page title should be {string}")
    public void the_page_title_should_be(String title) {
        waitUntilTitleIs(title);
        String actualTitle = getCurrentPageTitle();
        assertEquals(title, actualTitle);
    }

        @When("user enters {string} and {string}")
        public void user_enters_and(String username, String password) {
           loginPage.login(username, password);
        }

        @Then("user should not be able to login")
        public void user_should_not_be_able_to_login() {
            String expectedError = "Login and/or password are wrong.";
            String actualError = loginPage.getErrorMessage();
            assertEquals(expectedError, actualError);
        }




    }



