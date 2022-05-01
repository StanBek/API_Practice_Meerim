package com.cybertek.day5;
import com.cybertek.day4.Spartan;
import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;
public class SpartanPostRequest {
    @BeforeClass
    public void setUp(){

        baseURI = "http://34.207.129.114:8000";
    }
    @Test
    public void PostWithString(){
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body("{\n"+"\"gender\": \"Female\",\"name\": \"Jaimilya\",\"phone\": 7842554879\n"+"}")
                .when().post("/api/spartans/");
        response.prettyPrint();
      assertEquals(response.statusCode(),201);
      assertEquals(response.contentType(),"application/json");
      assertEquals(response.path("success"),"A Spartan is Born");
        JsonPath jsonPath=response.jsonPath();
        assertEquals(jsonPath.getString("data.name"),"Jaimilya");
        assertEquals(jsonPath.getString("data.gender"),"Female");
        assertEquals(jsonPath.getLong("data.phone"),7842554879L);

    }
    @Test
    public void PostMethodWithMap(){
        //create a map to be used as a body for post request
        Map<String,Object>requestMap=new HashMap<>();
        requestMap.put("name","Jaimilya");
        requestMap.put("gender","Female");
        requestMap.put("phone",7842554879L);
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(requestMap)
                .when().post("/api/spartans/");
        assertEquals(response.statusCode(),201);
        response.prettyPrint();
    }
    @Test
    public void POSTWithPOJO(){
        //create Spartan object and used as a body for post request
        Spartan spartan=new Spartan();
        spartan.setName("Timur");
        spartan.setGender("Male");
        spartan.setPhone(5555555555L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartan)
                .when().post("/api/spartans/");

        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json");
        response.prettyPrint();

        //==============GET REQUEST
        Response response1 = given().accept(ContentType.JSON)
                .pathParam("id", 126)
                .and().when().get("/api/spartans/{id}");
        Spartan spartanResponse=response1.body().as(Spartan.class);
        System.out.println(spartanResponse.toString());

    }

}
