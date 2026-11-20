package com.ankit.framework.steps;

import com.ankit.framework.database.UserRepository;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class DatabaseSteps {

    private final UserRepository userRepository = new UserRepository();

    @Then("user {string} should exist in database")
    public void userShouldExistInDatabase(String username) {
        Assert.assertTrue(
                userRepository.userExists(username),
                "User does not exist in database: " + username
        );
    }
}
