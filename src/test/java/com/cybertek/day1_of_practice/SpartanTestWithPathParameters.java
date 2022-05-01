package com.cybertek.day1_of_practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpartanTestWithPathParameters {
    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI="http://34.207.129.114:8000";


    }
    /**
     * Given accepts type is Json
     * And Id parameter value is 18
     * When user sends GET request to /api/spartans/{id}
     * Then response status code should be 200
     * And response content-type: application/json;charset=UTF-8
     * and "Allen" should be in response payload
     */
    @Test
    public void PathTest1() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 18)
                .when().get("/api/spartans/{id}");
        //verify status code
        assertEquals(response.statusCode(), 200);
        //verify content type
        assertEquals(response.contentType(), "application/json");
        //verify Allen exists
        assertTrue(response.body().asString().contains("Allen"));
    }
    /**
     * Given accept type is Json
     * And ID parameter value is 500
     * When user sends Get request to /api/spartans/{id}
     * Then response status code should be 404
     * And response content -type: applications/json
     * And "Spartan not found message should be in response payload
     *
     */

    @Test
    public void negativePathParamTest() {
      Response response=  RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id",500)
                .when().get("/api/spartans/{id}");
        //verify status code
        assertEquals(response.statusCode(),404);
        //verify content type
        assertEquals(response.contentType(),"application/json");
        //verify spartan not found
        assertTrue(response.body().asString().contains(""));
         response.prettyPrint();

    }


}
