package com.zeroabank.step_Definations;

import com.zeroabank.pages.PayBillsPage;
import io.cucumber.java.en.*;

import java.util.Map;

import static com.zeroabank.utilities.BrowserUtils.waitUntilVisible;
import static org.junit.Assert.assertEquals;

public class AddNewPayeeStepDefs {
    PayBillsPage payBillsPage = new PayBillsPage();

    @Given("user navigates to Pay Bills")
    public void user_navigates_to_pay_bills() {
        payBillsPage.payBill.click();
    }

    @When("user accesses the Add New Payee tab")
    public void user_accesses_the_add_new_payee_tab() {
        payBillsPage.addNewPayee.click();
    }


    @When("creates new payee using following information")
    public void creates_new_payee_using_following_information(Map<String, String> payeeInfo) {
        payBillsPage.payeeName.sendKeys(payeeInfo.get("Payee Name"));
        payBillsPage.payeeAddress.sendKeys(payeeInfo.get("Payee Address"));
        payBillsPage.payeeAccount.sendKeys(payeeInfo.get("Account"));
        payBillsPage.payeeDetails.sendKeys(payeeInfo.get("Payee details"));
        payBillsPage.addButton.click();


    }

    @Then("message {string} should be displayed")
    public void message_should_be_displayed(String expectedMessage) {
        waitUntilVisible(payBillsPage.alertMessage);
        String actualMessage = payBillsPage.alertMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
