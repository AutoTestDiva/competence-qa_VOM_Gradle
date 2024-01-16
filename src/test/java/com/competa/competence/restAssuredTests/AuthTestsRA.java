package com.competa.competence.restAssuredTests;

import com.competa.competence.dto.AuthResponseDto;
import com.competa.competence.dto.ExistEmailResponseDto;
import com.competa.competence.dto.RegisterUserResponseDto;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class AuthTestsRA extends TestBaseRA {

    @Test()
    public void registerUserPositiveTest() throws SQLException {
         user.registerUser("pnata_78@ukr.net", "Pnata1978!")
                .then()
                .assertThat().statusCode(200);

    }

    @Test()
    public void registerUserWithExistEmailTest() throws SQLException {
        ExistEmailResponseDto existEmail = user.registerUser("pnata_78@ukr.net", "Pnata1978!")
                .then()
                .assertThat().statusCode(400)
                .extract().response().as(ExistEmailResponseDto.class);
        System.out.println(existEmail.getMessage());

    }
/*
    @AfterMethod
    public void postCondition() throws SQLException {
        user.deleteUser("pnata_780@ukr.net");
    }*/
}