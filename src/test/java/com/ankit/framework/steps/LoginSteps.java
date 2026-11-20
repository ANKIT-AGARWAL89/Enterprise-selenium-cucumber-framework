package com.ankit.framework.steps;

import com.ankit.framework.context.TestContext;
import com.ankit.framework.pages.HomePage;
import com.ankit.framework.pages.LoginPage;
import com.ankit.framework.testdata.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {

    private final TestContext testContext;
    private LoginPage loginPage;
    private HomePage homePage;

    public LoginSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("user opens the login page")
    public void userOpensTheLoginPage() {
        loginPage = testContext.getLoginPage();
        loginPage.open();
    }

    @When("user enters valid credentials")
    public void userEntersValidCredentials() {
        User user = testContext.getUsers().getValidUser();

        loginPage
                .enterUsername(user.getUsername())
                .enterPassword(user.getPassword());
    }

    @When("user clicks login")
    public void userClicksLogin() {
        homePage = loginPage.clickLogin();
    }

    @Then("home page should be displayed")
    public void homePageShouldBeDisplayed() {
        Assert.assertTrue(
                homePage.isDisplayed(),
                "Home page was not displayed"
        );
    }
}
