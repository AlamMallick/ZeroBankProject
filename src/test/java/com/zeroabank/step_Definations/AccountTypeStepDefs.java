package com.zeroabank.step_Definations;


import com.zeroabank.pages.AccountSummaryPage;
import com.zeroabank.pages.LoginPage;
import com.zeroabank.utilities.ConfigurationReader;
import com.zeroabank.utilities.Driver;
import io.cucumber.java.en.*;
import java.util.List;
import static com.zeroabank.utilities.BrowserUtils.*;
import static org.junit.Assert.assertEquals;

public class AccountTypeStepDefs {

    @Given("user is logged in")
    public void user_is_logged_in() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        new LoginPage().login();
    }

    @Then("the user should see following account types")
    public void the_user_should_see_following_account_types(List<String> expectedAccountTypes) {
        AccountSummaryPage accountSummaryPage = new AccountSummaryPage();
        List<String> actualAccountTypes = getTextOfElements(accountSummaryPage.accountTypes);
        assertEquals(actualAccountTypes, expectedAccountTypes);
    }
}
