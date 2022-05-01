package com.cybertek.day1_of_practice;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class SpartanTestWithQueryParams {
    @BeforeClass
    public void setUpClass() {
      baseURI = "http://34.207.129.114:8000";
    }


        /**
         * Given accept type is Json
         * And query parameter values are:
         * gender/female
         * name contains/e
         * When user sends Get request to /api/spartans/search
         * Then response status code should be 200
         * And response content type: application/json
         * and female should be in response payload
         * and jannette should be in response payload
         *
         */
        @Test
    public void QueryParam1(){
    Response response=given().accept(ContentType.JSON)
            .and().queryParam("gender","Female")
            .and().queryParam("nameContains","J")
            .when().get("/api/spartans/search");
    //verify status code
          assertEquals(response.statusCode(),200);
          //verify contenty type
            assertEquals(response.contentType(),"application/json");
            //verify Female
            assertTrue(response.body().asString().contains("Female"));
            //verify male not exist
            assertFalse(response.body().asString().contains("Male"));

            //verify Janette
            assertTrue(response.body().asString().contains("Janette"));
        response.prettyPrint();

        }
        @Test
    public void queryParams2(){
            //creating map for query Params
            Map<String,Object>paramsMap=new HashMap<>();
            paramsMap.put("gender","Female");
            paramsMap.put("nameContains","J");

            //send request
            Response response=given().accept(ContentType.JSON)
                    .and().queryParams(paramsMap)
                    .when().get("/api/spartans/search");

            //verify status code
            assertEquals(response.statusCode(),200);
            //verify contenty type
            assertEquals(response.contentType(),"application/json");
            //verify Female
            assertTrue(response.body().asString().contains("Female"));
            //verify male not exist
            assertFalse(response.body().asString().contains("Male"));

            //verify Janette
            assertTrue(response.body().asString().contains("Janette"));
            response.prettyPrint();


        }
    }

