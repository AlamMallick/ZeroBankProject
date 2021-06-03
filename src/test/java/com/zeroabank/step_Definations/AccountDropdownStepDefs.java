package com.zeroabank.step_Definations;
import com.zeroabank.pages.AccountActivityPage;
import io.cucumber.java.en.*;
import java.util.List;
import static com.zeroabank.utilities.BrowserUtils.waitUntilTitleIs;
import static org.junit.Assert.assertEquals;
public class AccountDropdownStepDefs {



    @When("user navigates to Account Activity")
    public void user_navigates_to_account_activity() {
        new AccountActivityPage().accountActivity.click();
    }

    @Then("the Account dropdown selected option should be {string}")
    public void the_Account_dropdown_selected_option_should_be(String expected) {
        waitUntilTitleIs("Zero - Account Activity");
        String actual = new AccountActivityPage().selectedAccount();
        assertEquals(expected, actual);
    }

    @Then("the Account dropdown should have following options")
    public void the_Account_dropdown_should_have_following_options(List<String> expectedAccounts) {
        waitUntilTitleIs("Zero - Account Activity");
        List<String> actualAccounts = new AccountActivityPage().allAccountOptions();
        assertEquals(expectedAccounts, actualAccounts);
    }
}
