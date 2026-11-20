package com.ankit.framework.api.clients;

import com.ankit.framework.api.ApiUtils;
import io.restassured.response.Response;

public class UserApiClient {

    public Response getUsers() {
        return ApiUtils.requestSpec()
                .when()
                .get("/users");
    }

    public Response getUserById(String userId) {
        return ApiUtils.requestSpec()
                .when()
                .get("/users/{id}", userId);
    }
}
