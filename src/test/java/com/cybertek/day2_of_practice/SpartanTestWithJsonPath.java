package com.cybertek.day2_of_practice;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.testng.Assert.*;

import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
public class SpartanTestWithJsonPath {
    @BeforeClass
    public void setUpClass(){
        baseURI="http://34.207.129.114:8000";

    }
    @Test
    public void test1(){
        /**
         * Given accept type is json
         * And path param spartan id is 11
         * When user sends a get request to /spartan/{id}
         * Then status code is 200
         * And content type is Json
         * And "id" 11
         * "name":"Nona"
         * gender female
         * phone" 7959094216
         *
         */
        Response response = given().contentType(ContentType.JSON)
                .and().pathParam("id", 11)
                .when().get("api/spartans/{id}");
        System.out.println("response.statusCode() = " + response.statusCode());
       assertEquals(response.statusCode(),200);
       assertEquals(response.contentType(),"application/json");
        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.path("gender").toString());
        System.out.println(response.path("phone").toString());
        int id=response.path("id");
        String name=response.path("name");
        String gender=response.body().path("gender");
        long phone=response.body().path("phone");
        assertEquals(id,11);
        assertEquals(name,"Nona");
        assertEquals(gender,"Female");
        assertEquals(phone,7959094216l);
    }
    @Test
    public void test2(){
        /**
         * Given accept type is json
         * And path param spartan id is 11
         * When user sends a get request to /spartans/{id}
         * Then status code is 200
         * And content type is Json
         * And "id" 11
         * name Nona
         * gender female
         * phone 3532
         */
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 11)
                .when().get("/api/spartans/{id}");
        JsonPath jsonPath=response.jsonPath();
        int id=jsonPath.getInt("id");
        String name=jsonPath.getString("name");
        String gender=jsonPath.getString("gender");
        long phone=jsonPath.getLong("phone");
        assertEquals(11,id);
        assertEquals("Nona",name);
        assertEquals("Female",gender);
        assertEquals(7959094216L,phone);
    }
}
