package com.cybertek.day3_of_practice;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class SpartanTestWithPathMethod {
    @BeforeClass
    public void setUpClass(){
      baseURI="http://34.207.129.114:8000";
    }
    /**
     * Given accept type is json
     * and path param id is 10
     * When user sends a get request to "/api/spartans{id}"
     * then status code is 200
     * and content type is "application/json
     * and response payload values math the following
     * id is 10
     * name is "lorenza"
     * gender is "female"
     * phone is 32513255433
     */
    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                .and().pathParam("id",10)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response().body().prettyPrint();

    }
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 10)
                .when().get("/api/spartans/{id}");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        System.out.println("ID"+response.body().path("id").toString());
        System.out.println("name: "+response.body().path("name").toString());
        System.out.println("gender: "+response.body().path("gender").toString());
        System.out.println("phone: "+response.body().path("phone").toString());
        int id=response.path("id");
        String name=response.path("name");
        String gender=response.path("gender");
        long phone=response.path("phone");
        assertEquals(10,id);
        assertEquals("Lorenza",name);
        assertEquals("Female",gender);
        assertEquals(3312820936l,phone);
    }
    @Test
    public void test3(){
        Response response = get("/api/spartans");
       // response.prettyPrint();
        int firstID=response.path("id[0]");
        System.out.println(firstID);

        String first1stName=response.path("name[0]");
        System.out.println("first1stName = " + first1stName);

        String lastFirstName=response.path("name[-1]");
        System.out.println("lastFirstName = " + lastFirstName);

        List<String>allFirstNames=response.path("name");
        for(String each:allFirstNames){
            each.toString();
            System.out.println(each);
        }
        List<Object>allPhoneNumbers=response.path("phone");
        System.out.println(allPhoneNumbers);
    }
}
