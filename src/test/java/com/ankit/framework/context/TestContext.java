package com.ankit.framework.context;

import com.ankit.framework.pages.HomePage;
import com.ankit.framework.pages.LoginPage;
import com.ankit.framework.testdata.Users;

import io.restassured.response.Response;

public class TestContext {

    private LoginPage loginPage;
    private HomePage homePage;
    private Users users;
    private Response response;

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }

        return loginPage;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }

        return homePage;
    }

    public Users getUsers() {
        if (users == null) {
            users = com.ankit.framework.utils.JsonUtils.read(
                    "testdata/users/users.json",
                    Users.class
            );
        }

        return users;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
