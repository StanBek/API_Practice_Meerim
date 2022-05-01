package com.cybertek.day5;

import static org.testng.Assert.*;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
public class BookIt_Auth {
    String accessToken="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMzkiLCJhdWQiOiJzdHVkZW50LXRlYW0tbGVhZGVyIn0._vM1-eRoS7SsHu6T-QPdJoEdA8LSwnxUvvTTbhV-8ms";
    @BeforeClass
    public void setup(){
        baseURI="https://cybertek-reservation-api-qa.herokuapp.com";

    }
    @Test
    public void test1(){
        Response response = given().header("Authorization", accessToken)
                .when().get("/api/campuses");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();
    }
}
