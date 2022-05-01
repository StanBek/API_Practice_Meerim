package com.cybertek.day5;
import static org.testng.Assert.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static io.restassured.RestAssured.*;
import org.testng.annotations.BeforeClass;

public class JsonSchemaValidation {
    @BeforeClass
    public void setup(){
        baseURI="http://34.207.129.114:8000";
    }
    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                .pathParam("id",38)
                .when().get("/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().assertThat().body(matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));

    }
}
