package com.cybertek.day4;

import com.google.gson.Gson;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
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


public class SpartanTestsPOJODeserialization {
    @BeforeClass
    public void setUp(){

        baseURI = "http://34.207.129.114:8000";
    }
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 7)
                .when().get("/api/spartans/{id}");
       // response.prettyPrint();
        //how to convert json response to our spartan class

        Spartan spartan=response.body().as(Spartan.class);
        System.out.println("spartan.toString() = " + spartan.toString());
        assertEquals(spartan.getName(),"Hershel");
        assertEquals(spartan.getId(),7);
        assertEquals(spartan.getGender(),"Male");
        assertEquals(spartan.getPhone(),new Long(5278678322L));
    }
   @Test
   public void gsonExample(){
       Gson gson=new Gson();
        String myJsonBody="{id=7, name='Hershel', gender='Male', phone=5278678322}";
        //using gson method do  de serialize our json body
       Spartan spartanHershel=gson.fromJson(myJsonBody,Spartan.class);
       System.out.println("spartanHershel = " + spartanHershel);
   }

}
