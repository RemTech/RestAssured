package com.rest.test;

import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class Rest_Test {

 private Response response;
 private RestService restService;

 public Rest_Test (){
   this.restService=new RestService();
 }
    @Test
    public void getObject(){
     response=restService.getObjects();
     String page=response.getBody().jsonPath().getString("page");
     int pages=Integer.parseInt(page);
     assertEquals(1,pages);
     System.out.println(pages);
    }

    @Test
    public void postObject() throws IOException, ParseException {
    response=restService.postObject();
     int stat=response.getStatusCode();
     String name=response.jsonPath().get("name").toString();
     assertEquals(201,stat);
     System.out.println(name);
     assertEquals("morpheus",name);
   }

   @Test
    public void registerObject(){
     response=restService.registerObject();
     String token=response.getBody().jsonPath().getString("token");
     assertEquals("QpwL5tke4Pnpja7X4",token);
     String id=response.getBody().jsonPath().getString("id");
     int Id=Integer.parseInt(id);
     assertEquals(4,Id);
   }

   @Test
    public void getProperties(){
     /** Checking the properties of my computer **/
     System.out.println(System.getProperty("user.dir"));
     System.out.println(System.getProperty("os.arch"));
     System.out.println(System.getProperty("java.version"));
     System.out.println(System.getProperty("os.name"));
     assertEquals("C:\\Api",System.getProperty("user.dir"));
     assertEquals("amd64",System.getProperty("os.arch"));
     assertEquals("1.8.0_311",System.getProperty("java.version"));
     assertEquals("Windows 10",System.getProperty("os.name"));
   }
}
