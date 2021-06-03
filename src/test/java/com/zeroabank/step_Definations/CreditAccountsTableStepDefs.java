package com.zeroabank.step_Definations;
import com.zeroabank.pages.AccountSummaryPage;
import io.cucumber.java.en.*;
import java.util.List;
import static com.zeroabank.utilities.BrowserUtils.getTextOfElements;
import static org.junit.Assert.assertEquals;

public class CreditAccountsTableStepDefs {

    @Then("the user should see following columns in credit accounts table")
    public void the_user_should_see_following_columns_in_credit_accounts_table(List<String> expectedHeaders) {
        AccountSummaryPage accountSummaryPage = new AccountSummaryPage();
        List<String> actualHeaders = getTextOfElements(accountSummaryPage.creditAccountHeaders);
        assertEquals(expectedHeaders, actualHeaders);
    }
}
