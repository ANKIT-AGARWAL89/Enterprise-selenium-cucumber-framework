package com.ankit.framework.steps;

import com.ankit.framework.api.clients.UserApiClient;
import com.ankit.framework.context.TestContext;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class ApiSteps {

    private final TestContext testContext;
    private final UserApiClient userApiClient;

    public ApiSteps(TestContext testContext) {
        this.testContext = testContext;
        this.userApiClient = new UserApiClient();
    }

    @When("client gets the users")
    public void clientGetsTheUsers() {
        testContext.setResponse(userApiClient.getUsers());
    }

    @Then("API response status should be {int}")
    public void apiResponseStatusShouldBe(int expectedStatus) {
        Assert.assertEquals(
                testContext.getResponse().statusCode(),
                expectedStatus
        );
    }
}
