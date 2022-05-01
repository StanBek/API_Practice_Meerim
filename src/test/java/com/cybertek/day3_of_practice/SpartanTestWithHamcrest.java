package com.cybertek.day3_of_practice;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpartanTestWithHamcrest {
    @BeforeClass
    public void setUp(){
        baseURI="http://34.207.129.114:8000";

    }
    /**
     * Given accept type is Json
     * And pathparam id is 15
     * When user sends a get request to spartans/{id}
     * then status code is 200
     * and content type is json
     * and json data has following
     * id 15
     * name meta
     * gender male
     * phone 3432
     */
    @Test
    public void test1(){
         given().accept(ContentType.JSON)
                .pathParam("id", 13)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).and().assertThat().contentType("application/json");
    }
    @Test
    public void test2(){
        given().accept(ContentType.JSON)
                .pathParam("id",13)
                .when().get("/api/spartans/{id}")
                .then().assertThat().contentType("application/json")
                .and().assertThat().statusCode(200)
                .and().body("id", Matchers.equalTo(13),"name",Matchers.equalTo("Jaimie"),
                "gender",Matchers.equalTo("Female"),"phone",Matchers.equalTo(7842554879L));
    }
}
