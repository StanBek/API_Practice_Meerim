package com.cybertek.day2_of_practice;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import static org.testng.Assert.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
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
     * And path param id is 10
     * When user sends a get request to "/spartans{id}"
     * Then status code is 200
     * And content type is "application/json;char"
     * And response payload values match the following
     * id is 10,
     * name is "Lorenza"
     * gender is Female
     * phone is 3312820936
     */

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 10)
                .when().get("/api/spartans/{id}");
        //verify status code
        assertEquals(response.statusCode(),200);
        //verify content type
       assertEquals(response.contentType(),"application/json");
       //printing values of json keys
        System.out.println("Id:"+response.body().path("id").toString());
        System.out.println("name:"+response.body().path("name").toString());
        System.out.println("gender:"+response.body().path("gender").toString());
        System.out.println("phone:"+response.body().path("phone").toString());
        int id=response.path("id");
        String name=response.path("name");
        String gender=response.body().path("gender");
        long phone=response.body().path("phone");
        System.out.println("id "+id);
        System.out.println("name "+name);
        System.out.println("gender "+gender);
        System.out.println("phone "+phone);
        //verify json keys and values
        assertEquals(id,10);
        assertEquals(name,"Lorenza");
        assertEquals(gender,"Female");
        assertEquals(phone,3312820936l);

    }
    @Test
    public void test2(){
        Response response = given().contentType(ContentType.JSON)
                .pathParam("id", 30)
                .when().get("/api/spartans/{id}");
        //verify status code
        System.out.println(response.statusCode());
        assertEquals(response.statusCode(),200);
        //verify content type
        assertEquals(response.contentType(),"application/json");
        //verify values of json keys
        System.out.println(response.body().path("id").toString());
        System.out.println(response.body().path("name").toString());
        System.out.println(response.path("gender").toString());
        System.out.println(response.path("phone").toString());
        int id=response.path("id");
        String name=response.path("name");
        String gender=response.path("gender");
        long phone=response.path("phone");
        assertEquals(id,30);
        assertEquals(name,"Melania");
        assertEquals(gender,"Female");
        assertEquals(phone,8916944276l);
    }
    @Test
    public void test3(){
        Response response = get("/api/spartans");
        //extract first id
        int firstID=response.path("id[0]");
        System.out.println("firstID = " + firstID);
        //extract name
        String first1stName=response.path("name[0]");
        System.out.println("first1stName = " + first1stName);
        //get the last firstname
        String last1stName=response.path("name[-1]");
        System.out.println("last1stName = " + last1stName);
        //extract all firstNames and print them
       List<String> names= response.path("name");
        System.out.println(names);
        System.out.println(names.size()+" Names");
        List<Object>phoneNumbers=response.path("phone");
        for(Object phoneNumber:phoneNumbers){
            System.out.println(phoneNumber);
        }



    }
}
