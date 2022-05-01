package com.cybertek.day2_of_practice;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class SpartanTestsWithQueryParams2 {
    @BeforeClass
    public void setUpClass() {
        baseURI = "http://34.207.129.114:8000";

    }

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "j")
                .when().get("/api/spartans/search");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));
    }
    @Test
    public void test2(){
        Map<String,Object> paramsMap=new HashMap<>();
        paramsMap.put("gender","Female");
        paramsMap.put("nameContains","J");
        Response response = given().accept(ContentType.JSON)
                .and().queryParams(paramsMap)
                .when().get("/api/spartans/search");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));
    }
}
