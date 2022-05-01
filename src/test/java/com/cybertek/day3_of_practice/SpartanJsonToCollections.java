package com.cybertek.day3_of_practice;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class SpartanJsonToCollections {
    @BeforeClass
    public void setUp(){
        baseURI="http://34.207.129.114:8000";

    }
    /**
     *  /**
     *      * Given accept type is Json
     *      * And pathparam id is 15
     *      * When user sends a get request to spartans/{id}
     *      * then status code is 200
     *      * and content type is json
     *      * and json data has following
     *      * id 13
     *      * name Jaimie
     *      * gender Female
     *      * phone     "phone": 7842554879
     *      */

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 13)
                .and().when().get("/api/spartans/{id}");
      Map<String,Object>spartanMap=  response.body().as(Map.class);
        System.out.println(spartanMap.get("name"));
        System.out.println("spartanMap.get(\"gender\") = " + spartanMap.get("gender"));
        assertEquals(spartanMap.get("name"),"Jaimie");
        assertEquals(spartanMap.get("gender"),"Female");
      //  assertEquals(spartanMap.get("phone"),7842554879L);
        assertEquals(spartanMap.get("id"),13.0);
       // assertEquals(spartanMap.get("phone"),7842554879l);
      assertThat(spartanMap.get("phone"),is(7.842554879E9));
    }
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");
     //   response.prettyPrint();
        //convert full json body to list of maps
       List<Map<String,Object>>listOfSpartans= response.body().as(List.class);
       //print all data of first spartan
        System.out.println(listOfSpartans.get(0));
        Map<String,Object>firstSpartan=listOfSpartans.get(0);
        System.out.println("firstSpartan.get(\"name\") = " + firstSpartan.get("name"));
        int counter=1;
        for (Map<String, Object> map : listOfSpartans) {
            System.out.println(counter + " - spartan " + map);
            counter++;

        }
    }
}
