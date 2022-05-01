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
public class SpartanPUTRequest {
    @BeforeClass
    public void setUp() {

        baseURI = "http://34.207.129.114:8000";
    }
    @Test
    public void test1(){
        String newSpartan="\"name\": \"Ollen\",\n" +
                "        \"gender\": \"Male\",\n" +
                "        \"phone\": 8716150217";

        //we gonna send request body with updated value
        Map<String,Object>updateSpartan=new HashMap<>();
        updateSpartan.put("name","Timur");
        updateSpartan.put("gender","Male");
        updateSpartan.put("phone",24352525353L);
        given().contentType(ContentType.JSON)
                .pathParam("id",18)
                .and().body(updateSpartan)
                .when().put("/api/spartans/{id}").then().statusCode(204);
    }
    @Test
    public void test2(){
        String newSpartan="\"name\": \"Ollen\",\n" +
                "        \"gender\": \"Male\",\n" +
                "        \"phone\": 8716150217";

        //we gonna send request body with updated value
        Map<String,Object>PatchSpartan=new HashMap<>();
        PatchSpartan.put("name","Alena");
        given().contentType(ContentType.JSON)
                .pathParam("id",18)
                .and().body(PatchSpartan)
                .when().patch("/api/spartans/{id}").then().statusCode(204);
    }
    @Test
    public void test3(){
        given().pathParam("id",1)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);
    }
}