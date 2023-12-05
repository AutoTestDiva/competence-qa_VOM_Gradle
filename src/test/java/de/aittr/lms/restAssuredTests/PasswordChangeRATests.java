package de.aittr.lms.restAssuredTests;

import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PasswordChangeRATests extends TestBaseRA {
    private Cookie cookie;

    @BeforeMethod
    public void precondition() throws SQLException {
        user.registerUser("Cohort 33", "lilu@mail.com", "Lilu", "Test",
                "Germany", "+490571234567");
        user.setPasswordByEmail("lilu@mail.com", "Qwerty123!");
        cookie = user.getLoginCookie("lilu@mail.com", "Qwerty123!");
    }

    @AfterMethod
    public void afterTest() throws SQLException {
        user.deleteUser("lilu@mail.com");
    }

    @Test
    public void testPasswordChangeSuccessPositiveTest() {
        String requestBody = """
                {
                  "oldPassword": "Qwerty123!",
                  "newPassword": "Qwerty123!@"
                }
                """;
        given()
                .cookie(cookie)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/users/password-change").then().assertThat().statusCode(201)
                .assertThat().body("message", equalTo("Your password has been successfully changed"));
    }

    @Test
    public void testInvalidOldPasswordNegativeTest() {
        String invalidOldPassword = "wrong-password";
        String requestBody = "{ \"oldPassword\": \"" + invalidOldPassword + "\", \"newPassword\": \"StrongPassword-123\" }";
        Response response = given()
                .cookie(cookie)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("users/password-change");
        Assert.assertEquals(response.getStatusCode(), 400);
        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message, "Invalid old password.");
        int status = response.jsonPath().getInt("status");
        Assert.assertEquals(status, 0);
    }
}

