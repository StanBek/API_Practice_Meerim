package com.cybertek.day2_of_practice;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpartanTestsWithPathParameters2 {
    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI="http://34.207.129.114:8000";

    }
    @Test
    public void test1(){
        /**
         * Given accept type is Json
         * And ID parameter value is 18
         * When user sends GET Request to /api/spartans/{id}
         * Then response status code should be 200
         * And response content type  : application/json
         * And "Allen" should be in response payload
         */
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",18)
                .when().get("/api/spartans/{id}");
       assertEquals(response.statusCode(),200);
       assertEquals(response.contentType(),"application/json");
       assertTrue(response.body().asString().contains("Allen"));
       response.body().prettyPrint();


    }
    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .when().get("/api/spartans/{id}");
        assertEquals(response.statusCode(),404);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("Not Found"));

        response.prettyPrint();
    }
}
